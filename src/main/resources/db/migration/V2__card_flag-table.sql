CREATE TABLE card_flag (
    id SMALLINT UNIQUE PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

INSERT INTO card_flag VALUES
(1, 'Visa'),
(2, 'Mastercard'),
(3, 'Amex'),
(4, 'Discover'),
(5, 'Diners'),
(6, 'Union Pay'),
(7, 'JCB'),
(8, 'ELO'),
(9, 'Hipercard'),
(10, 'Cabal'),
(11, 'Sorocred'),
(12, 'BaneseCard'),
(13, 'Outros');