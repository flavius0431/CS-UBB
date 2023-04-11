

%f([],0).
%f([H|T],S):-f(T,S1),H<S1,!,S is H+S1.
%f([_|T],S):-f(T,S1),S is S1+2.

%f([],0).
%f([H|T],S):-f(T,S1),p_aux([H|T],S,S1).

%p_aux([H|_],S,S1):-H<S1,!,S is H+S1.
%p_aux([_|_],S,S1):-S is S1+2.

insert(E, L, [E | L]).
insert(E, [H | T], [H | R]) :-
    insert(E, T, R).

arr([E | _], 1, [E]).

arr([_ | T], K, R) :-
    arr(T, K, R).

arr([H | T], K, R) :-
    K > 1,
    K1 is K - 1,
    arr(T, K1, R1),
    insert(H, R1, R).


main(L, K, R) :-
    findall(R1, arr(L, K, R1), R).
