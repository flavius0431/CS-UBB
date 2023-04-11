%LABORATOR1
%1)anagramele/permutari cuvantului word(perms)
function out = lab1(v=[],k)
  w=['w','o','r','d'];
  perms(w);

%2) toate perechile neordoonate de cifre
  v2= [2,3,5,7];
  nchoosek(v23,2);

%3) o functie care afiseaza toate aranjamentele de n luate cate k
   mat=nchoosek(v,k);
   n=rows(mat);
   m=columns(mat);
   for i=1:n
     vector=[];
     for j=1:m
       vector=[vector mat(i,j)];
     endfor
     disp(perms(vector))
   endfor

end
