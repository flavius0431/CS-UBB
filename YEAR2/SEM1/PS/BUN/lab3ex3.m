%Laboratorul 3 exercitiul 3
%3.Consideram experimentul:  se arunca 4 zaruri,  apoi se calculeaza suma numerelor
%obtinute.Rezolvati in Octave urmatoarele cerinte:

%i)Simulati de 1000 de ori aruncarea a 4 zaruri, folosind functia randi.
% Afisati, sub forma unei matrice, toate sumele aparute cu frecventele lor absolute.

%ii)Reprezentati  grafic  frecventele  relative  ale  sumelor  obtinute,  folosind  functiile hist ̧si bar.
%Care sunt cele mai frecvente sume?iii)Afi ̧sat ̧i, sub forma unei matrice,
%toate sumele posibile cu frecventele lor absolute teoretice.
%Reprezentati grafic frecventele relative corespunzatoare.
%Care sunt cele mai frecvente sume?

%iv)Estimati probabilitatea ca suma numerelor celor 4 zaruri este cel putin 10,
%stiind ca suma este cel mult 20.  Afisati probabilitatea teoretica corespunzatoare.
function out = lab3ex3()
    n=1000;
    m=4;
    % generăm un vector de n elemente aleatoare între 1 și 6 (numerele de pe zaruri)
    dice = randi([1 6], n, m);

  % calculăm suma elementelor din fiecare linie
  sums = sum(dice, 2);

  % calculăm frecvența absolută pentru fiecare sumă
  [frequencies, values] = hist(sums, unique(sums));

  % afișăm rezultatele sub forma unei matrici
   result = [values' frequencies']

   relative_frequencies = frequencies / n;

% reprezentăm grafic frecvențele relative
  bar(values, relative_frequencies);

  %iii)
  % calculăm toate sumele posibile (de la 4 la 4 * 6 = 24)
sums1 = 4:24;

% calculăm frecvențele absolute teoretice pentru fiecare sumă
frequencies1 = nchoosek(6, m) * (1/6) .^ m
 %combinari de 6 luate cate 4 ori 1/6 la puterea 4

% afișăm rezultatele sub forma unei matrici
result = [sums1' frequencies1']

% reprezentăm grafic frecvențele relative
bar(sums1, frequencies1 / sum(frequencies1));


  endfunction
