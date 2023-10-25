function out = lab4ex2( p m)
    % Initializam pozitia curenta cu 0
  pozitie = 0;
  % Simulam m pasi
  for i = 1:m
    % Generam un numar aleatoriu x din intervalul (0,1)
    x = rand();
    % Daca x este mai mic decat p, ne deplasam la dreapta (incrementam pozitia curenta cu 1)
    if x < p
      pozitie = pozitie + 1;
    % Daca x este mai mare sau egal cu p, ne deplasam la stanga (decrementam pozitia curenta cu 1)
    else
      pozitie = pozitie - 1;
    endif
  endfor
endfunction

% Functia de simulare a m deplasari si afisare a histogramei pozitiilor finale
function histogramaPozitiiFinale(p, m, n)
  % Cream dictionarul pentru a tine evidenta numarului de aparitii ale fiecarei pozitii finale
  pozitiiFinale = containers.Map();
  % Simulam n deplasari
  for i = 1:n
    % Simulam o deplasare
    pozitie = simuleazaDeplasare(p, m);
    % Daca pozitia finala nu exista in dictionar, o adaugam
    if ~isKey(pozitiiFinale, pozitie)
      pozitiiFinale(pozitie) = 1;
    % Daca pozitia finala exista deja in dictionar, incrementam numarul de aparitii
    else
      pozitiiFinale(pozitie) = pozitiiFinale(pozitie) + 1;
    endif
  endfor
  % Afisam histograma pozitiilor finale
  bar(cell2mat(keys(pozitiiFinale)), cell2mat(values(pozitiiFinale)), "r");
  xlabel("Pozitii finale");
  ylabel("Numar de aparitii");
  title("Histograma pozitiilor finale");
endfunction

% Exemplu de apel al functiei
histogramaPozitiiFinale(0.5, 20, 1000);

endfunction
