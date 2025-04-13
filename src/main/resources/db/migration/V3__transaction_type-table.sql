CREATE TABLE transaction_type (
    id SMALLINT UNIQUE PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

INSERT INTO transaction_type VALUES
(1, 'Receita'),
(2, 'Despesa'),
(3, 'Transferência'),
(4, 'Ajuste'),
(5, 'Estorno'),
(6, 'Pagamento');