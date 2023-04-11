function patrat(N=100)
  clf;hold on;axis equal; grid on;
  rectangle('Position',[0,0,1,1])
  contor=0;
  for i=1:N
    hold on;
    x=rand;y=rand;
    O=[0.5,0.5];
    XY=[x,y];
    d = pdist([O;XY]);
    if d<= 0.5
      plot(x,y,'r*')
      contor++;
    endif
  endfor
  out=contor/N

  endfunction
