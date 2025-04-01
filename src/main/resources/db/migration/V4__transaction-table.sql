CREATE TABLE transaction(
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    id_account BIGINT,
    id_card BIGINT,
    amount DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP NOT NULL,
    description VARCHAR(40) NOT NULL,
    category VARCHAR(20) NOT NULL,
    type SMALLINT NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY(id_account) REFERENCES account(id) ON DELETE CASCADE,
    CONSTRAINT fk_card FOREIGN KEY(id_card) REFERENCES card(id) ON DELETE CASCADE
);