--liquibase formatted sql
--changeset matheus:2025-08-08
--comment: enterprises_groups table create

CREATE TABLE enterprises_groups(

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    user_id BIGINT NOT NULL,
        CONSTRAINT users__enterprises_groups_fk
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE

);

--rollback DROP TABLE enterprises_groups;