CREATE INDEX idx_profile_email ON profile(email);

CREATE INDEX idx_account_profile ON account(id_profile);
CREATE INDEX idx_card_profile ON card(id_profile);

CREATE INDEX idx_transaction_account ON transaction(id_account);
CREATE INDEX idx_transaction_card ON transaction(id_card);

CREATE INDEX idx_invoice_card ON invoice(id_card);