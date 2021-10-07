drop table if exists account CASCADE;
drop table if exists card CASCADE;
drop table if exists client CASCADE;

create table CLIENT(
    ID BIGINT auto_increment primary key,
    EMAIL      VARCHAR(255),
    FIRST_NAME VARCHAR(255),
    LAST_NAME  VARCHAR(255)
);

create table ACCOUNT(
    ID BIGINT auto_increment primary key,
    BALANCE   FLOAT,
    NUMBER    VARCHAR(255),
    CLIENT_ID BIGINT,
    foreign key (CLIENT_ID) references CLIENT (ID)
);

create table CARD(
    ID BIGINT auto_increment primary key,
    CVV        VARCHAR(255),
    NUMBER     VARCHAR(255),
    PIN        VARCHAR(255),
    ACCOUNT_ID BIGINT,
    foreign key (ACCOUNT_ID) references ACCOUNT (ID)
);