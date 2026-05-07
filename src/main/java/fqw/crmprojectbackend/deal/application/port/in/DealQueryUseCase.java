package fqw.crmprojectbackend.deal.application.port.in;

import fqw.crmprojectbackend.deal.application.dto.DealDTO;
import fqw.crmprojectbackend.deal.application.dto.DealPageDTO;
import fqw.crmprojectbackend.deal.application.query.DealQueryParams;

import java.util.UUID;

public interface DealQueryUseCase {
    DealDTO findByID(UUID id);
    DealPageDTO findByParams(DealQueryParams params);
}
