package fqw.crmprojectbackend.company.adapter.out.persistence.entity.company;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.address.RegisteredAddressJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactJPAEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class CompanyJPAEntity {

    @Id
    private UUID id;

    @Column(name = "official_name", nullable = false)
    private String officialName;

    @Column(name = "commercial_name", nullable = false)
    private String commercialName;

    @Column(name = "inn", length = 10, nullable = false, unique = true)
    private String inn;

    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "company_segment_code",
            referencedColumnName = "code",
            nullable = false)
    private CompanyClientSegmentJPAEntity clientSegment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "company_lifecycle_status_code",
            referencedColumnName = "code",
            nullable = false)
    private CompanyLifecycleStatusJPAEntity lifecycleStatus;

    @OneToOne(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            optional = false)
    private RegisteredAddressJPAEntity registeredAddress;

    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CompanyContactJPAEntity> contacts = new ArrayList<>();

    public void setRegisteredAddress(RegisteredAddressJPAEntity registeredAddress) {
        this.registeredAddress = registeredAddress;

        if (registeredAddress != null) {
            registeredAddress.setCompany(this);
        }
    }
}
