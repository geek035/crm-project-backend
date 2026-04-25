package fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyJPAEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_contacts")
public class CompanyContactJPAEntity {

    @Id
    private UUID id;

    @Column(name = "individual_id", nullable = false, unique = true)
    private UUID individualID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyJPAEntity company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_conctact_role_id", nullable = false)
    private CompanyContactRoleJPAEntity role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_contact_status_id", nullable = false)
    private CompanyContactStatusJPAEntity status;
}
