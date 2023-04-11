function out = lab6ex1()
  n=100;
clf; hold on;
# x = normrnd(media_inaltimii, sigma=variatia, nr_linii, nr_coloane)
x = normrnd(165,10,1,1000);
#hist(x,10)
# [nn,xx] = hist(x, mijl_bare, norm)
# nn-frecv absolute
# xx - mijl barelor
# norm = suma inaltimilor barelor)
# norm ul nostru ar trebui sa reprezinte suma ariilor barelor si sa fie = 1
# suma ariilor barelor = suma inaltimilor barelor * latimea unei bare = 1
# => norm = 1/L
[~,mijl_bare] = hist(x,10);
# latimea unei bare este (max(x)-min(x))/10
l = (max(x)-min(x))/10;
norm = 1/l;
hist(x,mijl_bare,norm);

# lambda function
# f = @(t) normpdf(t, medie, sigma)
f = @(t)normpdf(t,165,10)
fplot(f,[min(x), max(x)],'-r','LineWidth',4)
mean(x)
std(x)
# proportia de valori din intervalul [160,170]
mean(160<x & x<170)
# P(x<a) = normcdf(a medie, sigma)
normcdf(170,165,10)-normcdf(160,165,10)

endfunction

