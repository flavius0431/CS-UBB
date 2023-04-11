% 2.Fiind dat un numar n pozitiv, se cere sa se determine toate
% descompunerile sale ca suma de numere prime distincte.
%Modelul matematic
%ePrim(n) = ePrim(N,2), daca N>1
%ePrim(N,D)= true, daca D>N/2
%	     ePrim(N,D+1), daca N%D==0
% ePrim verifica daca numarul N este prim sau nu. retureaza true daca e
% prim si false altfel
%
% ePrim(N:intreg, D: intreg)
% N: numarul pe care il verificam sa fie prim
% D: divizorii
% model de fluz (i,o) determinist, (i,i)determinist

ePrim(N, D) :-D > N / 2, !.
ePrim(N, D) :-N mod D =\= 0, !,D1 is D + 1,ePrim(N, D1).
ePrim(N) :-N > 1,ePrim(N, 2).

% Modelul matematic
% primSum(S,P) = primSum(P,P), daca ePrim(S) = true
%                primSum(S,P+1), daca S > P si ePrim(P)= false
%                primSum(S-P,P+1),daca S>p, ePrim(P)=true
%                primSum(S,P+1),daca S>P,ePrim(P)=true.
% primSum(S:intreg, P:intreg,R:Lista)
% S: numarul din care facem lista de numere intregi
% P: numere prime mai mici sau egale decat S
% R:lista rezultata
% face liste de numere prime in care suma listelor este egala nu numarul
% dat S
%
primSum(P, P, [P]) :-ePrim(P).
primSum(S, P, R) :-S > P,not(ePrim(P)),P1 is P + 1,primSum(S, P1, R).
primSum(S, P, [P | R]) :-S > P,ePrim(P),P1 is P + 1,S1 is S - P,primSum(S1, P1, R).
primSum(S, P, X) :-S > P,ePrim(P),P1 is P + 1,primSum(S, P1, X).
primSum(S, X) :-primSum(S, 2, X).

%Modelul matematic
%   main(S) = primSum(S)
%
% main(S:intreg,R:lista)
% S: numarul pe care trebuie sa ii facem suma sub forma de numere prime
% R:lista de liste  rezultata

main(S, R) :-
    findall(X, primSum(S, X), R).
