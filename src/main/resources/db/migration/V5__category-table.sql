CREATE TABLE category (
    id SERIAL UNIQUE PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    color_id SMALLINT NOT NULL,
    CONSTRAINT fk_color FOREIGN KEY(color_id) REFERENCES colors(id) ON DELETE CASCADE
);
