DROP TABLE IF EXISTS ACCOUNT CASCADE;
DROP TABLE IF EXISTS CARD CASCADE;
DROP TABLE IF EXISTS CLIENT CASCADE;

CREATE TABLE  CLIENT(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    EMAIL      VARCHAR(255),
    FIRST_NAME VARCHAR(255),
    LAST_NAME  VARCHAR(255)
);

CREATE TABLE  ACCOUNT(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BALANCE   FLOAT,
    NUMBER    VARCHAR(255),
    CLIENT_ID BIGINT,
    CONSTRAINT FK_ACCOUNT_CLIENT_ID FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT (ID)
);

CREATE TABLE  CARD(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CVV        VARCHAR(255),
    NUMBER     VARCHAR(255),
    PIN        VARCHAR(255),
    ACCOUNT_ID BIGINT,
    CONSTRAINT FK_CARD_ACCOUNT_ID FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT (ID)
);

CREATE INDEX INDEX_ACCOUNT_CLIENT_ID ON ACCOUNT(CLIENT_ID);
CREATE INDEX INDEX_CARD_ACCOUNT_ID ON CARD(ACCOUNT_ID);