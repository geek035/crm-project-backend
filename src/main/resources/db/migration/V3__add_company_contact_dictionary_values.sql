INSERT INTO company_contact_roles (code, description)
VALUES ('OWNER', 'Владелец'),
       ('CEO', 'Генеральный директов'),
       ('FINANCE_DIRECTOR', 'Финансовый директор'),
       ('ACCOUNTANT', 'Бухгалтер'),
       ('TREASURER', 'Казначей'),
       ('OPERATIONS_MANAGER', 'Операционный менеджер'),
       ('AUTHORIZED_REPRESENTATIVE', 'Уполномоченный представитель');

INSERT INTO company_contact_statuses (code, description)
VALUES ('ACTIVE', 'Активный'),
       ('INACTIVE', 'Неактивный'),
       ('DISMISSED', 'Уволен'),
       ('TEMPORARY_UNAVAILABLE', 'Временно не доступен');