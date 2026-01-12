CREATE TABLE user_credentials
(
    id       UUID PRIMARY KEY,
    email    VARCHAR(50)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_id  UUID         NOT NULL UNIQUE,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);