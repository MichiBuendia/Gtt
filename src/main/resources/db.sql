DROP DATABASE IF EXISTS `luciocrusca`;
CREATE DATABASE `luciocrusca`
  DEFAULT CHARSET=utf8;

USE `luciocrusca`;

CREATE TABLE `linea` (
  id INTEGER NOT NULL AUTO_INCREMENT,
  codice VARCHAR(3) NOT NULL,
  tipo VARCHAR(25) NOT NULL,
  PRIMARY KEY(id) 
) Engine=InnoDB;

CREATE TABLE `fermata` (
  id INTEGER NOT NULL AUTO_INCREMENT,
  codice VARCHAR(50) NOT NULL,
  latitudine DECIMAL(11,8) NOT NULL,
  longitudine DECIMAL(11,8) NOT NULL,
  PRIMARY KEY(id) 
) Engine=InnoDB;

CREATE TABLE `linea_fermata` (
  id INTEGER NOT NULL AUTO_INCREMENT,
  linea INTEGER NOT NULL,
  fermata INTEGER NOT NULL,
  capolinea BOOLEAN DEFAULT FALSE,
  PRIMARY KEY(id),
  CONSTRAINT FOREIGN KEY (linea)
     REFERENCES linea(id), 
  CONSTRAINT FOREIGN KEY (fermata)
     REFERENCES fermata(id)
) Engine=InnoDB;

CREATE TABLE  `mezzo` (
  id INTEGER NOT NULL AUTO_INCREMENT,
  tipo VARCHAR(25) NOT NULL,
  passeggeri INTEGER NOT NULL,
  velocità_max DECIMAL(4,1) NOT NULL,
  PRIMARY KEY(id)
) Engine=InnoDB;

CREATE TABLE `orario` (
  id INTEGER NOT NULL AUTO_INCREMENT,
  linea INTEGER NOT NULL,
  partenza TIME NOT NULL,
  capolinea INTEGER NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT FOREIGN KEY (linea)
     REFERENCES linea(id), 
  CONSTRAINT FOREIGN KEY (capolinea)
     REFERENCES fermata(id)
) Engine=InnoDB;

CREATE TABLE `corsa` (
  id INTEGER NOT NULL AUTO_INCREMENT,
  mezzo INTEGER NOT NULL,
  orario INTEGER NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT FOREIGN KEY (mezzo)
     REFERENCES mezzo(id), 
  CONSTRAINT FOREIGN KEY (orario)
     REFERENCES orario(id) 
) Engine=InnoDB;

INSERT INTO `linea` (codice, tipo)
  VALUES ('18', 'Bus');
INSERT INTO `linea` (codice, tipo)
  VALUES ('1', 'Metro');
INSERT INTO `linea` (codice, tipo)
  VALUES ('9', 'Tram');

INSERT INTO `fermata` 
  (codice, latitudine, longitudine)
    VALUES ('Fermi', 44.3, 7.04);
INSERT INTO `fermata` 
  (codice, latitudine, longitudine)
    VALUES ('Lingotto', 44.1, 6.99);
INSERT INTO `fermata` 
  (codice, latitudine, longitudine)
    VALUES ('Piazzacaiomario', 44.3, 7.01);
INSERT INTO `fermata` 
  (codice, latitudine, longitudine)
    VALUES ('Portanuova', 44, 7);
INSERT INTO `fermata` 
  (codice, latitudine, longitudine)
    VALUES ('Piazzasofia', 44.4, 7.09);
INSERT INTO `fermata` 
  (codice, latitudine, longitudine)
    VALUES ('Stampalia', 44.6, 7.02);
INSERT INTO `fermata` 
  (codice, latitudine, longitudine)
    VALUES ('Torinoespo', 44.11, 6.992);

INSERT INTO `linea_fermata` 
  (linea, fermata, capolinea)
    VALUES (1, 3, TRUE);
INSERT INTO `linea_fermata` 
  (linea, fermata, capolinea)
    VALUES (1, 4, FALSE);
INSERT INTO `linea_fermata` 
  (linea, fermata, capolinea)
    VALUES (1, 5, TRUE);
INSERT INTO `linea_fermata` 
  (linea, fermata, capolinea)
    VALUES (2, 1, TRUE);
INSERT INTO `linea_fermata` 
  (linea, fermata, capolinea)
    VALUES (2, 2, TRUE);
INSERT INTO `linea_fermata` 
  (linea, fermata, capolinea)
    VALUES (2, 4, FALSE);
INSERT INTO `linea_fermata` 
  (linea, fermata, capolinea)
    VALUES (3, 6, TRUE);
INSERT INTO `linea_fermata` 
  (linea, fermata, capolinea)
    VALUES (3, 4, FALSE);
INSERT INTO `linea_fermata` 
  (linea, fermata, capolinea)
    VALUES (3, 7, TRUE);


INSERT INTO `mezzo` 
  (tipo, passeggeri, velocità_max)
    VALUES ('Bus', 60, 70);
INSERT INTO `mezzo` 
  (tipo, passeggeri, velocità_max)
    VALUES ('Tram', 120, 50);
INSERT INTO `mezzo` 
  (tipo, passeggeri, velocità_max)
    VALUES ('Metro', 300, 80);

INSERT INTO `orario` 
  (linea, partenza, capolinea)
    VALUES (1, '05:30:00', 1);
INSERT INTO `orario` 
  (linea, partenza, capolinea)
    VALUES (1, '05:30:00', 2);
INSERT INTO `orario` 
  (linea, partenza, capolinea)
    VALUES (2, '06:00:00', 5);
INSERT INTO `orario` 
  (linea, partenza, capolinea)
    VALUES (2, '06:20:00', 5);
INSERT INTO `orario` 
  (linea, partenza, capolinea)
    VALUES (3, '05:47:00', 6);
INSERT INTO `orario` 
  (linea, partenza, capolinea)
    VALUES (3, '05:39:00', 7);

INSERT INTO `corsa` 
  (mezzo, orario, `data`)
    VALUES (1, 1, NOW());
INSERT INTO `corsa` 
  (mezzo, orario, `data`)
    VALUES (1, 2, NOW());
INSERT INTO `corsa` 
  (mezzo, orario, `data`)
    VALUES (3, 3, NOW());
INSERT INTO `corsa` 
  (mezzo, orario, `data`)
    VALUES (3, 4, NOW());
INSERT INTO `corsa` 
  (mezzo, orario, `data`)
    VALUES (2, 5, NOW());
INSERT INTO `corsa` 
  (mezzo, orario, `data`)
    VALUES (2, 6, NOW());

