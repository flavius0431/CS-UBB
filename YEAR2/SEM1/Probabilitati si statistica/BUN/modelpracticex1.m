%1)Considerăm următorul experiment: Într-o urnă sunt 10 bile roșii, 5bile negre și 5 bile albe.
% Se extrag aleator fără returnare 3 bile din urnă.
%a.Estimați, folosind comenzi Octave, probabilitatea evenimentelorP(A), P(B),P(A|C),
% unde
%A: bilele au aceeași culoare,
% B: cele 3 bile sunt de trei culori distincte;
%Ā: bilele nu au aceeași culoare,
%C: printre bilele extrase există cel puţin o bilă neagră.
%b.Afișați probabilitatea teoretică pentru P(A), P(B), P(C).
function out= modelpracticex1()
  urna=['R','R','R','R','R','R','R','R','R','R','N','N','N','N','N','A','A','A','A','A'];
  evA=0;
  evB=0;
  evC=0;
  evCA=0;
  %simulam extragerea bilelor
  for i=1:5000
    %in V extragem 3 bile din urna
    V=randsample(urna,3);
    %cazuri posibile pentru evenimentul A
    if length(unique(V))==1
      evA++;
    endif
    %cazuri posibile pentru evenimentul B
    if length(unique(V))==3
      evB++;
    endif
    %cazuri posibile pentru evenimentul C intersectat A
    if (length(unique(V))==1 && unique(V)=='N')
      evCA++;
    endif
    %cazuri posibile evenimentul C
    if (V(1)=='N' || V(2)=='N' || V(3)=='N')
      evC++;
    endif
  endfor


fprintf("Posibilitatea evenimentului P(A) este = %d\n",evA/5000);
fprintf("Posibilitatea evenimentului P(B) este = %d\n",evB/5000);
fprintf("Posibilitatea evenimentului P(A|C) este = %d\n",evCA/evC);

%%probabilitatile teoretie

%pentru evenimentul A(bilele au aceasi culoare
evA2=840;
%nr de extrageri posibile aranjamente de 20 luate cate 3
N=6840;
%pentru evenimentul B(cele trei culori sunt de culori distincte 10*5*5
evB2=250*6;
%nr de extrageri posibile aranjamente de 20 luate cate 3
N=6840;

fprintf("Posibilitatea teoretica aevenimentului P(A) este = %d\n",evA2/N);
fprintf("Posibilitatea teoretica aevenimentului P(B) este = %d\n",evB2/N);



endfunction
