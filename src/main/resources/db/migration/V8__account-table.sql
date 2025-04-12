CREATE TABLE account(
    id SERIAL UNIQUE PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    initial_balance DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    current_balance DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    account_company_id SMALLINT NOT NULL,
    account_type_id SMALLINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_profile FOREIGN KEY(profile_id) REFERENCES profile(id) ON DELETE CASCADE,
    CONSTRAINT fk_company FOREIGN KEY(account_company_id) REFERENCES account_company(id) ON UPDATE SET NULL,
    CONSTRAINT fk_type FOREIGN KEY(account_type_id) REFERENCES account_type(id) ON UPDATE SET NULL
);