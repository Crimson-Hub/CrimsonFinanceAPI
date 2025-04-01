CREATE TABLE account(
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    id_profile BIGINT NOT NULL,
    initial_balance DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    current_balance DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    company VARCHAR(30) NOT NULL,
    type SMALLINT NOT NULL,
    amount_expenses INTEGER DEFAULT 0,
    amount_revenues INTEGER DEFAULT 0,
    amount_transfers INTEGER DEFAULT 0,
    home_screen BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_profile FOREIGN KEY(id_profile) REFERENCES profile(id) ON DELETE CASCADE
);