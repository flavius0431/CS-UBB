%  b.Sa se scrie un predicat care adauga intr-o lista dupa fiecare
%  element par valoarea 1.
%
%  Modelul matematic
%  adaugapar(l1l2..ln)=  - [],  daca n=0.
%                        - l1 (+) 1 (+) adaugapar(l2l3..ln), daca
%                        l1%2=0.
%                       - l1 (+) adaugapar(l2l3..ln), daca
%                        l1%2!=0.
%
%  Semnificația argumentelor predicatelor
%  adaugapar(L:Lista, REZ: Lista( Rezultat))
%  Adauga dupa fiecare element par din lista 1
%  L: lista
%  R: lista rezultat
%   modelul de flux(i,i)determinist sau (i,o) determinist


adaugapar([],[]):-!.
adaugapar([H|T],[H,1|REZ]):- mod(H,2)=:=0,!,adaugapar(T,REZ).
adaugapar([H|T],[H|REZ]):- not(mod(H,2)=:=0),adaugapar(T,REZ).

%  Exemple de testare
%  adaugapar([2,2,2],R). =>R=[2, 1, 2, 1, 2, 1].
%  adaugapar([],[]). => true.
%  adaugapar([3,2,1],[3,1,2,1,1,1]).=>false.
%
