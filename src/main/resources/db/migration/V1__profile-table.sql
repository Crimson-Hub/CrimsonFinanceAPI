CREATE TABLE profile (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    role SMALLINT NOT NULL,
    full_name VARCHAR(60) NOT NULL,
    preferred_name VARCHAR(60),
    birthday DATE,
    phone VARCHAR(13),
    nationality VARCHAR(12),
    identification_number VARCHAR(11) UNIQUE NOT NULL,
    cep VARCHAR(8),
    created_at TIMESTAMP NOT NULL
);