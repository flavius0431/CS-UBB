#pragma once
#include<string>
#include<iostream>

using std::string;
using std::cout;

class Masina{
	std::string  nrInmatriculare;
	std::string producator;
	std::string model;
	std::string tip;
public:
	Masina() {};
	Masina(const string e, const string p, const string m, const string t):nrInmatriculare{e}, producator{p},model{m},tip{t}{}

	Masina(const Masina& ot) :nrInmatriculare{ot.nrInmatriculare}, producator{ot.producator}, model{ot.model}, tip{ot.tip}{}
	Masina& operator=(const Masina& m);
	
	string getnrInmatriculare()const;
	string getproducator()const;
	string getmodel()const;
	string gettip()const;

	void setnrInmatriculare(string nrT);
	void setproducator(string p);
	void setmodel(string m);
	void settip(string t);

};

