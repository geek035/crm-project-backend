package fqw.crmprojectbackend.individual.application.port.in;

import fqw.crmprojectbackend.individual.adapter.in.web.exception.IndividualDuplicateEmailException;
import fqw.crmprojectbackend.individual.application.command.IndividualAddCommand;

import java.util.UUID;

public interface IndividualAddUseCase {
    UUID addIndividual(IndividualAddCommand command) throws IndividualDuplicateEmailException;
}
