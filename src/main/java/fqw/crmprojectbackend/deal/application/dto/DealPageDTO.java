package fqw.crmprojectbackend.deal.application.dto;

import fqw.crmprojectbackend.common.web.response.EntityPageDTO;

import java.util.List;

public class DealPageDTO extends EntityPageDTO<DealDTO> {

    public DealPageDTO(
            Long total,
            List<DealDTO> data) {
        super(total, data);
    }
}
