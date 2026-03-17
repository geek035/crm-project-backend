package fqw.crmprojectbackend.individual.application.command;

import java.util.Objects;
import java.util.UUID;

public record IndividualSelectByIDCommand(UUID id) {
    public IndividualSelectByIDCommand {
        Objects.requireNonNull(id);
    }
}
