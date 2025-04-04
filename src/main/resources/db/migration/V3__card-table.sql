CREATE TABLE card(
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    id_profile BIGINT NOT NULL,
    expiry_day INTEGER NOT NULL,
    credit_limit DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    current_expenses DECIMAL(12, 2) NOT NULL 0.00,
    CONSTRAINT fk_profile FOREIGN KEY(id_profile) REFERENCES profile(id) ON DELETE CASCADE
);