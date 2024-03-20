CREATE TABLE IF NOT EXISTS users
(
    user_id      VARCHAR(36) PRIMARY KEY,
    nickname     VARCHAR(255) NOT NULL,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL
);
