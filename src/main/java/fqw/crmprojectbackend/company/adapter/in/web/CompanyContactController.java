package fqw.crmprojectbackend.company.adapter.in.web;

import fqw.crmprojectbackend.common.query.BaseQueryDTO;
import fqw.crmprojectbackend.common.query.BaseQueryMapper;
import fqw.crmprojectbackend.company.adapter.in.web.mapper.CompanyContactWebMapper;
import fqw.crmprojectbackend.company.adapter.in.web.request.contact.CompanyContactAddDTO;
import fqw.crmprojectbackend.company.adapter.in.web.request.contact.CompanyContactUpdateRoleDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyContactPageDTO;
import fqw.crmprojectbackend.company.application.port.in.CompanyContactAddUseCase;
import fqw.crmprojectbackend.company.application.port.in.CompanyContactDeleteUseCase;
import fqw.crmprojectbackend.company.application.port.in.CompanyContactQueryUseCase;
import fqw.crmprojectbackend.company.application.port.in.CompanyContactUpdateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "companies/{companyID}/contacts")
@RequiredArgsConstructor
public class CompanyContactController {
    private final CompanyContactAddUseCase contactAddUseCase;
    private final CompanyContactQueryUseCase contactQueryUseCase;
    private final CompanyContactUpdateUseCase contactUpdateUseCase;
    private final CompanyContactDeleteUseCase contactDeleteUseCase;


    @PostMapping()
    public ResponseEntity<UUID> addContact(
            @PathVariable @Valid UUID companyID,
            @RequestBody @Valid CompanyContactAddDTO body) {
        var command = CompanyContactWebMapper.toCommand(body);
        var contactID = this.contactAddUseCase.addContact(companyID, command);

        return ResponseEntity.status(HttpStatus.OK).body(contactID);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable @Valid UUID id) {
        this.contactDeleteUseCase.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "query")
    public ResponseEntity<CompanyContactPageDTO> queryContacts(
            @PathVariable @Valid UUID companyID,
            @RequestBody @Valid BaseQueryDTO body) {
        var params = BaseQueryMapper.toParams(body);
        var pageable = this.contactQueryUseCase.findByParams(companyID, params);

        return ResponseEntity.status(HttpStatus.OK).body(pageable);
    }

    @PostMapping(path = "{id}/role")
    public ResponseEntity<CompanyContactDTO> updateRole(
            @PathVariable("id") @Valid UUID id,
            @RequestBody @Valid CompanyContactUpdateRoleDTO body) {
        var command = CompanyContactWebMapper.toCommand(body);
        var updated = this.contactUpdateUseCase.updateRole(id, command);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}
