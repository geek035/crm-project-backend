package fqw.crmprojectbackend.company.adapter.in.web.request.company;

import jakarta.validation.constraints.NotNull;

public record CompanyUpdateLifecycleDTO(
        @NotNull(message = "Код жизненнего цикла не может быть пустым")
        String lifecycleCode
) {
}
