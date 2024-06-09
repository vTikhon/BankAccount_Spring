BEGIN;

DROP TABLE IF EXISTS card, account, password, client CASCADE;

CREATE TABLE client (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    surname VARCHAR NOT NULL,
    date_of_birth DATE NOT NULL,
    passport CHAR(10) UNIQUE NOT NULL
);

CREATE TABLE password
(
    id BIGSERIAL PRIMARY KEY,
    client_password VARCHAR NOT NULL,
    client_id INTEGER NOT NULL REFERENCES client (id)
);

CREATE TABLE account (
    id BIGSERIAL PRIMARY KEY,
    account_number CHAR(20) UNIQUE NOT NULL,
    account_type VARCHAR NOT NULL,
    account_balance NUMERIC(15, 2) NOT null,
    client_id INTEGER REFERENCES client(id) 
);

CREATE TABLE card (
    id BIGSERIAL PRIMARY KEY,
    card_number CHAR(20) UNIQUE NOT NULL,
    card_balance NUMERIC(15, 2) NOT NULL,
    payment_system VARCHAR NOT NULL,
    card_status VARCHAR NOT NULL,
    account_id INTEGER REFERENCES account(id)
);


INSERT INTO client (first_name, surname, date_of_birth, passport)
VALUES ('Ivan', 'Ivanov', '1980-01-01', '1234567890'),
       ('Petr', 'Petrov', '1985-05-12', '1234567891'),
       ('Svetlana', 'Sidorova', '1990-07-23', '1234567892'),
       ('Anna', 'Kuznetsova', '1992-02-02', '1234567893'),
       ('Sergey', 'Smirnov', '1988-08-30', '1234567894'),
       ('Olga', 'Popova', '1995-12-15', '1234567895'),
       ('Nikolay', 'Lebedev', '1979-09-21', '1234567896'),
       ('Marina', 'Kovaleva', '1983-11-11', '1234567897'),
       ('Vladimir', 'Semenov', '1975-03-05', '1234567898'),
       ('Tatiana', 'Mikhailova', '1998-06-18', '1234567899'),
       ('Alexey', 'Zaytsev', '1981-04-14', '1234567800'),
       ('Elena', 'Vorobyova', '1993-03-19', '1234567801'),
       ('Andrey', 'Belov', '1987-07-29', '1234567802'),
       ('Irina', 'Pavlova', '1994-08-25', '1234567803'),
       ('Dmitry', 'Volkov', '1991-09-13', '1234567804'),
       ('Ekaterina', 'Fedorova', '1982-12-22', '1234567805'),
       ('Mikhail', 'Gusev', '1984-11-30', '1234567806'),
       ('Natalia', 'Morozova', '1996-10-10', '1234567807'),
       ('Oleg', 'Titov', '1989-06-16', '1234567808'),
       ('Alina', 'Polyakova', '1997-05-07', '1234567809'),
       ('Kirill', 'Vinogradov', '1986-02-24', '1234567810'),
       ('Yulia', 'Karpova', '1992-11-03', '1234567811'),
       ('Roman', 'Melnikov', '1985-04-09', '1234567812'),
       ('Sofia', 'Sorokina', '1990-08-18', '1234567813'),
       ('Viktor', 'Gerasimov', '1983-12-31', '1234567814'),
       ('Maria', 'Nikiforova', '1988-01-15', '1234567815'),
       ('Anton', 'Trifonov', '1991-02-08', '1234567816'),
       ('Lidiya', 'Zhukova', '1994-03-12', '1234567817'),
       ('Gennady', 'Osipov', '1987-04-23', '1234567818'),
       ('Olga', 'Pankratova', '1995-05-31', '1234567819');

INSERT INTO password (client_password, client_id)
VALUES ('password123', 1),
       ('mypassword', 2),
       ('passw0rd', 3),
       ('securepass', 4),
       ('letmein', 5),
       ('qwerty', 6),
       ('123456', 7),
       ('password1', 8),
       ('admin', 9),
       ('userpass', 10),
       ('examplepass', 11),
       ('strongpassword', 12),
       ('mypassword1', 13),
       ('easy2remember', 14),
       ('mysecurepass', 15),
       ('topsecret', 16),
       ('1234abcd', 17),
       ('abcd1234', 18),
       ('password321', 19),
       ('mypass123', 20),
       ('letmein123', 21),
       ('qwerty123', 22),
       ('pass123word', 23),
       ('passwordpass', 24),
       ('secure123pass', 25),
       ('easy1234pass', 26),
       ('rememberme', 27),
       ('topsecret123', 28),
       ('123password', 29),
       ('password456', 30);

