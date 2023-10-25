%2 b.Sa se scrie un predicat care adauga dupa 1-ul, al 2-lea, al 4-lea, al
% 8-lea ...element al unei liste o valoare v data.
% Modelul matematic
%  putere(E) = false, faca E%2!=0
%              true, daca E =1
%              putere(E/2), daca E%2==0
%  putere(E:element)
%  E: elementul pe care il verificam sa fie putere a lui 2
%
putere(1):-!.
putere(E):- E mod 2=:=0,E1 is E div 2, putere(E1).
% Modelul matematic
%  adaugaV(l1l2..ln,v,poz)= 0, daca n=0
%                       l1 U v U adaugaV(l2..ln,v,poz+1), daca n>1 si
%                       putere2(poz)=true
%                        adaugaV(l2..ln,v,poz), daca n>1 si putere2(poz)
%                        e fals,poz=poz+1
% adaugaV(L:Lista,V:element,POZ:element,R:Lista)
% L: lista in care adaugam elementul v pe pozitiile care is putere a lui 2
% V: elementul pe care il inseram in lista pe pozitiile lui 2
% POZ: pozitia elementului curent (ca sa verificam daca este o putere a lui 2
% R:lista rezultata dupa inserarea elementului V.
%
adaugaV([],_,_,[]):-!.
adaugaV([H|T],V,POZ,[H,V|R]):-putere(POZ),POZ1 is POZ+1,!,adaugaV(T,V,POZ1,R).
adaugaV([H|T],V,POZ,[H|R]):-not(putere(POZ)),POZ1 is POZ+1,adaugaV(T,V,POZ1,R).


adaugaVP(P,V,R):-adaugaV(P,V,0,R).

