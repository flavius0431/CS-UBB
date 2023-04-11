USE Cofetarie;

/*
--Personal:
INSERT INTO Personal(nume_personal, email_personal,telefon_personal)
VALUES(N'Andrei Popescu',N'andrei.popescu@gmail.com',N'0770234242'),
      (N'Mihai Bucur',N'mihai.bucur@gmail.com',N'0771789121'),
	  (N'Bogdan Asavistei',N'bogdan.asavistei@gmail.com',N'0771223890');

--Bucatar:
INSERT INTO Bucatar(nume,email,telefon,experienta)
VALUES(N'Flavius Simbotin',N'flavius.simbotin@gmail.com',N'0770386118',N'profesionist'),
      (N'Marius Istrate',N'marius.istrate@yahoo.com',N'7771458990',N'incepator');

--TipComanda:
INSERT INTO TipComanda(tip_comanda,descriere)
VALUES (N'online',N'se va livra cu Glovo'),
       (N'in magazin',N'se face in magazin'),
	   (N'la telefon',N'se va livra cu livratorul');
--TipClient:
INSERT INTO TipClient(categorie_client,reducere_aplicata)
VALUES (N'student/elev',15),
       (N'pensionar',25),
	   (N'fara categorie',0),
	   (N'client fidel',10);

--RatingProduse
INSERT INTO RatingProdus(numar_stele,descriere)
VALUES (1,N'nivel slab de calitate'),
       (2,N'ingrediente de nivel mediu'),
	   (3,N'nivel peste mediu de calitate'),
	   (4,N'printre cele mai calititative produse'),
	   (5,N'ingrediente de calitate inalta');

--TipProdus
INSERT INTO TipProdus(categorie_produs,descriere)
VALUES  (N'vegan',N'exclude tot ce ține de produse animale'),
        (N'vegetarian',N'exclude produsele animale'),
		(N'lacto-ovo-vegetarian',N'vegetarian, ce include oua si lapte'),
		(N'lacto-vegetarian',N'vegetarian, ce include lapte'),
		(N'ovo-vegetarian',N'vegetarian, ce include oua');

--TipAlergeni
INSERT INTO TipAlergeni(nume,descriere)
VALUES (N'lapte',N'contine lapte si produse pe baza de lapte'),
       (N'ou',N'contine oua si produse pe baza de oua'),
	   (N'arahide',N'contine arahide si produse pe baza de arahide'),
	   (N'soia',N'contine soia si produse pe baza de soia'),
	   (N'gluten',N'ceriale care contin gluten(grau,secara,ovaz,orz)'),
	   (N'fructe cu coaja',N'contine nuci, alune,migdale,caju si produse derivate');

--Client
INSERT INTO Client(nume_client,adresa_client,cod_tip_client)
VALUES (N'Andrei Aoki',N'Str. Gheorghe Bucur nr.28',1),
       (N'Simpetru Gabriel',N'Str. Bogdan Voda nr.32',4),
	   (N'Maria Pop',N'Str. Mircea Anton nr.193',3),
	   (N'Chiuta Ion',N'Str. Eugen Ionesci nr.2',2),
	   (N'Casandra Munteanu',N'Str. Anton Panu nr.23',1),
	   (N'Darius Chirila',N'Str. Ion Creanga nr.22',3),
	   (N'Antonia Pascal',N'Str. Mihai Eminescu nr.78',4);

--Produs

INSERT INTO Produs(nume,valabiliatet,pret,cod_alergeni,cod_tip_produs,cod_rating,cod_bucatar)
VALUES (N'Tofifee',N'20.04.2022',4.0,3,1,3,2), 
       (N'Tort cu zmeura si ciocolata',N'20.04.2022',23.0,6,1,4,1),
	   (N'Tort cu ciocolata si capsuni',N'20.04.2022',23.0,6,1,4,1),
	   (N'Biscuiti cu crema de fistic',N'20.04.2022',7.0,6,4,3,2),
	   (N'Brownie',N'20.04.2022',13,6,1,3,1);

--Comanda

INSERT INTO Comanda(data_comanda,cod_tip_comanda,cod_client,cod_personal)
VALUES ('2022-11-04',6,1,1),
       ('2022-4-12',7,3,2),
	   ('2022-4-11',5,5,3),
	   ('2022-4-11',7,3,1);


--Comanda Produs

INSERT INTO ComandaProdus(cod_comanda,cod_produs)
VALUES (1,5),
       (2,3),
	   (3,4),
	   (4,1);
*/
