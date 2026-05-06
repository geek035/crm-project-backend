ALTER TABLE company_contacts
    ADD CONSTRAINT fk_company_contacts_individual_id
        FOREIGN KEY (individual_id)
            REFERENCES individuals(id);