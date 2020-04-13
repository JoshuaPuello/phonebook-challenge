DROP TABLE IF EXISTS contact;

CREATE TABLE contact
(
    id           VARCHAR(255) NOT NULL
        PRIMARY KEY,
    address      VARCHAR(255),
    email        VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    phone_number VARCHAR(255)
);

INSERT INTO CONTACT (id, first_name, last_name, email, address, phone_number) VALUES ('f8423fb5', 'April', 'Lydiatt', 'alydiatt0@hao123.com', '89819 Blaine Terrace', '421-588-0392');
INSERT INTO CONTACT (id, first_name, last_name, email, address, phone_number) VALUES ('81e29d93', 'Anallese', 'Onslow', 'aonslow1@discovery.com', '944 Sundown Point', '479-358-9170');
INSERT INTO CONTACT (id, first_name, last_name, email, address, phone_number) VALUES ('040420de', 'Wallie', 'Gaenor', 'wgaenor2@home.pl', '0 Scott Terrace', '369-860-5501');
INSERT INTO CONTACT (id, first_name, last_name, email, address, phone_number) VALUES ('db285ab4', 'Teresina', 'Litchfield', 'tlitchfield3@bloglines.com', '12 Troy Point', '479-684-0092');
INSERT INTO CONTACT (id, first_name, last_name, email, address, phone_number) VALUES ('1552964a', 'Giulio', 'Onslow', 'gcarrane4@nature.com', '728 Comanche Trail', '529-725-3226');