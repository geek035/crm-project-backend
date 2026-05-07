package fqw.crmprojectbackend.deal.adapter.in.web;

import fqw.crmprojectbackend.common.query.BaseQueryDTO;
import fqw.crmprojectbackend.deal.adapter.in.web.mapper.DealWebMapper;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealCreateDTO;
import fqw.crmprojectbackend.deal.application.dto.DealDTO;
import fqw.crmprojectbackend.deal.application.dto.DealPageDTO;
import fqw.crmprojectbackend.deal.application.port.in.DealCreateUseCase;
import fqw.crmprojectbackend.deal.application.port.in.DealQueryUseCase;
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

    @PostMapping(path = "query")
    public ResponseEntity<DealPageDTO> findByParams(
            @RequestBody @Valid BaseQueryDTO body) {
        var params = DealWebMapper.toQuery(body);
        var pageable = this.dealQueryUseCase.findByParams(params);

        return ResponseEntity.status(HttpStatus.OK).body(pageable);
    }
}
