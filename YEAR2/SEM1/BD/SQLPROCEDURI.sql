USE Cofetarie;

--1)MODIFICA TIPUL UNEI COLOANE;
GO
CREATE PROCEDURE VARIANTA_1
AS 
BEGIN
     ALTER TABLE Comanda
	 ALTER COLUMN data_comanda datetime;

	 UPDATE Versiune
	 SET numarVersiune = 2
END;

--UNDO TIPUL UNEI COLOANE
GO
CREATE PROCEDURE UNDO_1
AS 
BEGIN
     ALTER TABLE Comanda
	 ALTER COLUMN data_comanda date;

	 UPDATE Versiune
	 SET numarVersiune = 1
END
EXEC UNDO_1

GO
 --2) ADAUGA O CONSTANGERE DE "VALOARE IMPLICITA" PENTRU UN CAMP

 CREATE PROCEDURE VARIANTA_2
 AS 
 BEGIN
      ALTER TABLE TipAlergeni
	  ADD CONSTRAINT df_descriere DEFAULT N'Nu exista descriere' FOR descriere;

	  UPDATE dbo.Versiune
	  SET numarVersiune = 3
 END;
 GO

  -- UNDO ADAUGA O CONSTANGERE DE "VALOARE IMPLICITA" PENTRU UN CAMP
GO
CREATE PROCEDURE UNDO_2 
AS
BEGIN
    ALTER TABLE TipAlergeni
	DROP CONSTRAINT df_descriere;

	 UPDATE Versiune
	 SET numarVersiune = 2
END;



--3)CREEAZA O TABELA;

GO
CREATE PROCEDURE VARIANTA_3
AS 
BEGIN
     CREATE TABLE ProdusulSaptamanii
	 ( cod_ps INT PRIMARY KEY IDENTITY,
	   nume NVARCHAR(100),
	   reducere INT 
	 );

	 UPDATE Versiune
	 SET numarVersiune = 4
END;

EXEC VARIANTA_4;

--STERGE O TABELA;
GO 
CREATE PROCEDURE UNDO_3
AS
BEGIN
    DROP TABLE ProdusulSaptamanii;

	UPDATE Versiune
	SET numarVersiune = 3
END;

--4)ADAUGA UN CAMP NOU

GO
CREATE PROCEDURE VARIANTA_4
AS 
BEGIN
      ALTER TABLE ProdusulSaptamanii
	  ADD cod_produs INT;

	  UPDATE Versiune
	  SET numarVersiune = 5
END;

--STERGE UN CAMP NOU
GO
CREATE PROCEDURE UNDO_4
AS 
BEGIN
     ALTER TABLE ProdusulSaptamanii
	 DROP COLUMN cod_produs;

	 UPDATE Versiune
	 SET numarVersiune = 4
END;

--5)CREEAZA O CONSTRANGERE DE CHEIE STRAINA
GO
CREATE PROCEDURE VARIANTA_5
AS 
BEGIN
     ALTER TABLE ProdusulSaptamanii
	 ADD CONSTRAINT ck_cod_pprodus FOREIGN KEY (cod_produs) REFERENCES Produs(cod_produs) ON UPDATE CASCADE ON DELETE CASCADE

	 UPDATE Versiune
	 SET numarVersiune = 6
END;

--STERGE CONSTRANGERE CHEIE STRAINA
GO
CREATE PROCEDURE UNDO_5
AS
BEGIN
     ALTER TABLE ProdusulSaptamanii
	 DROP CONSTRAINT ck_cod_pprodus

	 UPDATE Versiune
	 SET numarVersiune = 5
END;

GO
CREATE PROCEDURE MAIN
@input VARCHAR(10)
AS
BEGIN
     DECLARE @versiuneCurenta INT
	 SET @versiuneCurenta = (SELECT numarVersiune From Versiune)

	 IF @input NOT IN ('1','2','3','4','5','6')
	 BEGIN
	     PRINT 'Versiune invalida!'
		 RETURN 
     END

	 DECLARE @versiune INT
	 SET @versiune = CONVERT(INT,@input)

	 DECLARE @rezultat VARCHAR(10)
	 WHILE @versiune > @versiuneCurenta
	 BEGIN
	      SET @rezultat = CONCAT('VARIANTA_',@versiuneCurenta)
		  EXECUTE @rezultat
		  PRINT 'Se executa ' + @rezultat
		  SET @versiuneCurenta = (SELECT numarVersiune FROM Versiune)
	 END

	 WHILE @versiune < @versiuneCurenta
	 BEGIN
	      SET @versiuneCurenta = @versiuneCurenta - 1
	      SET @rezultat = CONCAT('UNDO_',@versiuneCurenta)
		  EXECUTE @rezultat
		  PRINT 'Se executa ' + @rezultat
		  SET @versiuneCurenta = (SELECT numarVersiune FROM Versiune)
	 END
END;

GO 

EXEC MAIN 1;
