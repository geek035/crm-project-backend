package fqw.crmprojectbackend.deal.application.port.in;

import fqw.crmprojectbackend.deal.application.command.DealChangeStageCommand;
import fqw.crmprojectbackend.deal.application.command.DealChangeStatusCommand;
import fqw.crmprojectbackend.deal.application.command.DealUpdateCommand;
import fqw.crmprojectbackend.deal.application.dto.DealDTO;

import java.util.UUID;

public interface DealUpdateUseCase {
    DealDTO update(UUID id, DealUpdateCommand command);
    DealDTO changeStage(UUID id, DealChangeStageCommand command);
    DealDTO changeStatus(UUID id, DealChangeStatusCommand command);
}
