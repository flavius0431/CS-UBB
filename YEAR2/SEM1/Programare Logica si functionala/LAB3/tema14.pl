
%14. a) Definiti un predicat care determina predecesorul unui numar reprezentat
%cifra cu cifra intr-o lista. De ex: [1 9 3 6 0 0] => [1 9 3 5 9 9].
%
% decrement(L:lista cu numerul initial, L: lista cu numarul
% decrementat)
%    L: Lista(initiala)
%    L: Lista(lista rezultata)
%    Decrementeaza numarul din lista initiala
%    Model de flux:(i,i) determinist,(i,o)determinist
%    Modelul Matematic
%      decrement(l1l2..ln)=dec(l1l2..ln,0).
%
%predicat auxiliar
decrement(L,[0|Rez]):- dec(L,Rez,0).
decrement(L,[H|Rez]):- dec(L,[H|Rez],0).


% getCF(Elem:cifra din care se scade, CF:vechiul CF care se scade,
% CF1:actualul CF.
%     E:elementul din care se scade
%     CF:este un indicator vechi din care scadem din element
%     CF1:este actualul indicator din care scadem la urmatorul element
%     Seteaza noul carry flag in functie de o cifra si de vechiul carry
%     flag
%     Model de flux: (i,i,i)determinist, (i,i,o)determinist
%     Modelul Matematic
%       getCF(l1,CF) = true daca l1=0,CF=1
%		       false, altfel

getCF(0, 1, 1).
%getCF(_, _, 0).

% scadere(Elem:numarul din care se scade, CF:carry flag ul actual,
% REZ:rezultat)
%   E: elementul din care scade,
%   CF: indicatorul actual
%   REZ: rezultatul dupa scadere, daca e cazul
%   Scade carry flagul din numar
%   Model de flux (i,i,i)determinist, (i,i,o)determinist
%   Modelul Matematic
%     scadere(Nr,CF)= scadere(Nr-CF,CF) daca Nr >0
%                     scadere(Nr,CF), daca Nr=0, CF=0
%                     scadere(Nr,CF), daca Nr=0, CF=1.
%
scadere(Nr, CF, Rez):- Nr > 0,!, Rez is Nr - CF.
scadere(0, 0, 0).
scadere(0, 1, 9).

% dec(L:lista din care scade, REZ:lista rezultat, Elem:CF setat in
% urma operatiei
%  Decrementeaza un numar cu o cifra si seteaza CarryFlag-ul CF
%  L: lista din care scadem
%  REZ: lista rezultata dupa ce am scazut
%  Elem: indicatorul setat in urma operatiei de scadere
%  Modelul matematic
%  dec(l1l2...lm,CF)= dec([l1],CF) ,daca l1=0, CF=1
%                         dec([l1],CF) ,daca l1>0 CF=0
%                         dec([l1|l2..ln],CF), daca
%                               dec(l2l3..ln,CF1),
%                               getCF(H, CF1),
%                               sub1(H, CF1).
%   Model de flux: (i,i,i)determinist, (i,i,o)determinist,
%   (i,o,o)determinist.
dec([E],[Rez],CF):- E == 0, !, Rez is 9, CF is 1.
dec([E],[Rez],CF):- E > 0, !, Rez is E - 1, CF is 0.
dec([H|T],[F|Rez],CF):- dec(T,Rez,CF1),!,getCF(H, CF1, CF),scadere(H, CF1, F).

%b) Se da o lista eterogena, formata din numere intregi si liste de cifre.
%Pentru fiecare sublista sa se determine predecesorul numarului reprezentat
%cifra cu cifra de lista respectiva. De ex:
%[1, [2, 3], 4, 5, [6, 7, 9], 10, 11, [1, 2, 0], 6] =>
%[1, [2, 2], 4, 5, [6, 7, 8], 10, 11, [1, 1, 9] 6]


%decrementList(L:lista cu elemente si liste, REZ:lista rezultat)
%  Scade 1 din toate sublistele
%  Model de flux(i,i)determinist,(i,o)determinist
%  Modelul matematic
%    decrementList(l1l2..ln) = [], daca n=0.
%				   dec(l1)(+) decremetList(l2l3..ln),
%				   daca l1 este  lista
%				   l1(+) decremetList(l2l3..ln),daca l1
%				   este atom
decrementList([],[]).
decrementList([H|T],[Dec|Rez]):-is_list(H),decrement(H,Dec),!,decrementList(T,Rez).
decrementList([H|T],[H|Rez]):-  \+ is_list(H),decrementList(T,Rez).
