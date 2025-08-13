--liquibase formatted sql
--changeset matheus:2025-08-08
--comment: sheets table create

CREATE TABLE SHEETS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    monthDate DATE NOT NULL,
    sum_subtotal INT NOT NULL,
    sum_taxes INT NOT NULL,
    final_transfer INT NOT NULL,
    file LONGBLOB NOT NULL,
    enterprise_id BIGINT NOT NULL,
    CONSTRAINT sheets__enterprise_fk FOREIGN KEY (enterprise_id)
    REFERENCES ENTERPRISES(id)
    ON DELETE CASCADE
);

--rollback DROP TABLE SHEETS