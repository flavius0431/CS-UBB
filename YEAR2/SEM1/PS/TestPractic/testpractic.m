%Vriabilele aleatoare independente
%eveniment C"X1<2 si X2<2"
%eveniment D"X1>2 sau X2<2"
function out = testpractic()
    N=1000;
    X1=unifrnd(1,3,1,N);
    X2=unifrnd(1,3,1,N);
    evC=0;
    evD=0;
    Y=0;
    for i=1:N
      if(X1(i)>2 && X2(i)<2)
         evC++;
      endif
      if(X1(i)>2 || X2(i)<2)
         evD++;
      endif
      Y=Y+ min([X1(i) X2(i)])*max([X1(i) X2(i)]);
    endfor

    Z=min(X1,X2)+max(X1,X2);

fprintf("Probabilitatea evenimentului C=%d\n",evC/N);
fprintf("Probabilitatea evenimentului D=%d\n",evD/N);

%probabilitatile teoretice de la a
evC1=0;
evD1=0;

fprintf("Probabilitatea teoretica a evenimentului C=%d\n",evC1/N);
fprintf("Probabilitatea teoretica a evenimentului D=%d\n",evD1/N);

%valoarea medie a variabilei aleatoare Y=min{X1,X2}*max{X1,X2}
%Valoarea teoretica E(Y)?
fprintf("valoarea medie a variabilei aleatoare Y=%d\n",Y/N);
fprintf("valoarea teoretica medie a variabilei aleatoare Y=%d\n",mean(Z));
  endfunction
