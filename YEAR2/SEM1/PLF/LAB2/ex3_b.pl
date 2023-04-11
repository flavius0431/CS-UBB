%b. Sa se scrie o functie care descompune o lista de numere intr-o lista
% de forma [ lista-de-numere-pare lista-de-numere-impare] (deci lista cu doua
% elemente care sunt liste de intregi), si va intoarce si numarul
% elementelor pare si impare.

%Modelul matematic
%  para(l1l2..ln)=[],daca n=0.
%                  l1 U para(l2..ln),daca l1%2=0.
%                  para(l2..ln), daca l1%2!=0.
%  para(L:lista,R:lista)
%  L:lista din care formam o lista de numere pare
%  R: lista rezultata doar cu numere pare.
para([],[]):-!.
para([H|T],[H|R]):- H mod 2=:=0,!,para(T,R).
para([H|T],R):-not(H mod 2=:=0),para(T,R).

%Modelul matematic
%  impara(l1l2..ln) = [], daca n=0.
%                     l1 U impara(l2..ln), daca l1%2!=0.
%                     impara(l2..ln), daca l1%2==0.
%  impara(L:lista,R:lista)
%  L:lista din care formam o lista de numere impare
%  R: lista rezultata doar cu numere impare
impar([],[]):-!.
impar([H|T],[H|R]):- H mod 2 =:= 1,!,impar(T,R).
impar([H|T],R):- H mod 2 =:= 0, impar(T,R).

%Modelul matematic
% len(l1l2..ln)=0, daca n=0.
%                1+len(l2l3..ln).
% len(L:Lista,N:intreg)
%   L:Lista
%   N:lungimea listei
len([],0).
len([_|T],N):-len(T,N1), N is N1+1.

%Modelul matematic
%  listepi(l1l2..ln,)=[], daca n=0.
%                     pare(l1..ln),
%                     impare(l1..ln),
%                     l(para),
%                     l(impara).
%
listepi([],[[],[]],0,0).
listapi(L,[RP,RI],NP,NI):-para(L,RP),
                         impar(L,RI),
                         len(RP,NP),
                         len(RI,NI).
