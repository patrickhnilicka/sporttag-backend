CREATE DATABASE IF NOT EXISTS sporttag;

CREATE TABLE IF NOT EXISTS sportlehrer
(
    id       MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
    vorname  VARCHAR(255) NOT NULL,
    nachname VARCHAR(255) NOT NULL,
    kuerzel  VARCHAR(2)   NOT NULL
);
CREATE TABLE IF NOT EXISTS sporttag
(
    id          MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
    datum       DATE         NOT NULL,
    bezeichnung VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS sportklasse
(
    id             MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
    klassenname    VARCHAR(255) NOT NULL,
    sportlehrer_id MEDIUMINT,
    sporttag_id    MEDIUMINT,
    CONSTRAINT `fk_sportklasse_sportlehrer`
        FOREIGN KEY (sportlehrer_id) REFERENCES sportlehrer (id)
            ON DELETE CASCADE
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_sportklasse_sporttag`
        FOREIGN KEY (sporttag_id) REFERENCES sporttag (id)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS riege
(
    id              MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
    nummer          TINYINT UNSIGNED,
    sportklassen_id MEDIUMINT,
    CONSTRAINT `fk_riege_sportklasse`
        FOREIGN KEY (sportklassen_id) REFERENCES sportklasse (id)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS student
(
    id           MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
    vorname      VARCHAR(255) NOT NULL,
    nachname     VARCHAR(255) NOT NULL,
    geschlecht   VARCHAR(1)   NOT NULL,
    geburtsdatum DATE         NOT NULL,
    klasse       VARCHAR(2)   NOT NULL,
    riege_id     MEDIUMINT,
    CONSTRAINT `fk_student_riege`
        FOREIGN KEY (riege_id) REFERENCES riege (id)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
);

ALTER TABLE riege ADD COLUMN IF NOT EXISTS  (isdefault BOOLEAN NOT NULL);
ALTER TABLE riege
    ADD UNIQUE INDEX IF NOT EXISTS (isdefault, sportklassen_id);