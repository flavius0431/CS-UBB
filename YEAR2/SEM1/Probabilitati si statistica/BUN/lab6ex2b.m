function out = lab6ex1b(m=165, p=10, nr_linii=1, nr_coloane=1000)
    clf;hold on
    x = normrnd(m, p, nr_linii, nr_coloane);
    [~, mijl_bare] = hist(x, 10);
    l = (max(x)-min(x))/10 ;%pentru a afla latimea unei coloane
    hist(x, mijl_bare, 1/l); %norm=suma inaltimilor barelor
    f = @(t) normpdf(t, m, p); %trandul tuturor histogramelor, clopotul lui gauss
    fplot(f, [min(x), max(x)], '-r', 'LineWidth', 4);
    mean(x);
    std(x);
    mean(160<x & x<170); %media de persoane a pers intre 1.60 si 1.70
    normcdf(170,m, p)-normcdf(160, m, p);
end
%suma ariei barelor=1=> l1+..+l10)*latimea barei