INSERT INTO account (account_number, account_type, account_balance, client_id)
VALUES ('12345678912345678911', 'DEPOSIT', 100000, 1),
       ('12345678912345678912', 'DEBIT', 200000, 1),
       ('12345678912345678913', 'CREDIT', 300000, 2),
       ('12345678912345678914', 'DEBIT', 150000, 2),
       ('12345678912345678915', 'DEBIT', 50000, 3),
       ('12345678912345678916', 'DEBIT', 70000, 4),
       ('12345678912345678917', 'CREDIT', 120000, 5),
       ('12345678912345678918', 'DEPOSIT', 40000, 6),
       ('12345678912345678919', 'DEBIT', 250000, 7),
       ('12345678912345678920', 'DEBIT', 350000, 8),
       ('22345678912345678911', 'DEPOSIT', 110000, 9),
       ('22345678912345678912', 'DEBIT', 210000, 10),
       ('22345678912345678913', 'CREDIT', 310000, 11),
       ('22345678912345678914', 'DEBIT', 160000, 12),
       ('22345678912345678915', 'DEBIT', 51000, 13),
       ('22345678912345678916', 'DEBIT', 71000, 14),
       ('22345678912345678917', 'CREDIT', 121000, 15),
       ('22345678912345678918', 'DEPOSIT', 41000, 16),
       ('22345678912345678919', 'DEBIT', 251000, 17),
       ('22345678912345678920', 'DEBIT', 351000, 18),
       ('32345678912345678911', 'DEPOSIT', 120000, 19),
       ('32345678912345678912', 'DEBIT', 220000, 20),
       ('32345678912345678913', 'CREDIT', 320000, 21),
       ('32345678912345678914', 'DEBIT', 170000, 22),
       ('32345678912345678915', 'DEBIT', 52000, 23),
       ('32345678912345678916', 'DEBIT', 72000, 24),
       ('32345678912345678917', 'CREDIT', 122000, 25),
       ('32345678912345678918', 'DEPOSIT', 42000, 26),
       ('32345678912345678919', 'DEBIT', 252000, 27),
       ('32345678912345678920', 'DEBIT', 352000, 28);

INSERT INTO card (card_number, card_balance, payment_system, card_status, account_id)
VALUES ('12345678912345678921', 200000, 'VISA', 'ACTIVE', 2),
       ('12345678912345678922', 300000, 'MASTERCARD', 'ACTIVE', 3),
       ('12345678912345678923', 150000, 'MIR', 'ACTIVE', 4),
       ('12345678912345678924', 50000, 'UNION', 'ACTIVE', 5),
       ('12345678912345678925', 70000, 'VISA', 'CLOSED', 6),
       ('12345678912345678926', 120000, 'MASTERCARD', 'BLOCKED', 7),
       ('12345678912345678927', 250000, 'MIR', 'ACTIVE', 9),
       ('12345678912345678928', 350000, 'UNION', 'CLOSED', 10),
       ('12345678912345678929', 200000, 'VISA', 'ACTIVE', 2),
       ('12345678912345678930', 150000, 'MASTERCARD', 'ACTIVE', 4),
       ('22345678912345678921', 210000, 'VISA', 'ACTIVE', 12),
       ('22345678912345678922', 310000, 'MASTERCARD', 'ACTIVE', 13),
       ('22345678912345678923', 160000, 'MIR', 'ACTIVE', 14),
       ('22345678912345678924', 51000, 'UNION', 'ACTIVE', 15),
       ('22345678912345678925', 71000, 'VISA', 'CLOSED', 16),
       ('22345678912345678926', 121000, 'MASTERCARD', 'BLOCKED', 17),
       ('22345678912345678927', 251000, 'MIR', 'ACTIVE', 19),
       ('22345678912345678928', 351000, 'UNION', 'CLOSED', 20),
       ('22345678912345678929', 210000, 'VISA', 'ACTIVE', 12),
       ('22345678912345678930', 160000, 'MASTERCARD', 'ACTIVE', 14),
       ('32345678912345678921', 220000, 'VISA', 'ACTIVE', 22),
       ('32345678912345678922', 320000, 'MASTERCARD', 'ACTIVE', 23),
       ('32345678912345678923', 170000, 'MIR', 'ACTIVE', 24),
       ('32345678912345678924', 52000, 'UNION', 'ACTIVE', 25),
       ('32345678912345678925', 72000, 'VISA', 'CLOSED', 26),
       ('32345678912345678926', 122000, 'MASTERCARD', 'BLOCKED', 27),
       ('32345678912345678927', 252000, 'MIR', 'ACTIVE', 22),
       ('32345678912345678928', 352000, 'UNION', 'CLOSED', 23),
       ('32345678912345678929', 220000, 'VISA', 'ACTIVE', 24),
       ('32345678912345678930', 170000, 'MASTERCARD', 'ACTIVE', 25);


COMMIT;
