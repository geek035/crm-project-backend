ALTER TABLE deals
    ADD CONSTRAINT fk_deals_individual_id
        FOREIGN KEY (individual_id)
            REFERENCES individuals(id);

ALTER TABLE deals
    ADD CONSTRAINT fk_deals_company_id
        FOREIGN KEY (company_id)
            REFERENCES companies(id);

ALTER TABLE deals
    ADD CONSTRAINT chk_deals_single_client
        CHECK (
            (deal_client_type_code = 'INDIVIDUAL' AND individual_id IS NOT NULL AND company_id IS NULL)
                OR
            (deal_client_type_code = 'COMPANY' AND company_id IS NOT NULL AND individual_id IS NULL)
        );
