CREATE TABLE account_type (
    id SMALLINT UNIQUE PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

INSERT INTO account_type VALUES
(1, 'Conta corrente'),
(2, 'Carteira'),
(3, 'Poupança'),
(4, 'Investimentos'),
(5, 'Outros');
