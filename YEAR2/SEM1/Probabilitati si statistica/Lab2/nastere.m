function fr = nastere(NS=2000)
 contor=0;
 for i=1:NS
   b= randi(365,1,23);
   if length(b)!=length(unique(b))
     contor=contor+1;
   endif

 endfor

 fr =contor/NS;

