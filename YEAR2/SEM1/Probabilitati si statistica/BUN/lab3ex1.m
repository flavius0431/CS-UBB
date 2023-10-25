%Intr-o urna sunt 5 bile rosii, 3 bile albastre si 2 bile verzi.
%Se extrag aleator, pe rand, 3 bile din urna  fara repunerea bilei extrase ınapoi in urna ınaintea urmatoarei extrageri.
%Se considera urmatoarele evenimente  asociate  acestui  experiment:
%A:“cel  putin  o  bila  extras a  este  ŗosie”
%si B:“toate  bileleextrase au aceea si culoare.”

%iv)Scrieti o functie care simuleaza de 5000 de ori experimentul
%de mai sus  si returneaza proportia de simulari in care a avut loc
%evenimentul B dupa ce s-a observat anterior apariţia evenimentului A,
% relativa  la  numarul  de  aparitii  ale  evenimentului A.
% Comparati  valoarea  obtinuta  cu  valorile obtinute la iii).


function out = lab3ex1()
  urna= ['R','R','R','R','R','A','A','A','V','V'];
  evA=0;
  evAB=0;
  %i)Folosind functia randsample, scrieti o functie care simuleaza de 5000 de ori experimentul de
  %mai sus si returneaza proportia de simulari ın care a avut loc evenimentul A.
  for i=1:5000
    V=randsample(urna,3);
    if (V(1)=='R' || V(2)=='R' || V(3)=='R')
      evA++;
    endif
   %i)Scrieţi o functie care simuleaza de 5000 de ori experimentul de mai sus si returneaza
   %proportia de simulari in care a avut loc evenimentul A∩B.
   if (  unique(V) == 'R')
     evAB++;
   endif
  endfor
  A=evA/5000;
  fprintf("Proportie eveniment A= %d\n",A);
  B=evAB/5000;
  fprintf("Proportie eveniment A ∩ B=%d\n",B);
  %iii)Folosind rezultatele obtinute la i) si ii), estimati probabilitatea P(B|A). \
  % ComparaTi aceasta estimare cu valoarea exacta a probabilitatii.
  fprintf("probabilitatea conditionata a evenimentului B conditionat de evenimentul A=%d\n",evAB/evA);
endfunction

%1.Daca A si B sunt dou a evenimente astfel ıncat P(A)>0,
%atunci probabilitatea conditionata a evenimentului B conditionat de evenimentul A este P(B|A) =P(A∩B)/P(A).
