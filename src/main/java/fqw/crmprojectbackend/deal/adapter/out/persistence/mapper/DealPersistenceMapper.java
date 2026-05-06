package fqw.crmprojectbackend.deal.adapter.out.persistence.mapper;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.*;
import fqw.crmprojectbackend.deal.application.request.DealAddRequest;

public class DealPersistenceMapper {
    public static DealJPAEntity fromRequest(DealAddRequest request) {
        var deal = new DealJPAEntity();

        deal.setId(request.id());
        deal.setNumber(request.number());
        deal.setClientType(referenceClientType(request.clientTypeCode()));
        deal.setIndividualID(request.individualID());
        deal.setCompanyID(request.companyID());
        deal.setTitle(request.title());
        deal.setDescription(request.description());
        deal.setProduct(referenceProduct(request.productCode()));
        deal.setAmount(request.amount());
        deal.setCurrency(referenceCurrency(request.currencyCode()));
        deal.setStage(referenceStage(request.stageCode()));
        deal.setStatus(referenceStatus(request.statusCode()));
        deal.setProbability(request.probability());
        deal.setPriority(referencePriority(request.priorityCode()));
        deal.setSource(referenceSource(request.sourceCode()));
        deal.setExpectedCloseDate(request.expectedCloseDate());
        deal.setActualCloseDate(request.actualCloseDate());
        deal.setLossReason(referenceLossReason(request.lossReasonCode()));

        return deal;
    }

    private static DealClientTypeJPAEntity referenceClientType(String code) {
        var entity = new DealClientTypeJPAEntity();
        entity.setCode(code);
        return entity;
    }

    private static DealCurrencyJPAEntity referenceCurrency(String code) {
        var entity = new DealCurrencyJPAEntity();
        entity.setCode(code);
        return entity;
    }

    private static DealLossReasonJPAEntity referenceLossReason(String code) {
        if (code == null) return null;

        var entity = new DealLossReasonJPAEntity();
        entity.setCode(code);
        return entity;
    }

    private static DealPriorityJPAEntity referencePriority(String code) {
        var entity = new DealPriorityJPAEntity();
        entity.setCode(code);
        return entity;
    }

    private static DealProductJPAEntity referenceProduct(String code) {
        var entity = new DealProductJPAEntity();
        entity.setCode(code);
        return entity;
    }

    private static DealSourceJPAEntity referenceSource(String code) {
        var entity = new DealSourceJPAEntity();
        entity.setCode(code);
        return entity;
    }

    private static DealStageJPAEntity referenceStage(String code) {
        var entity = new DealStageJPAEntity();
        entity.setCode(code);
        return entity;
    }

    private static DealStatusJPAEntity referenceStatus(String code) {
        var entity = new DealStatusJPAEntity();
        entity.setCode(code);
        return entity;
    }
}
