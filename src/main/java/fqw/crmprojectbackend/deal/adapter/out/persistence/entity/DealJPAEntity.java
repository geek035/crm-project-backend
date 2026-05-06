package fqw.crmprojectbackend.deal.adapter.out.persistence.entity;

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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deals")
public class DealJPAEntity {
    @Id
    private UUID id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "deal_client_type_code",
            referencedColumnName = "code",
            nullable = false)
    private DealClientTypeJPAEntity clientType;

    @Column(name = "individual_id")
    private UUID individualID;

    @Column(name = "company_id")
    private UUID companyID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "deal_product_code",
            referencedColumnName = "code",
            nullable = false)
    private DealProductJPAEntity product;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "deal_currency_code",
            referencedColumnName = "code",
            nullable = false)
    private DealCurrencyJPAEntity currency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "deal_stage_code",
            referencedColumnName = "code",
            nullable = false)
    private DealStageJPAEntity stage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "deal_status_code",
            referencedColumnName = "code",
            nullable = false)
    private DealStatusJPAEntity status;

    @Column(name = "probability", nullable = false)
    private Integer probability;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "deal_priority_code",
            referencedColumnName = "code",
            nullable = false)
    private DealPriorityJPAEntity priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "deal_source_code",
            referencedColumnName = "code",
            nullable = false)
    private DealSourceJPAEntity source;

    @Column(name = "expected_close_date")
    private LocalDate expectedCloseDate;

    @Column(name = "actual_close_date")
    private LocalDate actualCloseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "deal_loss_reason_code",
            referencedColumnName = "code")
    private DealLossReasonJPAEntity lossReason;
}
