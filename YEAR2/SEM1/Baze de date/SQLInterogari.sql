--Interogari

USE Cofetarie;

--1) Afiseaza produsele si pretul lor, impreuna cu tipul de produs si tip Alergeni
   --WHERE,UN EXTRACT INFO DIN MAI MULT DE 2 TABELE
SELECT P.nume AS Produs,P.pret as pret,TP.categorie_produs AS TipProdus, R.nume AS TipAlergeni
FROM Produs P, TipProdus TP, TipAlergeni R
WHERE P.cod_tip_produs=TP.cod_tip_produs AND P.cod_rating=R.cod_tip_alergeni;


--2)afiseaza Produsele care au rating-ul peste medie (mai mare decat 3) impreuna cu bucatarul 
   --WHERE,UN EXTRACT INFO DIN MAI MULT DE 2 TABELE
SELECT P.nume AS Produs,R.numar_stele AS RatingProdus ,B.nume AS Bucatar
FROM Produs P INNER JOIN RatingProdus R ON P.cod_rating=R.cod_rating INNER JOIN Bucatar B ON P.cod_bucatar = B.cod_bucatar
WHERE R.numar_stele >3


--3)Afisam bucatarii care au mai multe comenzi pe produsele fabricate de ei (mai mult de o comanda)
    --GROUP BY, HAVING,UN EXTRACT INFO DIN MAI MULT DE 2 TABELE
SELECT B.nume AS Bucatar, COUNT(cod_comanda) AS NumarComenzi
FROM Bucatar B INNER JOIN Produs P ON P.cod_bucatar = B.cod_bucatar INNER JOIN ComandaProdus CP ON CP.cod_produs=P.cod_produs
GROUP BY B.nume HAVING COUNT(cod_comanda)>1

--4)Afisam comenzile online impreuna cu produsele comandate
 --WHERE,UN EXTRACT INFO DIN MAI MULT DE 2 TABELE, 1 PE RELATIE M-N
SELECT C.cod_comanda AS Comanda, P.nume AS Produs
FROM TipComanda TC INNER JOIN Comanda C ON C.cod_tip_comanda=TC.cod_tip_comanda INNER JOIN ComandaProdus CP ON C.cod_comanda = CP.cod_comanda INNER JOIN 
Produs P ON P.cod_produs= CP.cod_produs
WHERE TC.tip_comanda=N'online'

--5)Afisam clientii carora nu li se aplica nicio reducere impreuna cu  produse comandate 
  --WHERE,UN EXTRACT INFO DIN MAI MULT DE 2 TABELE, 1 PE RELATIE M-N
 SELECT C.nume_client AS Client , P.nume AS Produs 
 FROM TipClient TC INNER JOIN Client C ON C.cod_tip_client=TC.cod_tip_client INNER JOIN Comanda CO ON CO.cod_client=C.cod_client 
 INNER JOIN ComandaProdus CP ON CO.cod_comanda = CP.cod_comanda INNER JOIN Produs P ON P.cod_produs= CP.cod_produs
 WHERE TC.categorie_client=N'fara categorie'

 --6) Afisam personalul cu tipurile de comenzi pe care le are in preluare la telefon
 --WHERE,UN EXTRACT INFO DIN MAI MULT DE 2 TABELE
 SELECT P.nume_personal AS Personal ,TC.tip_comanda
 FROM Personal P INNER JOIN Comanda C ON P.cod_personal=C.cod_personal INNER JOIN TipComanda TC ON C.cod_tip_comanda=TC.cod_tip_comanda
 WHERE TC.tip_comanda=N'la telefon'

 --7)Afisam personalul care are mai multe comenzi in preluare(mai mult de o comanda)
 --GROUP BY, HAVING
 SELECT P.nume_personal AS Personal ,COUNT(cod_comanda) AS NumarComenzi
 FROM Personal P INNER JOIN Comanda C ON P.cod_personal=C.cod_personal 
 GROUP BY P.nume_personal HAVING COUNT(cod_comanda)>1

 --8)Afisam tipurile de alergeni aflate in produsele  comandate
 --UN EXTRACT INFO DIN MAI MULT DE 2 TABELE,GROUP BY,WHERE
 SELECT TA.nume AS TipAlergeni
 FROM TipAlergeni TA INNER JOIN Produs P ON P.cod_alergeni=TA.cod_tip_alergeni INNER JOIN ComandaProdus CP ON CP.cod_produs = P.cod_produs 
 WHERE P.cod_produs = CP.cod_produs GROUP BY TA.nume 

 --9)Afiseaza rating-urile pe care le au produsele disponibile
 --DISTINCT
 SELECT DISTINCT R.numar_stele ,R.descriere
 FROM RatingProdus R INNER JOIN Produs P ON P.cod_rating=R.cod_rating


 --10)Afiseaza tipurile de alergeni care apar in produsele disponibile
 --DISTINCT
 SELECT DISTINCT A.nume AS TipAlergeni,A.descriere 
 FROM TipAlergeni A INNER JOIN Produs P on P.cod_alergeni=A.cod_tip_alergeni



