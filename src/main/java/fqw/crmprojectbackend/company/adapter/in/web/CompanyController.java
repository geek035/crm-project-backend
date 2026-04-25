package fqw.crmprojectbackend.company.adapter.in.web;

import fqw.crmprojectbackend.company.adapter.in.web.mapper.CompanyWebMapper;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyAddDTO;
import fqw.crmprojectbackend.company.application.port.in.CompanyAddUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyAddUseCase companyAddUseCase;

    @PostMapping(path = "create")
    public ResponseEntity<UUID> addCompany(@RequestBody @Valid CompanyAddDTO body) {
        var command = CompanyWebMapper.toCommand(body);
        var id = this.companyAddUseCase.add(command);

        return ResponseEntity.status(HttpStatus.OK).body(id.getValue());
    }
}
