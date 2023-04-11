CREATE DATABASE GestiuneFestivaluri
GO
USE GestiuneFestivaluri

CREATE TABLE Festivaluri
(cod_f INT PRIMARY KEY IDENTITY,
nume VARCHAR(100),
perioada VARCHAR(100),
an INT,
oras VARCHAR(100),
tara VARCHAR(100)
);

INSERT INTO Festivaluri(nume,perioada,an,oras,tara)VALUES
('Untold','3-6 august',2023,'Cluj-Napoca','Romania'),
('Neversea','6-9 iulie',2023,'Constanta','Romania'),
('Electric Catstle','18-21 iulie',2023,'Bontida','Romania');

SELECT * FROM Festivaluri

INSERT INTO Festivaluri(nume,perioada,an,oras,tara)VALUES
('Beach Please','mai',2023,'Constanta','Romania');
