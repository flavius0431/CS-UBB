--Operratii CRUD pentru cele 3 tabele: Produs, ComandaProdus,Comanda 
USE Cofetarie;
GO
--Tabela Produs
--functie pentru validare
CREATE OR ALTER FUNCTION ValidareProdus(@nume NVARCHAR(100),@valabilitate NVARCHAR(100),@pret FLOAT,@cod_alergeni INT,@cod_tip_produs INT,@cod_rating INT,@cod_bucatar INT)
RETURNS NVARCHAR(100)
AS
BEGIN
     IF(@nume='' OR 
	 @valabilitate='' OR 
	 @pret < 0 OR 
	 NOT(EXISTS(SELECT cod_tip_alergeni FROM TipAlergeni WHERE cod_tip_alergeni = @cod_alergeni)) OR
	 NOT(EXISTS(SELECT cod_tip_produs FROM TipProdus WHERE cod_tip_produs = @cod_tip_produs)) OR
	 NOT(EXISTS(SELECT cod_rating FROM RatingProdus WHERE cod_rating = @cod_rating)) OR
	 NOT(EXISTS(SELECT cod_bucatar FROM Bucatar WHERE cod_bucatar = @cod_bucatar)))
	    RETURN 'invalid'
	return 'valid'
END;
GO

--functie pentru a verifica existenta unui cod in tabela de Produs
CREATE OR ALTER FUNCTION VerCodProdus( @cod_produs INT)
RETURNS VARCHAR(20)
AS
BEGIN
    IF((EXISTS(SELECT cod_produs FROM Produs WHERE cod_produs = @cod_produs)))
	  RETURN 'exista'
    return 'nu exista'
END;
GO

--operatii CRUD
--CREATE/INSERT
CREATE OR ALTER PROCEDURE CreateProdus @nume NVARCHAR(100),@valabilitate NVARCHAR(100),@pret FLOAT,@cod_alergeni INT,@cod_tip_produs INT,@cod_rating INT,@cod_bucatar INT
AS 
BEGIN
    IF (dbo.ValidareProdus(@nume ,@valabilitate ,@pret ,@cod_alergeni ,@cod_tip_produs,@cod_rating ,@cod_bucatar ) ='valid')
	    INSERT INTO Produs(nume ,valabiliatet ,pret ,cod_alergeni ,cod_tip_produs,cod_rating ,cod_bucatar) VALUES
		(@nume ,@valabilitate ,@pret ,@cod_alergeni ,@cod_tip_produs,@cod_rating ,@cod_bucatar );
	ELSE
	    PRINT 'NU SE POATE REALIZA CREAREA DEOARECE DATELE SUNT INVALIDE';
END;


--READ/SELECT
GO
CREATE OR ALTER PROCEDURE ReadProdus @cod_produs INT
AS 
BEGIN 
    IF(dbo.VerCodProdus(@cod_produs)='exista')
	  SELECT * FROM Produs WHERE cod_produs = @cod_produs
	ELSE
	  PRINT 'NU EXISTA PRODUSUL CU ACEST COD'
END;

GO

--UPDATE
CREATE OR ALTER PROCEDURE UpdateProdus @cod_produs INT,@nume NVARCHAR(100),@valabilitate NVARCHAR(100),@pret FLOAT,@cod_alergeni INT,@cod_tip_produs INT,@cod_rating INT,@cod_bucatar INT
AS
BEGIN
     IF(dbo.VerCodProdus(@cod_produs)='nu exista')
	    PRINT 'NU EXISTA PRODUS CU ACEST COD'
	ELSE IF(dbo.ValidareProdus(@nume ,@valabilitate ,@pret ,@cod_alergeni ,@cod_tip_produs,@cod_rating ,@cod_bucatar)='valid')
	BEGIN
	   UPDATE Produs
	   SET nume = @nume,valabiliatet=@valabilitate, pret = @pret,cod_alergeni = @cod_alergeni,cod_tip_produs=@cod_tip_produs,cod_rating=@cod_rating,cod_bucatar = @cod_bucatar
	   WHERE cod_produs = @cod_produs
	END
	  ELSE
	   PRINT 'DATELE INTODUSE NU SUNT CORECTE'
END;
	   
SELECT * from Produs

--DELETE
GO
CREATE OR ALTER PROCEDURE DeleteProdus @cod_produs INT
AS
BEGIN
    IF(dbo.VerCodProdus(@cod_produs)='nu exista')
	   PRINT 'NU EXISTA PRODUS CU ACEST COD'
	ELSE
	   DELETE FROM Produs WHERE cod_produs = @cod_produs
END;


