function P = bile(N=500)
 P = 0;
 urna = ['r','r','r','r','r','a','a','a','v','v'];
 for i=1:N
   ext= randsample(urna,3);
   if (ext(1) == 'r')
     ++P;
   endif
 endfor
 P =P/N;
 end

