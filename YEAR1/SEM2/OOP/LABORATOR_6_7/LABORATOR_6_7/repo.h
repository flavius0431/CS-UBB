#pragma once
#include"Masina.h"
#include"errors.h"
#include"Spalatorie.h"
#include<vector>
#include<string>
#include<ostream>
#include <map>
#include <unordered_map>

using std::vector;
using std::string;
using std::ostream;

class repo{
	vector<Masina> all;

	bool exist(const Masina& m)const;

public:
	repo() = default;
	//nu permitem copierea de obiecte
	repo(const repo& ot) = delete;
	/*
	* salvare masina
	* arunca exceptie daca mai exista alta masina cu acelasi nr de inmatriculare si model
	*/
	virtual void inchiriere(const Masina& m) ;

	virtual void sterge(const Masina& m) ;

	virtual const Masina& find(string nrI) ;

	/*
     cauta 
	 arunca exceptie daca nu exista masina
	*/
	virtual bool cauta(const Masina& m) ;

	/*
	modifica

	*/
	virtual void modifica(Masina& m1, const Masina& m2) ;

	/*
	* returneaza toate masinile salvate
	*/

	virtual const vector<Masina>& getAll() ;

	
};

class PRepo :public repo {
	std::unordered_map<int, Masina> masini;
	vector<Masina> MasiniV;
	double prob;
	friend class Spalatorie;
	void pass()const;
public:
	PRepo() {
		std::mt19937 gen(123);
		std::uniform_real_distribution<double>dis(0.0, 1.0);
		prob = dis(gen);
	};
	void setProb(double f);

	PRepo(const repo& rep) = delete;

	void inchiriere(const Masina& m)override;

	void sterge(const Masina& m)override;

	const Masina& find(string nrI)override;

	/*
	 cauta
	 arunca exceptie daca nu exista masina
	*/
	bool cauta(const Masina& m)override;

	/*
	modifica

	*/
	void modifica( Masina& m1, const Masina& m2)override;

	/*
	* returneaza toate masinile salvate
	*/

	const vector<Masina>& getAll() override;

};

class repoException {
	string msg;
public:
	repoException(string m):msg{m}{}
	string getMesaj()const;
	//functie friend (vreau sa folosesc membru privat msg)
};

void testeRepo();

