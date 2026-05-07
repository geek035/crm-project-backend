package fqw.crmprojectbackend.deal.domain.model.deal;

import fqw.crmprojectbackend.deal.domain.exception.DealClosedException;
import fqw.crmprojectbackend.deal.domain.exception.DealIllegalStageChangeException;
import fqw.crmprojectbackend.deal.domain.model.client.DealClient;
import fqw.crmprojectbackend.deal.domain.model.money.DealAmount;
import fqw.crmprojectbackend.deal.domain.model.process.*;
import fqw.crmprojectbackend.deal.domain.model.product.DealProduct;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class Deal {
    private DealID id;
    private DealNumber number;
    private DealClient client;
    private DealTitle title;
    private DealDescription description;
    private DealProduct product;
    private DealAmount amount;
    private DealStage stage;
    private DealStatus status;
    private DealProbability probability;
    private DealPriority priority;
    private DealSource source;
    private LocalDate expectedCloseDate;
    private DealCloseInfo closeInfo;

    public Deal(
            DealID id,
            DealNumber number,
            DealClient client,
            DealTitle title,
            DealDescription description,
            DealProduct product,
            DealAmount amount,
            DealStage stage,
            DealStatus status,
            DealProbability probability,
            DealPriority priority,
            DealSource source,
            LocalDate expectedCloseDate,
            DealCloseInfo closeInfo) {
        this.id = id;
        this.number = number;
        this.client = client;
        this.title = title;
        this.description = description;
        this.product = product;
        this.amount = amount;
        this.stage = stage;
        this.status = status;
        this.probability = probability;
        this.priority = priority;
        this.source = source;
        this.expectedCloseDate = expectedCloseDate;
        this.closeInfo = closeInfo;
    }

    public static Deal createNew(
            DealNumber number,
            DealClient client,
            DealTitle title,
            DealDescription description,
            DealProduct product,
            DealAmount amount,
            DealPriority priority,
            DealSource source,
            LocalDate expectedCloseDate) {
        return new Deal(
                DealID.generateID(),
                number,
                client,
                title,
                description,
                product,
                amount,
                new DealStage(DealStageCode.LEAD),
                new DealStatus(DealStatusCode.OPEN),
                new DealProbability(10),
                priority,
                source,
                expectedCloseDate,
                null);
    }

    public void changeStage(DealStage nextStage) {
        this.changeStage(nextStage, null);
    }

    public void changeStage(DealStage nextStage, DealLossReason lossReason) {
        this.ensureNotClosed();
        this.checkStageTransition(nextStage.code());
        this.checkCloseInfo(nextStage.code(), lossReason);

        this.stage = nextStage;
        this.status = new DealStatus(DealStatusCode.fromStage(nextStage.code()));
        this.probability = new DealProbability(defaultProbability(nextStage.code()));

        if (nextStage.code() == DealStageCode.WON) {
            this.closeInfo = DealCloseInfo.won(LocalDate.now());
        } else if (nextStage.code() == DealStageCode.LOST || nextStage.code() == DealStageCode.CANCELLED) {
            this.closeInfo = DealCloseInfo.failed(LocalDate.now(), lossReason);
        } else {
            this.closeInfo = null;
        }
    }

    public void closeAsWon() {
        this.changeStage(new DealStage(DealStageCode.WON), null);
    }

    public void closeAsLost(DealLossReason lossReason) {
        this.changeStage(new DealStage(DealStageCode.LOST), lossReason);
    }

    public void cancel(DealLossReason lossReason) {
        this.changeStage(new DealStage(DealStageCode.CANCELLED), lossReason);
    }

    public void changeStatus(DealStatusCode nextStatus, DealLossReason lossReason) {
        switch (nextStatus) {
            case OPEN -> {
                this.ensureNotClosed();

                if (lossReason != null) {
                    throw new DealIllegalStageChangeException(
                            "Причина закрытия не может быть указана для открытой сделки");
                }

                this.status = new DealStatus(DealStatusCode.OPEN);
                this.probability = new DealProbability(defaultProbability(this.stage.code()));
                this.closeInfo = null;
            }

            case SUCCESS -> this.changeStage(new DealStage(DealStageCode.WON), lossReason);
            case FAILED -> this.changeStage(new DealStage(DealStageCode.LOST), lossReason);
            case CANCELLED -> this.changeStage(new DealStage(DealStageCode.CANCELLED), lossReason);
        }
    }

    public void changeProduct(DealProduct product) {
        this.ensureNotClosed();
        this.product = product;
    }

    public void changeAmount(DealAmount amount) {
        this.ensureNotClosed();
        this.amount = amount;
    }

    public void changePriority(DealPriority priority) {
        this.ensureNotClosed();
        this.priority = priority;
    }

    public void changeSource(DealSource source) {
        this.ensureNotClosed();
        this.source = source;
    }

    public void changeExpectedCloseDate(LocalDate expectedCloseDate) {
        this.ensureNotClosed();
        this.expectedCloseDate = expectedCloseDate;
    }

    public void changeTitle(String title) {
        this.ensureNotClosed();
        this.title = new DealTitle(title);
    }

    public void updateDescription(String description) {
        this.ensureNotClosed();
        this.description = new DealDescription(description);
    }

    private void ensureNotClosed() {
        if (this.stage.code().isFinal()) {
            throw new DealClosedException(
                    String.format("Сделка уже закрыта на стадии '%s'", this.stage.code()));
        }
    }

    private void checkStageTransition(DealStageCode nextStage) {
        var allowedStages = allowedNextStages(this.stage.code());

        if (!allowedStages.contains(nextStage)) {
            throw new DealIllegalStageChangeException(String.format(
                    "Невозможно изменить стадию сделки с '%s' на '%s'",
                    this.stage.code(),
                    nextStage));
        }
    }

    private void checkCloseInfo(DealStageCode nextStage, DealLossReason lossReason) {
        if (nextStage == DealStageCode.WON && lossReason != null) {
            throw new DealIllegalStageChangeException(
                    "Причина закрытия не может быть указана для успешно завершенной сделки");
        }

        if ((nextStage == DealStageCode.LOST || nextStage == DealStageCode.CANCELLED)
                && lossReason == null) {
            throw new DealIllegalStageChangeException(
                    "Причина закрытия обязательна для проигранной или отмененной сделки");
        }

        if (!nextStage.isFinal() && lossReason != null) {
            throw new DealIllegalStageChangeException(
                    "Причина закрытия может быть указана только при закрытии сделки");
        }
    }

    private Set<DealStageCode> allowedNextStages(DealStageCode currentStage) {
        return switch (currentStage) {
            case LEAD -> Set.of(
                    DealStageCode.QUALIFICATION,
                    DealStageCode.CANCELLED);
            case QUALIFICATION -> Set.of(
                    DealStageCode.OFFER_PREPARATION,
                    DealStageCode.LOST,
                    DealStageCode.CANCELLED);
            case OFFER_PREPARATION -> Set.of(
                    DealStageCode.CLIENT_APPROVAL,
                    DealStageCode.BANK_REVIEW,
                    DealStageCode.LOST,
                    DealStageCode.CANCELLED);
            case CLIENT_APPROVAL -> Set.of(
                    DealStageCode.DOCUMENT_SIGNING,
                    DealStageCode.LOST,
                    DealStageCode.CANCELLED);
            case BANK_REVIEW -> Set.of(
                    DealStageCode.DOCUMENT_SIGNING,
                    DealStageCode.LOST,
                    DealStageCode.CANCELLED);
            case DOCUMENT_SIGNING -> Set.of(
                    DealStageCode.WON,
                    DealStageCode.LOST,
                    DealStageCode.CANCELLED);
            case WON, LOST, CANCELLED -> Set.of();
        };
    }

    private Integer defaultProbability(DealStageCode stage) {
        return switch (stage) {
            case LEAD -> 10;
            case QUALIFICATION -> 20;
            case OFFER_PREPARATION -> 40;
            case CLIENT_APPROVAL -> 60;
            case BANK_REVIEW -> 70;
            case DOCUMENT_SIGNING -> 90;
            case WON -> 100;
            case LOST, CANCELLED -> 0;
        };
    }
}
