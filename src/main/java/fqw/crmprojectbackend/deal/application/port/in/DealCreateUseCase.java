package fqw.crmprojectbackend.deal.application.port.in;

import fqw.crmprojectbackend.deal.application.command.DealCreateCommand;

import java.util.UUID;

public interface DealCreateUseCase {
    UUID create(DealCreateCommand command);
}
