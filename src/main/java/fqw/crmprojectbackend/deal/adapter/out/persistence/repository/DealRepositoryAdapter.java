package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.common.persistent.jpa.spectification.FilterSpecificationBuilder;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealCurrencyJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealClientTypeJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealLossReasonJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealPriorityJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealProductJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealSourceJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealStageJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealStatusJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.mapper.DealPersistenceMapper;
import fqw.crmprojectbackend.deal.application.dto.DealDTO;
import fqw.crmprojectbackend.deal.application.port.out.DealRepositoryPort;
import fqw.crmprojectbackend.deal.application.query.DealQueryParams;
import fqw.crmprojectbackend.deal.application.request.DealAddRequest;
import fqw.crmprojectbackend.deal.domain.model.deal.DealNumber;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DealRepositoryAdapter implements DealRepositoryPort {
    private final DealSpringDataRepository dealSpringDataRepository;
    private final DealClientTypeSpringDataRepository clientTypeSpringDataRepository;
    private final DealProductSpringDataRepository productSpringDataRepository;
    private final DealCurrencySpringDataRepository currencySpringDataRepository;
    private final DealStageSpringDataRepository stageSpringDataRepository;
    private final DealStatusSpringDataRepository statusSpringDataRepository;
    private final DealPrioritySpringDataRepository prioritySpringDataRepository;
    private final DealSourceSpringDataRepository sourceSpringDataRepository;
    private final DealLossReasonSpringDataRepository lossReasonSpringDataRepository;

    @Override
    public boolean existsByNumber(DealNumber number) {
        return this.dealSpringDataRepository.existsByNumber(number.value());
    }

    @Override
    public UUID add(DealAddRequest origin) {
        var deal = DealPersistenceMapper.fromRequest(origin);
        var saved = this.dealSpringDataRepository.save(deal);

        return saved.getId();
    }

    @Override
    public DealDTO updateByOrigin(DealDTO origin) {
        var dealJPA = this.dealSpringDataRepository
                .findById(origin.id())
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Сделка с идентификатором '%s' не найдена",
                        origin.id())));

        dealJPA.setNumber(origin.number());
        dealJPA.setClientType(this.getClientType(origin.clientType().code()));
        dealJPA.setIndividualID(origin.individualID());
        dealJPA.setCompanyID(origin.companyID());
        dealJPA.setTitle(origin.title());
        dealJPA.setDescription(origin.description());
        dealJPA.setProduct(this.getProduct(origin.product().code()));
        dealJPA.setAmount(origin.amount());
        dealJPA.setCurrency(this.getCurrency(origin.currency().code()));
        dealJPA.setStage(this.getStage(origin.stage().code()));
        dealJPA.setStatus(this.getStatus(origin.status().code()));
        dealJPA.setProbability(origin.probability());
        dealJPA.setPriority(this.getPriority(origin.priority().code()));
        dealJPA.setSource(this.getSource(origin.source().code()));
        dealJPA.setExpectedCloseDate(origin.expectedCloseDate());
        dealJPA.setActualCloseDate(origin.actualCloseDate());
        dealJPA.setLossReason(origin.lossReason() == null
                ? null
                : this.getLossReason(origin.lossReason().code()));

        var updated = this.dealSpringDataRepository.save(dealJPA);

        return DealPersistenceMapper.fromEntity(updated);
    }

    @Override
    public long getTotal() {
        return this.dealSpringDataRepository.count();
    }

    @Override
    public Optional<DealDTO> findByID(UUID id) {
        return this.dealSpringDataRepository
                .findById(id)
                .map(DealPersistenceMapper::fromEntity);
    }

    @Override
    public List<DealDTO> findByParams(DealQueryParams params) {
        Specification<DealJPAEntity> specification =
                new FilterSpecificationBuilder<DealJPAEntity>()
                        .with(params.filters())
                        .build();

        var orders = params.sort().stream()
                .map(it -> new Sort.Order(
                        Sort.Direction.fromString(it.direction().toString().toLowerCase()),
                        it.field()))
                .toList();

        var sort = Sort.by(orders);
        var pageRequest = PageRequest.of(params.pageNumber(), params.pageSize(), sort);
        var page = this.dealSpringDataRepository.findAll(specification, pageRequest);

        return page.stream()
                .map(DealPersistenceMapper::fromEntity)
                .toList();
    }

    @Override
    public List<DealDTO> findByCompanyID(UUID companyID, DealQueryParams params) {
        return this.findByParamsWithRequiredFilter(
                params,
                new FilterCriterion(
                        "companyID",
                        companyID,
                        FilterCriterionMatchMode.EQUALS));
    }

    @Override
    public List<DealDTO> findByIndividualID(UUID individualID, DealQueryParams params) {
        return this.findByParamsWithRequiredFilter(
                params,
                new FilterCriterion(
                        "individualID",
                        individualID,
                        FilterCriterionMatchMode.EQUALS));
    }

    private List<DealDTO> findByParamsWithRequiredFilter(
            DealQueryParams params,
            FilterCriterion requiredFilter) {
        Specification<DealJPAEntity> specification =
                new FilterSpecificationBuilder<DealJPAEntity>()
                        .with(params.filters())
                        .with(requiredFilter)
                        .build();

        var orders = params.sort().stream()
                .map(it -> new Sort.Order(
                        Sort.Direction.fromString(it.direction().toString().toLowerCase()),
                        it.field()))
                .toList();

        var sort = Sort.by(orders);
        var pageRequest = PageRequest.of(params.pageNumber(), params.pageSize(), sort);
        var page = this.dealSpringDataRepository.findAll(specification, pageRequest);

        return page.stream()
                .map(DealPersistenceMapper::fromEntity)
                .toList();
    }

    private DealClientTypeJPAEntity getClientType(String code) {
        return this.clientTypeSpringDataRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Тип клиента сделки с кодом '%s' не найден",
                        code)));
    }

    private DealProductJPAEntity getProduct(String code) {
        return this.productSpringDataRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Продукт сделки с кодом '%s' не найден",
                        code)));
    }

    private DealCurrencyJPAEntity getCurrency(String code) {
        return this.currencySpringDataRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Валюта сделки с кодом '%s' не найдена",
                        code)));
    }

    private DealStageJPAEntity getStage(String code) {
        return this.stageSpringDataRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Стадия сделки с кодом '%s' не найдена",
                        code)));
    }

    private DealStatusJPAEntity getStatus(String code) {
        return this.statusSpringDataRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Статус сделки с кодом '%s' не найден",
                        code)));
    }

    private DealPriorityJPAEntity getPriority(String code) {
        return this.prioritySpringDataRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Приоритет сделки с кодом '%s' не найден",
                        code)));
    }

    private DealSourceJPAEntity getSource(String code) {
        return this.sourceSpringDataRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Источник сделки с кодом '%s' не найден",
                        code)));
    }

    private DealLossReasonJPAEntity getLossReason(String code) {
        return this.lossReasonSpringDataRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Причина проигрыша сделки с кодом '%s' не найдена",
                        code)));
    }
}
