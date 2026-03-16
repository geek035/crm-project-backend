package fqw.crmprojectbackend.individual.adapter.in.web;

import fqw.crmprojectbackend.individual.adapter.in.web.exception.IndividualDuplicateEmailException;
import fqw.crmprojectbackend.individual.adapter.in.web.mapper.IndividualWebMapper;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualAddRequest;
import fqw.crmprojectbackend.individual.application.port.in.IndividualAddUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "individuals")
public class IndividualController {
    private final IndividualAddUseCase individualAddUseCase;

    @PostMapping
    private ResponseEntity<UUID> addIndividual(
            @RequestBody @Valid IndividualAddRequest body)
            throws IndividualDuplicateEmailException {

        var command = IndividualWebMapper.toApplicationModel(body);
        UUID id = this.individualAddUseCase.addIndividual(command);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
