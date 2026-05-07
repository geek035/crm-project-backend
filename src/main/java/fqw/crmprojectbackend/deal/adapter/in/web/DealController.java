package fqw.crmprojectbackend.deal.adapter.in.web;

import fqw.crmprojectbackend.common.query.BaseQueryDTO;
import fqw.crmprojectbackend.deal.adapter.in.web.mapper.DealWebMapper;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealChangeStageDTO;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealChangeStatusDTO;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealCreateDTO;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealUpdateDTO;
import fqw.crmprojectbackend.deal.application.dto.DealDTO;
import fqw.crmprojectbackend.deal.application.dto.DealPageDTO;
import fqw.crmprojectbackend.deal.application.port.in.DealCreateUseCase;
import fqw.crmprojectbackend.deal.application.port.in.DealQueryUseCase;
import fqw.crmprojectbackend.deal.application.port.in.DealUpdateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "deals")
@RequiredArgsConstructor
public class DealController {
    private final DealCreateUseCase dealCreateUseCase;
    private final DealQueryUseCase dealQueryUseCase;
    private final DealUpdateUseCase dealUpdateUseCase;

    @PostMapping(path = "create")
    public ResponseEntity<UUID> createDeal(
            @RequestBody @Valid DealCreateDTO body) {
        var command = DealWebMapper.toCommand(body);
        var id = this.dealCreateUseCase.create(command);

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<DealDTO> findByID(
            @PathVariable @Valid UUID id) {
        var deal = this.dealQueryUseCase.findByID(id);

        return ResponseEntity.status(HttpStatus.OK).body(deal);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<DealDTO> update(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid DealUpdateDTO body) {
        var command = DealWebMapper.toCommand(body);
        var updated = this.dealUpdateUseCase.update(id, command);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PostMapping(path = "{id}/stage")
    public ResponseEntity<DealDTO> changeStage(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid DealChangeStageDTO body) {
        var command = DealWebMapper.toCommand(body);
        var updated = this.dealUpdateUseCase.changeStage(id, command);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PostMapping(path = "{id}/status")
    public ResponseEntity<DealDTO> changeStatus(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid DealChangeStatusDTO body) {
        var command = DealWebMapper.toCommand(body);
        var updated = this.dealUpdateUseCase.changeStatus(id, command);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PostMapping(path = "query")
    public ResponseEntity<DealPageDTO> findByParams(
            @RequestBody @Valid BaseQueryDTO body) {
        var params = DealWebMapper.toQuery(body);
        var pageable = this.dealQueryUseCase.findByParams(params);

        return ResponseEntity.status(HttpStatus.OK).body(pageable);
    }

    @PostMapping(path = "individual/{id}/query")
    public ResponseEntity<DealPageDTO> findByIndividualID(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid BaseQueryDTO body) {
        var params = DealWebMapper.toQuery(body);
        var pageable = this.dealQueryUseCase.findByIndividualID(id, params);

        return ResponseEntity.status(HttpStatus.OK).body(pageable);
    }

    @PostMapping(path = "company/{id}/query")
    public ResponseEntity<DealPageDTO> findByCompanyID(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid BaseQueryDTO body) {
        var params = DealWebMapper.toQuery(body);
        var pageable = this.dealQueryUseCase.findByCompanyID(id, params);

        return ResponseEntity.status(HttpStatus.OK).body(pageable);
    }
}
