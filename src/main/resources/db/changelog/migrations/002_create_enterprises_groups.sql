--liquibase formatted sql
--changeset matheus: 2025-08-08
--comment: enterprises_groups table create

CREATE TABLE ENTERPRISES_GROUPS(

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL

);

--rollback DROP TABLE ENTERPRISES_GROUPS;