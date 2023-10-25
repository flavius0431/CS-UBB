%3.a. Sa se scrie un predicat care transforma o lista intr-o multime, in
% ordinea primei aparitii. Exemplu: [1,2,3,1,2] e transformat in [1,2,3].
%
%Modelul matematic
%  elimina(l1l2..ln,e )=[], daca n=0,
%                        l1 U elimina(l2l3..ln,e), daca l1!=e
%                        elimina(l2l3..ln), daca l1 =e
%elimina(L: lista,E:element, R:Lista)
%L: lista din care eliminam toate elementele E
%E: elementul pe care il eliminam din lista L
%R: lista rezultata dupa eliminarea tuturor aparitiilor lui E

elimina([],_,[]):-!.
elimina([H|T],E,[H|R]):-not(H==E),!,elimina(T,E,R).
elimina([H|T],E,R):-H==E,elimina(T,E,R).

%
%Modelul matematic
%  multime(l1l2l3..ln)=[], daca n=0.
%                      l1+ multime(l2..ln), daca
%                       n>1,elimina(l2..ln,l1).
%
% multime(L:Lista,R:Lista)
% L:lista pe care trebuie sa o transformam in multime un ordinea
% aparitiilor
% R: lista rezultata
%
multime([],[]):-!.
multime([H|T],[H|R2]):-elimina(T,H,R1),multime(R1,R2).
