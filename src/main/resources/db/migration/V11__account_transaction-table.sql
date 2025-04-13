CREATE TABLE account_transaction (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    profile_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    amount DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    transaction_type_id SMALLINT NOT NULL,
    description VARCHAR(40) NOT NULL,
    transaction_date DATE NOT NULL,
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_profile FOREIGN KEY(profile_id) REFERENCES profile(id) ON DELETE CASCADE,
    CONSTRAINT fk_account FOREIGN KEY(account_id) REFERENCES account(id) ON DELETE CASCADE,
    CONSTRAINT fk_type FOREIGN KEY(transaction_type_id) REFERENCES transaction_type(id) ON UPDATE SET NULL,
    CONSTRAINT fk_category FOREIGN KEY(category_id) REFERENCES category(id) ON UPDATE SET NULL
);