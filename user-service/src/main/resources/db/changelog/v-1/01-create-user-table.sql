CREATE TABLE users
(
    id            UUID PRIMARY KEY,
    first_name    VARCHAR(50) NOT NULL,
    last_name     VARCHAR(50) NOT NULL,
    middle_name   VARCHAR(50),
    birthday      DATE        NOT NULL,
    phone         VARCHAR(10) NOT NULL,
    avatar_s3_key VARCHAR(255)
);