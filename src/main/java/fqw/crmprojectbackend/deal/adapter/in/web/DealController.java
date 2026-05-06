package fqw.crmprojectbackend.deal.adapter.in.web;

import fqw.crmprojectbackend.deal.adapter.in.web.mapper.DealWebMapper;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealCreateDTO;
import fqw.crmprojectbackend.deal.application.port.in.DealCreateUseCase;
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
@RequestMapping(path = "deals")
@RequiredArgsConstructor
public class DealController {
    private final DealCreateUseCase dealCreateUseCase;

    @PostMapping(path = "create")
    public ResponseEntity<UUID> createDeal(
            @RequestBody @Valid DealCreateDTO body) {
        var command = DealWebMapper.toCommand(body);
        var id = this.dealCreateUseCase.create(command);

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
