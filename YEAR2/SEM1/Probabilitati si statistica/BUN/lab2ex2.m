%Laboratorul 2 ex 2
%  a) Generati N ∈ {500, 1000, 2000} puncte uniform aleatoare
% intr-un patrat. Afisati frecventa relativa a punctelor care:
% 1) sunt in interiorul cercului tangent laturilor patratului.
% 2) sunt mai apropiate de centrul patratului decat
% de varfurile patratului.
% 3) formeaza cu varfurile patratului doua triunghiuri
% ascutitunghice si doua triunghiuri obtuzunghice.

function out = lab2ex2(N=1000)
  clf;
  hold on;
  axis equal;
  caz_fav=0;
  caz_favl=0;
  caz_fav2=0;
  O=[0.5 0.5];
  rectangle('Position',[0,0,1,1])
  for i =1 : N
    x=rand();
    y=rand();
    P=[x y];
% 1) sunt in interiorul cercului tangent laturilor patratului.
    if pdist([O;P])<0.5
      plot(x,y,'*g')
      caz_fav++;
    else
      plot(x,y,'*r')
    endif
% 2) sunt mai apropiate de centrul patratului decat
% de varfurile patratului.
    vf1=[0 0];
    vf2=[0 1];
    vf3=[1 0];
    vf4=[1 1];

    if (pdist([O;P]) < pdist([vf1;P]) &&
        pdist([O;P]) < pdist([vf2;P]) &&
        pdist([O;P]) < pdist([vf3;P]) &&
        pdist([O;P]) < pdist([vf4;P]))
       caz_favl++;
       plot(x,y,'*b')
    endif

% 3) formeaza cu varfurile patratului doua triunghiuri
% ascutitunghice si doua triunghiuri obtuzunghice.
    %varfurile celor 4 cercuri suprapuse pe patrat
    centru1=[0.5 1];
    centru2=[1 0.5];
    centru3=[0.5 0];
    centru4=[0 0.5];

    %calculam distantele de la punctul P la centrul fiecarui cerc
    dist1=pdist([centru1;P]);
    dist2=pdist([centru2;P]);
    dist3=pdist([centru3;P]);
    dist4=pdist([centru4;P]);

    %laum distantele grupate 2 cate 2 pentru cercurile alaturate
    % si daca ambele sunt mai mici decat 0.5(raza cercurilor)=>
    %punctul apartine reuniunii celor 2 cercuri
    if(( dist1 < 0.5 && dist2 < 0.5) ||
       ( dist2 < 0.5 && dist3 < 0.5) ||
       ( dist3 < 0.5 && dist4 < 0.5) ||
       ( dist4 < 0.5 && dist1 < 0.5))
      caz_fav2++;
      plot(x,y,'*black')

    endif

 endfor
fr=caz_fav/N;
fprintf('Frecventa relativa a punctelor care sunt in interiorul cercului este = %d\n',fr)
frl = caz_favl/N;
fprintf('Frecv rel a pct care sunt mai aprope centrul patratului decat de varfuri= %d\n',frl)
fr2=caz_fav2/N;
fprintf('Frecventa relativa patratului doua triunghiuri ascutitunghice si doua tri obt = %d\n',fr2)

  end
