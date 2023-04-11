%2. a)Pentru p∈(0,1),n, m∈N∗ ̧si o variabila aleatoare X∼Bino(n, p),
%sa se genereze un vector x de m valori ale lui X, folosind functia binornd.
%  Comparat datele obţinute cu cele date de distributie, folosind functiile:
%bar,binopdf ̧sihist, testand codul de mai jos:

%b)Folosind funcţia binornd ın 5000 de simulari, estimati probabilitatea ca exact
%2 zaruri din 5 zaruriaruncate sa arate numere divizibile cu 3.
% Comparati valoarea obtinuta cu probabilitatea teoretica corespunzatoare, folosind functia binopdf.
%binornd(n,p..) genereaza valori aleatoare
%binopdf(x, n, p) calculeaza P(X = x), daca X ∼ Bino(n, p).


function out = lab3ex2()
  clf; grid on; hold on;
  p=0.5; n=10; m=100;
  x=binornd(n,p,1,m);
  N=hist(x,0:n);
  bar(0:n,N/m,'hist','FaceColor','b');
  bar(0:n,binopdf(0:n,n,p),'FaceColor','y');
  legend('probabilitatile estimate','probabilitatile teroretice');
  set(findobj('type','patch'),'facealpha',0.7);xlim([-1 n+1]);

  %b
  %nr de incercari
  N=5000;
  #probabilitatea ca nr obtinut de zar sa fie dicizibil cu 3
  P=1/3;
  #cazuri
  cazuri=0;
  for i=1:N
    x=binornd(5,P);
    if x==2
      cazuri++;
    endif
  endfor
  probabilitatea = cazuri/N;
  fprintf("Probabilitatea ca exact 2 din 5 zaruri aa arate nr divizibil cu 3 = %d\n",probabilitatea);
  corect=binopdf(2,5,P);
  fprintf("Probabilitatea teoretica ca exact 2 din 5 zaruri aa arate nr divizibil cu 3 = %d\n",corect);
  endfunction
