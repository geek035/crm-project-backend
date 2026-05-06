INSERT INTO deal_client_types (code, description)
VALUES ('INDIVIDUAL', 'Физическое лицо'),
       ('COMPANY', 'Компания');

INSERT INTO deal_products (code, description)
VALUES ('CURRENT_ACCOUNT', 'Расчетный счет'),
       ('LOAN', 'Кредит'),
       ('DEPOSIT', 'Депозит'),
       ('SALARY_PROJECT', 'Зарплатный проект'),
       ('ACQUIRING', 'Эквайринг'),
       ('BANK_GUARANTEE', 'Банковская гарантия'),
       ('LEASING', 'Лизинг');

INSERT INTO deal_currencies (code, description)
VALUES ('RUB', 'Российский рубль'),
       ('USD', 'Доллар США'),
       ('EUR', 'Евро'),
       ('CNY', 'Китайский юань');

INSERT INTO deal_stages (code, description)
VALUES ('LEAD', 'Первичный интерес'),
       ('QUALIFICATION', 'Квалификация'),
       ('OFFER_PREPARATION', 'Подготовка предложения'),
       ('CLIENT_APPROVAL', 'Согласование с клиентом'),
       ('BANK_REVIEW', 'Проверка банком'),
       ('DOCUMENT_SIGNING', 'Подписание документов'),
       ('WON', 'Успешно закрыта'),
       ('LOST', 'Проиграна'),
       ('CANCELLED', 'Отменена');

INSERT INTO deal_statuses (code, description)
VALUES ('OPEN', 'Открыта'),
       ('SUCCESS', 'Успешна'),
       ('FAILED', 'Неуспешна'),
       ('CANCELLED', 'Отменена');

INSERT INTO deal_priorities (code, description)
VALUES ('LOW', 'Низкий'),
       ('NORMAL', 'Обычный'),
       ('HIGH', 'Высокий'),
       ('URGENT', 'Срочный');

INSERT INTO deal_sources (code, description)
VALUES ('WEBSITE', 'Сайт'),
       ('BRANCH', 'Отделение'),
       ('CALL_CENTER', 'Контакт-центр'),
       ('MANAGER_OUTBOUND', 'Исходящее обращение менеджера'),
       ('PARTNER', 'Партнер'),
       ('REFERRAL', 'Рекомендация'),
       ('REPEAT_CLIENT', 'Повторное обращение клиента');

INSERT INTO deal_loss_reasons (code, description)
VALUES ('CLIENT_REFUSED', 'Клиент отказался'),
       ('BANK_REJECTED', 'Банк отказал'),
       ('CONDITIONS_NOT_ACCEPTED', 'Условия не согласованы'),
       ('COMPETITOR', 'Выбран конкурент'),
       ('DUPLICATE', 'Дубль сделки'),
       ('CANCELLED_BY_CLIENT', 'Отменена клиентом'),
       ('OTHER', 'Другая причина');
