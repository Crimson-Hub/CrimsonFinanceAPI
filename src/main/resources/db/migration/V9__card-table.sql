CREATE TABLE card(
    id SERIAL UNIQUE PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    credit_limit DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    current_expenses DECIMAL(12, 2) NOT NULL 0.00,
    card_flag_id SMALLINT NOT NULL,
    description VARCHAR(40) NOT NULL,
    CONSTRAINT fk_profile FOREIGN KEY(profile_id) REFERENCES profile(id) ON DELETE CASCADE,
    CONSTRAINT fk_flag FOREIGN KEY(card_flag_id) REFERENCES card_flag(id) ON UPDATE SET NULL
);