package fqw.crmprojectbackend.individual.adapter.in.web.response;

import fqw.crmprojectbackend.common.query.EntityPageDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.util.List;

@Getter
public class IndividualPageDTO extends EntityPageDTO<IndividualDTO> {

    public IndividualPageDTO(
            @NotNull
            @PositiveOrZero
            Long total,
            @NotNull
            List<IndividualDTO> data) {
        super(total, data);
    }
}
