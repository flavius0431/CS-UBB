function I=monte_carlo1(g,a,b,M,nr_sim=1000)
  x=unifrnd(a,b,1,nr_sim);
  y=unifrnd(0,M,1,nr_sim);
  clf;hold on;
  plot( x(g(x)>y) , y(g(x)>y) ,'h','MarkerFaceColor','c')
  # g = @(x) exp(-x.^2)
  # a = -1; b = 2; M = 1;
  fplot(g,[a,b])
  # raportul = aria de sub grafic / aria dreptunghiului
  # => aria de sub grafic = raport * aria dreptunghiului
  # raport = mean(g(x)>y)
  I=mean(g(x)>y)*((b-a)*M);
  integral(g,a,b)
end


