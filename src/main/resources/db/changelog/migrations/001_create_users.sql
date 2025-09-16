--liquibase formatted sql
--changeset matheus:2025-08-08
--comment: users table create

CREATE TABLE users(

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    hashed_password VARCHAR(255) NOT NULL

);

--rollback DROP TABLE users