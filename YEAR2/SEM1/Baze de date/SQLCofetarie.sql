/*CREATE DATABASE Cofetarie;

GO*/

USE Cofetarie;

CREATE TABLE TipClient
( cod_tip_client INT PRIMARY KEY IDENTITY,
  categorie_client NVARCHAR(100) NOT NULL,
  reducere_aplicata NVARCHAR(100)
);

CREATE TABLE TipComanda
( cod_tip_comanda INT PRIMARY KEY IDENTITY,
  tip_comanda NVARCHAR(100) NOT NULL,
  descriere NVARCHAR(100) 
);

CREATE TABLE RatingProdus
( cod_rating INT PRIMARY KEY IDENTITY,
  numar_stele INT,
  descriere NVARCHAR(100)
);

CREATE TABLE TipProdus
( cod_tip_produs INT PRIMARY KEY IDENTITY,
  categorie_produs NVARCHAR(100) NOT NULL,
  descriere NVARCHAR(100) 
);

CREATE TABLE TipAlergeni
( cod_tip_alergeni INT PRIMARY KEY IDENTITY,
  nume NVARCHAR(100) NOT NULL,
  descriere NVARCHAR(100) 
);

CREATE TABLE Produs
( cod_produs INT PRIMARY KEY IDENTITY,
  nume NVARCHAR(100) NOT NULL UNIQUE,
  valabilitate DATE,
  pret FLOAT,
  cod_alergeni INT FOREIGN KEY REFERENCES TipAlergeni(cod_tip_alergeni) ON UPDATE CASCADE ON DELETE CASCADE,
  cod_tip_produs INT FOREIGN KEY REFERENCES TipProdus(cod_tip_produs) ON UPDATE CASCADE ON DELETE CASCADE,
  cod_rating INT FOREIGN KEY REFERENCES RatingProdus(cod_rating) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Client
( cod_client INT PRIMARY KEY IDENTITY,
  nume_client NVARCHAR(100) NOT NULL,
  adresa_client NVARCHAR(100),
  cod_tip_client INT FOREIGN KEY REFERENCES TipClient(cod_tip_client) ON UPDATE CASCADE 
);

CREATE TABLE Personal
( cod_personal INT PRIMARY KEY IDENTITY,
  nume_personal NVARCHAR(100) NOT NULL ,
  email_personal NVARCHAR(100) ,
  telefon_personal NVARCHAR(100)
);

CREATE TABLE Comanda
( cod_comanda INT PRIMARY KEY IDENTITY,
  data_comanda DATE,
  cod_tip_comanda INT FOREIGN KEY REFERENCES TipComanda( cod_tip_comanda) ON UPDATE CASCADE ON DELETE CASCADE,
  cod_client INT FOREIGN KEY REFERENCES Client( cod_client) ON UPDATE CASCADE ON DELETE CASCADE,
  cod_personal INT FOREIGN KEY REFERENCES Personal(cod_personal) ON UPDATE CASCADE ON DELETE CASCADE
);
  
CREATE TABLE ComandaProdus
( cod_comanda INT FOREIGN KEY REFERENCES  Comanda( cod_comanda) ON UPDATE CASCADE ON DELETE CASCADE,
  cod_produs INT FOREIGN KEY REFERENCES Produs( cod_produs) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT pk_ComandaProdus  PRIMARY KEY(cod_comanda,cod_produs)
); */