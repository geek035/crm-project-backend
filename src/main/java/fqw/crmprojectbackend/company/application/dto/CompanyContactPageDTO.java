package fqw.crmprojectbackend.company.application.dto;

import fqw.crmprojectbackend.common.web.response.EntityPageDTO;

import java.util.List;

public class CompanyContactPageDTO extends EntityPageDTO<CompanyContactDTO> {

    public CompanyContactPageDTO(
            Long total,
            List<CompanyContactDTO> data) {
        super(total, data);
    }
}
