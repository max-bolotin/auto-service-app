--liquibase formatted sql
--changeset <postgres>:<create-car-table-id>
CREATE TABLE IF NOT EXISTS public.car
(
    id bigint NOT NULL,
    manufacturer character varying(256) NOT NULL,
    model character varying(256) NOT NULL,
    registrationNumber character varying(256) NOT NULL,
    makeYear bigint NOT NULL,
    owner_id bigint NULL,
    CONSTRAINT car_pk PRIMARY KEY (id)
);

--rollback DROP TABLE car