--Tabela Comanda
--functie pentru validare
GO
CREATE OR ALTER FUNCTION ValidareComanda (@data_comanda NVARCHAR(100), @cod_tip_comanda INT, @cod_client INT, @cod_personal INT)
RETURNS NVARCHAR(50)
AS 
BEGIN 
   IF(--ISDATE(@data_comanda)=0 OR
      NOT(@data_comanda LIKE '[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]')OR 
      NOT(EXISTS(SELECT cod_tip_comanda FROM TipComanda WHERE cod_tip_comanda = @cod_tip_comanda)) OR
	  NOT(EXISTS(SELECT cod_client FROM Client WHERE cod_client = @cod_client)) OR
	  NOT(EXISTS(SELECT cod_personal FROM Personal WHERE cod_personal = @cod_personal)))
	    RETURN 'invalid'
	RETURN 'valid'
END;
go
--functie pentru a verifica existenta unui cod in tabela de Comanda
CREATE OR ALTER FUNCTION VerCodComanda( @cod_comanda INT)
RETURNS VARCHAR(20)
AS
BEGIN
    IF((EXISTS(SELECT cod_comanda FROM Comanda WHERE cod_comanda = @cod_comanda)))
	  RETURN 'exista'
    return 'nu exista'
END;
GO

-- CREATE/INSERT
CREATE OR ALTER PROCEDURE CreateComanda @data_comanda NVARCHAR(100),@cod_tip_comanda INT,@cod_client INT,@cod_personal INT
AS 
BEGIN
    IF(dbo.ValidareComanda(@data_comanda ,@cod_tip_comanda,@cod_client,@cod_personal)='valid')
	   INSERT INTO Comanda(data_comanda,cod_tip_comanda,cod_client,cod_personal) 
	   VALUES(@data_comanda ,@cod_tip_comanda,@cod_client,@cod_personal)
	ELSE 
	   PRINT 'NU SE POATE REALIZA CREAREA DEOARECE DATELE SUNT INVALIDE'
END;



--READ/SELECT 
GO
CREATE OR ALTER PROCEDURE ReadComanda @cod_comanda INT
AS 
BEGIN
    IF(dbo.VerCodComanda(@cod_comanda)!='exista')
	   PRINT 'NU EXISTA COMANDA CU ACEST COD'
	ELSE
	   SELECT *  FROM Comanda WHERE cod_comanda = @cod_comanda
END;

--UPDATE
GO
CREATE OR ALTER PROCEDURE UpdateComanda @cod_comanda INT, @data_comanda NVARCHAR(100),@cod_tip_comanda INT,@cod_client INT,@cod_personal INT
AS
BEGIN
     IF(dbo.VerCodComanda(@cod_comanda)='nu exista')
	    PRINT 'NU EXISTA COMANDA CU ACEST COD'
	ELSE IF(dbo.ValidareComanda(@data_comanda ,@cod_tip_comanda,@cod_client,@cod_personal)='valid')
	BEGIN
	   UPDATE Comanda
	   SET data_comanda=@data_comanda,cod_tip_comanda=@cod_tip_comanda,cod_client =@cod_client,cod_personal=@cod_personal 
	   WHERE cod_comanda=@cod_comanda
	END
	  ELSE
	   PRINT 'DATELE INTODUSE NU SUNT CORECTE'
END;

--DELETE
GO
CREATE OR ALTER PROCEDURE DeleteComanda @cod_comanda INT
AS
BEGIN
    IF(dbo.VerCodComanda(@cod_comanda)='nu exista')
	   PRINT 'NU EXISTA COMANDA CU ACEST COD'
	ELSE
	   DELETE FROM Comanda WHERE cod_comanda = @cod_comanda
END;


SELECT * FROM Comanda

--Tabela ComandaProdus
--functie pentru validare
GO
CREATE OR ALTER FUNCTION ValidareComandaProdus (@nr_produse INT)
RETURNS NVARCHAR(50)
AS 
BEGIN 
   IF(@nr_produse < 0)
	    RETURN 'invalid'
	RETURN 'valid'
END;
go

--functie pentru a verifica existenta unui cod in tabela de Comanda
CREATE OR ALTER FUNCTION VerCodComandaProdus( @cod_comanda INT,@cod_produs INT)
RETURNS VARCHAR(20)
AS
BEGIN
    IF((EXISTS(SELECT cod_comanda FROM ComandaProdus WHERE cod_comanda = @cod_comanda AND cod_produs=@cod_produs)))
	  RETURN 'exista'
    RETURN 'nu exista'
END;
GO

--DE VERIFICAT
--CREATE/INSERT

