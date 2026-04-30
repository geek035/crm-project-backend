package fqw.crmprojectbackend.company.adapter.in.web;

import fqw.crmprojectbackend.company.adapter.in.web.mapper.CompanyContactWebMapper;
import fqw.crmprojectbackend.company.adapter.in.web.request.contact.CompanyContactAddDTO;
import fqw.crmprojectbackend.company.application.port.in.CompanyContactAddUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "companies/{id}/contacts")
@RequiredArgsConstructor
public class CompanyContactController {
    private final CompanyContactAddUseCase contactAddUseCase;

    @PostMapping()
    public ResponseEntity<UUID> addContact(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid CompanyContactAddDTO body) {
        var command = CompanyContactWebMapper.toCommand(body);
        var contactID = this.contactAddUseCase.addContact(id, command);

        return ResponseEntity.status(HttpStatus.OK).body(contactID);
    }
}
