package fqw.crmprojectbackend.individual.application.dto;

import fqw.crmprojectbackend.common.web.response.EntityPageDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class IndividualPageDTO extends EntityPageDTO<IndividualDTO> {

    public IndividualPageDTO(
            Long total,
            List<IndividualDTO> data) {
        super(total, data);
    }
}
