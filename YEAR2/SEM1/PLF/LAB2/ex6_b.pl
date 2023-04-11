% b. Sa se elimine toate aparitiile elementului maxim dintr-o lista de numere
% intregi.
%
% Modelul matematatic
% max(l1l2..ln,M)= [], daca n=0.
%                     max(l2..ln,l1),daca l1>M.
%                     max(l2..ln,M), daca l1<M.
% max(L:lista,M:intreg,R:intreg)
% L:lista din care aflam maximul
% M: elementul maximcurent din lista
% R:rezultatul
max([],M,M).
max([H|T],M,R):-H>M,!,max(T,H,R).
max([_|T],M,R):-max(T,M,R).

%Modelul matematic
% eliminaMax(l1l2l3..ln,M) = 0, daca n=0
%                          l1 U eliminaMax(l2..ln), l1!=M.
%                          eliminaMax(l2..ln), daca l1=M.
%
% eliminaMax(L:lista,M:intreg,R:lista)
% L: lista din care eliminam elementul maxim
% M:elementul maxim
% R: lista rezultata
%
eliminaMax([],_,[]).
eliminaMax([H|T],M,[H|R]):- not(H=:=M),eliminaMax(T,M,R).
eliminaMax([H|T],M,R):- H=:=M,eliminaMax(T,M,R).

eliminaMaxP(L,R):- M is max(L,0,M), eliminaMax(L,M,R).

