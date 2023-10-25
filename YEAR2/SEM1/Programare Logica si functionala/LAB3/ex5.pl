
% a) Sa se determine pozitiile elementului maxim dintr-o lista liniara.
% De ex:
% poz([10,14,12,13,14], L) va produce L = [2,5].
% Modelul matematic
%  maxim(l1l2..ln,E)= 0, daca n=0.
%                     E, daca n=1.
%                     maxim(l2..ln,l1), daca l1>E.
%                     maxim(l2..ln,E).

maxim([],0).
maxim([E],E):-!.
maxim([H,T],H):-maxim(T,R),H>R,!.
maxim([_|T],R):-maxim(T,R).

%Modelul matematic
% pozMax(l1l2..ln,M,POZ)= 0 daca n=0.
%                          poz(l2..ln,M,POZ+1), l1=M
%                          poz(l2..ln,M,POZ+1), l1!=M

pozMax([],_,_,[]).
pozMax([M|T],M,POZ,[POZ|R]):-!,POZ1 is POZ+1,pozMax(T,M,POZ1,R).
pozMax([_|T],M,POZ,R):-POZ1 is POZ+1, pozMax(T,M,POZ1,R).


poz(L,R):- maxim(L,M),pozMax(L,M,1,R).
