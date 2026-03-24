package fqw.crmprojectbackend.individual.application.response;

import java.util.List;

public record IndividualQueryResponse(
        Long total,
        List<IndividualResponse> data){}
