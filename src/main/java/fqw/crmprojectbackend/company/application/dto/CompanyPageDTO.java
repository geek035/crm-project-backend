package fqw.crmprojectbackend.company.application.dto;

import fqw.crmprojectbackend.common.web.response.EntityPageDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class CompanyPageDTO extends EntityPageDTO<CompanyDTO> {

    public CompanyPageDTO(
            Long total,
            List<CompanyDTO> data) {
        super(total, data);
    }
}