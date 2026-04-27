package fqw.crmprojectbackend.company.adapter.in.web;

import fqw.crmprojectbackend.company.adapter.in.web.mapper.CompanyWebMapper;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyAddDTO;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyQueryDTO;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyUpdateDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyPageDTO;
import fqw.crmprojectbackend.company.application.port.in.CompanyAddUseCase;
import fqw.crmprojectbackend.company.application.port.in.CompanyQueryUseCase;
import fqw.crmprojectbackend.company.application.port.in.CompanyUpdateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyAddUseCase companyAddUseCase;
    private final CompanyQueryUseCase companyQueryUseCase;
    private final CompanyUpdateUseCase companyUpdateUseCase;

    @PostMapping(path = "create")
    public ResponseEntity<UUID> addCompany(@RequestBody @Valid CompanyAddDTO body) {
        var command = CompanyWebMapper.toCommand(body);
        var id = this.companyAddUseCase.add(command);

        return ResponseEntity.status(HttpStatus.OK).body(id.getValue());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CompanyDTO> getCompanyByID(@PathVariable @Valid UUID id) {
        var company = this.companyQueryUseCase.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @PostMapping(path = "query")
    public ResponseEntity<CompanyPageDTO> getCompaniesByParams(
            @RequestBody @Valid CompanyQueryDTO body) {
        var params = CompanyWebMapper.toQuery(body);
        var pageable = this.companyQueryUseCase.findByParams(params);

        return ResponseEntity.status(HttpStatus.OK).body(pageable);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<CompanyDTO> updateCompany(
            @PathVariable UUID id, @RequestBody @Valid CompanyUpdateDTO body) {
        var command = CompanyWebMapper.toCommand(body);
        var updated = this.companyUpdateUseCase.update(id, command);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}
