--liquibase formatted sql
--changeset matheus:2025-08-08
--comment: enterprise table create

CREATE TABLE enterprises(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    cnpj char(14) UNIQUE NOT NULL,
    cellphone_number VARCHAR(20) UNIQUE NOT NULL,
    enterprise_group_id BIGINT NOT NULL,
    CONSTRAINT enterprises_groups__enterprises_fk
    FOREIGN KEY (enterprise_group_id)
    REFERENCES enterprises_groups(id)
    ON DELETE CASCADE
);

--rollback DROP TABLE enterprises