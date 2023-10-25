%LABORATORUL 2
%1. Estimati prin simulari repetate probabilitatea ca intr-un grup de 23 persoane cel putin doua sa
%aiba aceeasi zi de nastere. Puteti folosi functia randi.

function out = lab2ex1(N)
  caz_fav=0;
  for i=1:N
    persoane=[];
    for j=1:23
      persoane=[persoane randi(365)];
    endfor
    persoane=unique(persoane);
    if (length(persoane)!=23)
      caz_fav=caz_fav+1;
    endif
  endfor
  disp("probabilitatea este")
  disp(caz_fav/N)

  endfunction
