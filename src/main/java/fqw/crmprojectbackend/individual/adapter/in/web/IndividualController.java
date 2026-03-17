package fqw.crmprojectbackend.individual.adapter.in.web;

import fqw.crmprojectbackend.common.exception.web.APIErrorCode;
import fqw.crmprojectbackend.common.exception.web.GeneralAPIException;
import fqw.crmprojectbackend.individual.adapter.in.web.exception.IndividualDuplicateEmailException;
import fqw.crmprojectbackend.individual.adapter.in.web.mapper.IndividualWebMapper;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualAddRequest;
import fqw.crmprojectbackend.individual.adapter.in.web.response.IndividualDTO;
import fqw.crmprojectbackend.individual.application.command.IndividualSelectByIDCommand;
import fqw.crmprojectbackend.individual.application.port.in.IndividualAddUseCase;
import fqw.crmprojectbackend.individual.application.port.in.IndividualSelectUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "individuals")
public class IndividualController {
    private final IndividualAddUseCase individualAddUseCase;
    private final IndividualSelectUseCase individualSelectUseCase;

    @GetMapping(path = "{id}")
    public ResponseEntity<IndividualDTO> getIndividual(
            @PathVariable @Valid @NotNull UUID id) {
        var individual = individualSelectUseCase
                .findById(new IndividualSelectByIDCommand(id))
                .map(IndividualWebMapper::toDTO);

        if (individual.isEmpty())
            throw new GeneralAPIException(
                    APIErrorCode.RESOURCE_NOT_FOUND,
                    String.format("Ресурс с id '%s' не найден", id));

        return ResponseEntity.status(HttpStatus.OK).body(individual.get());

    }

    @PostMapping
    public ResponseEntity<UUID> addIndividual(
            @RequestBody @Valid IndividualAddRequest body)
            throws IndividualDuplicateEmailException {

        var command = IndividualWebMapper.toApplicationModel(body);
        UUID id = this.individualAddUseCase.addIndividual(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
