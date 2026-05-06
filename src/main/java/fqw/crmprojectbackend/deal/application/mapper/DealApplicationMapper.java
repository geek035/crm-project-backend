package fqw.crmprojectbackend.deal.application.mapper;

import fqw.crmprojectbackend.common.dto.DirectoryEntryDTO;
import fqw.crmprojectbackend.deal.application.dto.DealDTO;
import fqw.crmprojectbackend.deal.application.request.DealAddRequest;
import fqw.crmprojectbackend.deal.domain.model.client.DealClientTypeCode;
import fqw.crmprojectbackend.deal.domain.model.deal.Deal;

public class DealApplicationMapper {
    public static DealAddRequest toRequest(Deal deal) {
        var client = deal.getClient();
        var individualID = client.getType().code() == DealClientTypeCode.INDIVIDUAL
                ? client.getIndividualID().getValue()
                : null;
        var companyID = client.getType().code() == DealClientTypeCode.COMPANY
                ? client.getCompanyID().getValue()
                : null;
        var closeInfo = deal.getCloseInfo();

        return new DealAddRequest(
                deal.getId().getValue(),
                deal.getNumber().value(),
                client.getType().code().name(),
                individualID,
                companyID,
                deal.getTitle().value(),
                deal.getDescription().value(),
                deal.getProduct().code().name(),
                deal.getAmount().value(),
                deal.getAmount().currency().code().name(),
                deal.getStage().code().name(),
                deal.getStatus().code().name(),
                deal.getProbability().value(),
                deal.getPriority().code().name(),
                deal.getSource().code().name(),
                deal.getExpectedCloseDate(),
                closeInfo == null ? null : closeInfo.actualCloseDate(),
                closeInfo == null || closeInfo.lossReason() == null
                        ? null
                        : closeInfo.lossReason().code().name());
    }

    public static DealDTO fromDomainModel(Deal deal) {
        return new DealDTO(
                deal.getId().getValue(),
                deal.getNumber().value(),
                new DirectoryEntryDTO(
                        deal.getClient().getType().code().name(),
                        deal.getClient().getType().code().getDescription()),
                deal.getClient().getIndividualID() == null
                        ? null
                        : deal.getClient().getIndividualID().getValue(),
                deal.getClient().getCompanyID() == null
                        ? null
                        : deal.getClient().getCompanyID().getValue(),
                deal.getTitle().value(),
                deal.getDescription().value(),
                new DirectoryEntryDTO(
                        deal.getProduct().code().name(),
                        deal.getProduct().code().getDescription()),
                deal.getAmount().value(),
                new DirectoryEntryDTO(
                        deal.getAmount().currency().code().name(),
                        deal.getAmount().currency().code().getDescription()),
                new DirectoryEntryDTO(
                        deal.getStage().code().name(),
                        deal.getStage().code().getDescription()),
                new DirectoryEntryDTO(
                        deal.getStatus().code().name(),
                        deal.getStatus().code().getDescription()),
                deal.getProbability().value(),
                new DirectoryEntryDTO(
                        deal.getPriority().code().name(),
                        deal.getPriority().code().getDescription()),
                new DirectoryEntryDTO(
                        deal.getSource().code().name(),
                        deal.getSource().code().getDescription()),
                deal.getExpectedCloseDate(),
                deal.getCloseInfo() == null ? null : deal.getCloseInfo().actualCloseDate(),
                deal.getCloseInfo() == null || deal.getCloseInfo().lossReason() == null
                        ? null
                        : new DirectoryEntryDTO(
                                deal.getCloseInfo().lossReason().code().name(),
                                deal.getCloseInfo().lossReason().code().getDescription()));
    }
}
