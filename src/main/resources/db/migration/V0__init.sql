CREATE TABLE individuals
(
    id           UUID         NOT NULL,
    birth_date   date         NOT NULL,
    email        VARCHAR(255) NOT NULL,
    first_name   VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    second_name  VARCHAR(255) NOT NULL,
    surname      VARCHAR(255),
    CONSTRAINT individuals_pkey PRIMARY KEY (id)
);

CREATE TABLE company_contacts
(
    id                        UUID NOT NULL,
    individual_id             UUID NOT NULL,
    company_id                UUID NOT NULL,
    company_conctact_role_id  UUID NOT NULL,
    company_contact_status_id UUID NOT NULL,
    CONSTRAINT company_contacts_pkey PRIMARY KEY (id)
);

CREATE TABLE companies
(
    id                            UUID         NOT NULL,
    commercial_name               VARCHAR(255) NOT NULL,
    inn                           VARCHAR(10)  NOT NULL,
    kpp                           VARCHAR(9)   NOT NULL,
    official_name                 VARCHAR(255) NOT NULL,
    company_segment_code          VARCHAR(255) NOT NULL,
    company_lifecycle_status_code VARCHAR(255) NOT NULL,
    CONSTRAINT companies_pkey PRIMARY KEY (id)
);

ALTER TABLE individuals
    ADD CONSTRAINT uk84edapweiln9xot620ehbntyh UNIQUE (email);

ALTER TABLE company_contacts
    ADD CONSTRAINT uk9pq3npjxtkbvdam4mdifbvo5b UNIQUE (individual_id);

ALTER TABLE companies
    ADD CONSTRAINT ukikuvd8d97wjjvpvyn5y525g6r UNIQUE (inn);

CREATE TABLE company_client_segments
(
    code        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT company_client_segments_pkey PRIMARY KEY (code)
);

CREATE TABLE company_contact_roles
(
    id          UUID         NOT NULL,
    code        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT company_contact_roles_pkey PRIMARY KEY (id)
);

CREATE TABLE company_contact_statuses
(
    id          UUID         NOT NULL,
    code        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT company_contact_statuses_pkey PRIMARY KEY (id)
);

CREATE TABLE company_lifecycle_statuses
(
    code        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT company_lifecycle_statuses_pkey PRIMARY KEY (code)
);

CREATE TABLE registered_addresses
(
    id          UUID         NOT NULL,
    building    VARCHAR(255) NOT NULL,
    city        VARCHAR(255) NOT NULL,
    country     VARCHAR(255) NOT NULL,
    office      VARCHAR(255),
    postal_code VARCHAR(255) NOT NULL,
    region      VARCHAR(255) NOT NULL,
    street      VARCHAR(255) NOT NULL,
    CONSTRAINT registered_addresses_pkey PRIMARY KEY (id)
);

ALTER TABLE company_contacts
    ADD CONSTRAINT fk5a8peljwaldko3px1k3dxj5v4 FOREIGN KEY (company_id) REFERENCES companies (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE registered_addresses
    ADD CONSTRAINT fkfkifsmu3aue73hdgkr4g0wnt1 FOREIGN KEY (id) REFERENCES companies (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE company_contacts
    ADD CONSTRAINT fkh6s1ufc08c9b55topjse62v2d FOREIGN KEY (company_contact_status_id) REFERENCES company_contact_statuses (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE companies
    ADD CONSTRAINT fko210xax0mfa2ubx4bq2ymdavv FOREIGN KEY (company_segment_code) REFERENCES company_client_segments (code) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE companies
    ADD CONSTRAINT fkqfakocsqtlpvp955kfgpudgl9 FOREIGN KEY (company_lifecycle_status_code) REFERENCES company_lifecycle_statuses (code) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE company_contacts
    ADD CONSTRAINT fkr6fp6hexuuj7glmejiikvygl3 FOREIGN KEY (company_conctact_role_id) REFERENCES company_contact_roles (id) ON UPDATE NO ACTION ON DELETE NO ACTION;