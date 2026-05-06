package fqw.crmprojectbackend.individual.adapter.in.web;

import fqw.crmprojectbackend.common.web.exception.HTTPErrorCode;
import fqw.crmprojectbackend.common.web.exception.WebException;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualUpdateDTO;
import fqw.crmprojectbackend.individual.application.dto.IndividualPageDTO;
import fqw.crmprojectbackend.individual.application.port.in.IndividualUpdateUseCase;
import fqw.crmprojectbackend.individual.domain.exception.IndividualDuplicateEmailException;
import fqw.crmprojectbackend.individual.adapter.in.web.mapper.IndividualWebMapper;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualAddDTO;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualQueryDTO;
import fqw.crmprojectbackend.individual.application.dto.IndividualDTO;
import fqw.crmprojectbackend.individual.application.query.IndividualByIDQuery;
import fqw.crmprojectbackend.individual.application.port.in.IndividualAddUseCase;
import fqw.crmprojectbackend.individual.application.port.in.IndividualQueryUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "individuals")
@RequiredArgsConstructor
public class IndividualController {
    private final IndividualAddUseCase individualAddUseCase;
    private final IndividualQueryUseCase individualQueryUseCase;
    private final IndividualUpdateUseCase individualUpdateUseCase;

    @PostMapping(path="query")
    public ResponseEntity<IndividualPageDTO> getIndividualsByParams(
            @RequestBody(required = false) @Valid IndividualQueryDTO body) {

        var params = IndividualWebMapper.toApplicationModel(body);
        var individuals = this.individualQueryUseCase.findByParams(params);
        var response = new IndividualPageDTO(individuals.getTotal(), individuals.getData());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path="create")
    public ResponseEntity<UUID> addIndividual(
            @RequestBody @Valid IndividualAddDTO body)
            throws IndividualDuplicateEmailException {

        var command = IndividualWebMapper.toApplicationModel(body);
        UUID id = this.individualAddUseCase.addIndividual(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<IndividualDTO> getIndividualById(
            @PathVariable @Valid @NotNull UUID id) {
        var individual = this.individualQueryUseCase.findById(new IndividualByIDQuery(id));

        if (individual.isEmpty())
            throw new WebException(
                    HTTPErrorCode.RESOURCE_NOT_FOUND,
                    String.format("Ресурс с id '%s' не найден", id));

        return ResponseEntity.status(HttpStatus.OK).body(individual.get());
    }

    @PostMapping(path = "{id}")
    public ResponseEntity<IndividualDTO> updateIndividual(
            @PathVariable @Valid @NotNull UUID id,
            @RequestBody @Valid IndividualUpdateDTO body) {

        var command = IndividualWebMapper.toApplicationModal(id, body);
        var updated = this.individualUpdateUseCase.update(command);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}
