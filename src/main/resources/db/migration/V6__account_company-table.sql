CREATE TABLE account_company (
    id SMALLINT UNIQUE PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

INSERT INTO account_company VALUES
(1, 'Santander'),
(2, 'Nubank'),
(3, 'Banco do Brasil'),
(4, 'Caixa'),
(5, 'Itaú'),
(6, 'Bradesco'),
(7, 'PicPay'),
(8, 'Banco Inter'),
(9, 'Outra');