CREATE OR ALTER PROCEDURE CreateComandaProdus @cod_comanda INT,@cod_produs INT, @nr_produse INT
AS
BEGIN
    IF(dbo.VerCodComandaProdus(@cod_comanda,@cod_produs)='exista')
	     PRINT 'EXISTA DEJA ACEASTA COMANDA PRODUS'
	ELSE IF (dbo.ValidareComandaProdus(@nr_produse)='invalid')
	       PRINT 'DATELE INTODUSE NU SUNT VALIDE SI NU SE POATE EFECTUA CREATE'
	 ELSE
	    INSERT INTO ComandaProdus(cod_comanda,cod_produs,nr_produse) VALUES(@cod_comanda,@cod_produs,@nr_produse)
	  
END;


--READ/SELECT
GO
CREATE OR ALTER PROCEDURE ReadComandaProdus @cod_comanda INT , @cod_produs INT
AS
BEGIN
    IF(dbo.VerCodComandaProdus(@cod_comanda,@cod_produs)='nu exista')
	   PRINT 'NU EXISTA O COMANDA PRODUS CU ACESTE CODURI'
	ELSE
	   SELECT * FROM ComandaProdus WHERE cod_comanda =@cod_comanda AND cod_produs=@cod_produs
END;

--UPDATE
GO
CREATE OR ALTER PROCEDURE UpdateComandaProdus @cod_comanda INT, @cod_produs INT, @nr_produse INT
AS
BEGIN 
 
	 IF (dbo.ValidareComandaProdus(@nr_produse)='invalid')
	       PRINT 'DATELE INTODUSE NU SUNT VALIDE SI NU SE POATE EFECTUA CREATE'
	 ELSE
	 BEGIN
	    UPDATE ComandaProdus
		SET nr_produse=@nr_produse
		WHERE cod_comanda=@cod_comanda AND cod_produs = @cod_produs 
	END
END;

--DELETE
GO
CREATE OR ALTER PROCEDURE DeleteComandaProdus @cod_comanda INT, @cod_produs INT
AS
BEGIN
    IF(dbo.VerCodComandaProdus(@cod_comanda,@cod_produs)='nu exista')
	   PRINT ' NU EXISTA ACEASTA COMNADA PRODUS'
	ELSE
	   DELETE FROM ComandaProdus WHERE cod_comanda=@cod_comanda AND cod_produs=@cod_produs
END;

--VIEW
--View pentru a afisa numele produselor care au fost comandate in data de "2022-04-11"
GO
CREATE OR ALTER VIEW ViewProdus AS
SELECT P.nume,C.data_comanda FROM Comanda C INNER JOIN ComandaProdus CP 
ON C.cod_comanda = CP.cod_comanda INNER JOIN Produs P  
ON P.cod_produs = CP.cod_produs
WHERE(C.data_comanda = '2022-04-11')
GO

SELECT * FROM ViewProdus

--VIEW 
--View pentru a afisa data cand au fost comandate produsele cu pretul de 23 de lei
GO
CREATE OR ALTER VIEW ViewProdus2 AS
SELECT C.data_comanda, P.pret FROM Produs P INNER JOIN ComandaProdus CP 
ON CP.cod_produs = P.cod_produs INNER JOIN Comanda C  
ON C.cod_comanda = CP.cod_comanda
WHERE P.pret = 23
go

SELECT * FROM ViewProdus2

--CREARE INDECSI

--
CREATE INDEX ix_data_comanda_asc ON Comanda(data_comanda ASC)

CREATE INDEX ix_pret_produs_asc ON Produs(pret ASC)

--CREATE INDEX ix_nume_produs_asc ON Produs(nume ASC)
--
CREATE INDEX ix_cod_comanda_produs_asc ON ComandaProdus( cod_comanda ASC, cod_produs ASC)



DROP INDEX ix_nume_produs_asc ON Produs;

DROP INDEX ix_pret_produs_asc ON Produs;

DROP INDEX ix_data_comanda_asc ON Comanda;

SELECT * FROM ComandaProdus
SELECT * FROM Comanda
SELECT * FROM Produs

--EXEC CreateProdus 'Tort de ananas','2022-12-20',135,5,3,316538,1 
--EXEC ReadProdus 2740
--EXEC UpdateProdus 2740,'Tort rusesc','2022-12-24',70,3,2,316537,2
--EXEC DeleteProdus 2740
--EXEC CreateComanda '2022-12-24',6,2,2
--CreateComanda @data_comanda DATETIME,@cod_tip_comanda INT,@cod_client INT,@cod_personal INT
--EXEC ReadComanda 22
--EXEC UpdateComanda 22,'2022-12-24',5,5,2
--EXEC DeleteComanda 22
--EXEC CreateComandaProdus 9,2722,5
--EXEC ReadComandaProdus 9,2722
--EXEC UpdateComandaProdus 9,2722,6
--EXEC DeleteComandaProdus 9,2722