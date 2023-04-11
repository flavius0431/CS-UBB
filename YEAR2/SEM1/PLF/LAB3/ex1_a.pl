
plaseaza(E, [], [E]) :- !.
plaseaza(E, [H | T], [E, H | T]) :-
        E =< H, !.
plaseaza(E, [H | T], [H | R]) :-
        E > H,
        plaseaza(E, T, R).


sorteaza([], []).
sorteaza([H | T], R) :-
        sorteaza(T, R2),
        plaseaza(H, R2, R).
