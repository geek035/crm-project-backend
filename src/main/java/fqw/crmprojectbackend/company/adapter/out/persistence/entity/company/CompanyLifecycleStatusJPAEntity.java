package fqw.crmprojectbackend.company.adapter.out.persistence.entity.company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_lifecycle_statuses")
public class CompanyLifecycleStatusJPAEntity {

    @Id
    private String code;

    @Column(name = "description", nullable = false)
    private String description;
}
