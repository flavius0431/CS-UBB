%1.a. Sa se scrie un predicat care intoarce diferenta a doua multimi.
%
%  Modelul matematic
%
%  apare(l1l2l3...ln, elem) = - False, daca n=0
%                             - True, daca  elem = l1 si n>=1
%                             - apare(l2l3..ln, elem), daca elem != l1
%                             si n >=1
%
%  Semnificația argumentelor predicatelor
%  apare( L: List, El : element)
%  verifica daca elemntul apare intr-o lista
%  L: lista din care cautam elementul
%  E: elementul cautat
%
%  Modelul de flux(i,i) determinist,(o,i)determinist
%
  apare([H|_],El):- El == H,!.
  apare([_|T],El):- apare(T,El).

%   Exemple de testare
%   apare([1,2,3],1). => true
%   apare([1,2,3],4). => false
%   apare([],1). => false

% Modelul matematic
%   diferenta(l1l2..ln,f1f2..fm) = - [], daca n=0
%                                  - l1(+)diferenta(l2l3..ln,f1f2..fm),
%                                  daca not(apare(f1f2..fm, l1)
%                                  - diferenta(l2l3..ln,f1f2..fm), daca
%                                  apare( f1f2..fm,l1).
%  Semnificația argumentelor predicatelor
%  diferenta(L: List, F: List, R : List(rezultat))
%  Calculeaza diferenta celor doua multimi reprezentate ca L si F
%  L: Lista( descazut)
%  F: Lista(scazator)
%  R: Lista rezultat R=L-K
%  modelul de flux(i,i,i)determinist sau (i,i,o) determinist


  diferenta([],_,[]):-!.
  diferenta([H|T],F,[H|REZ]):-not(apare(F,H)),!,diferenta(T,F,REZ).
  diferenta([_|T],F,REZ):-diferenta(T,F,REZ).

%  Exemple de testare
%  diferenta([1,2,3],[4,5,6],[1,2,3]). => true
%  diferenta([4,5,6],[4,5,6],[]). => true
%  diferenta([4,6,8],[1,2,4,7,8,9],R). => R=[6]
%  diferenta([1,5,8],[1,5,8],[5]).=>false

















