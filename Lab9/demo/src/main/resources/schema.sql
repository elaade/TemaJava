CREATE DATABASE IF NOT EXISTS masini_db;
USE masini_db;

CREATE TABLE IF NOT EXISTS masini (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numar_inmatriculare VARCHAR(15) NOT NULL UNIQUE,
    marca VARCHAR(50),
    an_fabricatie INT,
    culoare VARCHAR(20),
    km INT
);
