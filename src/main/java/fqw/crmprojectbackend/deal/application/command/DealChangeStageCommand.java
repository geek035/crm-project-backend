package fqw.crmprojectbackend.deal.application.command;

import java.util.Objects;

public record DealChangeStageCommand(
        String stageCode,
        String closeInfo
) {
    public DealChangeStageCommand {
        Objects.requireNonNull(stageCode);
    }
}
