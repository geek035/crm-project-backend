package fqw.crmprojectbackend.deal.application.port.out;

import fqw.crmprojectbackend.deal.application.request.DealAddRequest;
import fqw.crmprojectbackend.deal.domain.model.deal.DealNumber;

import java.util.UUID;

public interface DealRepositoryPort {
    boolean existsByNumber(DealNumber number);
    UUID add(DealAddRequest origin);
}
