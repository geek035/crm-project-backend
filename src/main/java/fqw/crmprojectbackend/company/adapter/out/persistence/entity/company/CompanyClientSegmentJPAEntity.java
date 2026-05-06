package fqw.crmprojectbackend.company.adapter.out.persistence.entity.company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_client_segments")
public class CompanyClientSegmentJPAEntity {

    @Id
    private String code;

    @Column(name = "description", nullable = false)
    private String description;
}
