%comb(L:list, K:integer, C:list)
% (i,i,o)-nedeterminist
comb([H|_],1,[H]).
comb([_|T],K,C):-comb(T,K,C).
comb([H|T],K,[H|C]):-K>1,K1 is K-1,comb(T,K1,C).


predicat(T,K,LC):-findall(C,comb(T,K,C),LC).

insereaza(E,L,[E|L]).
insereaza(E,[H|T],[H|R]):-insereaza(E,T,R).

insereazaFA(E,L,LC):-findall(R,insereaza(E,L,R),LC).

perm([],[]).
perm([H|T],R):-perm(T,L),insereaza(H,L,R).

permFA(L,LC):-findall(R,perm(L,R),LC).

aranj(L,K,R):-comb(L,K,C),perm(C,R).

aranjFA(L,K,LC):-findall(R,aranj(L,K,R),LC).
