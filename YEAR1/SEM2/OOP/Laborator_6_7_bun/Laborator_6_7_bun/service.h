#pragma once

#include"Masina.h"
#include"repo.h"
#include<string>
#include<stdlib.h>
#include<stdio.h>

#include"vector.h"
#include<functional>
#include"validator.h"


using std::function;

class service{
	repo& rep;
	MasinaValidator& val;

public:
	service(repo& rep, MasinaValidator& val) noexcept:rep{ rep }, val{ val }{
	}
	//nu permite copierea de obiecte service
	service(const service& ot)noexcept = delete;
	

	//returneaza toate animalele in ordinea in care o fost adaugate
	vector<Masina> getAll() noexcept {
		return rep.getAll();
	}
	/*
	* adauga o noua masina
	* arunca exceptie daca : nu se poate salva , nu este valida
	*/
	void addMasina( string nrInmatriculare,  string producator,  string model, string tip);

	/*\
	* sterge o masina dupa numarul de inmatriculare
	* arunca exceptie daca nu gaseste masina
	*/
	void deleteMasina(string nrInmatriculare);
	
	void modifyMasina(string nrInmatriculare1, string nrInmatriculare2, string producator, string model, string tip);

	bool cautaMasina(string nrInmatriculare);

	vector<Masina> filtrareDupaTip(string tip);

	vector<Masina> filtrareDupaProducator(string producator);

	vector<Masina> generalSort(bool(*cmp)(const Masina&, const Masina&));

	vector<Masina> SortByNrInmatriculare(int optiune);

	vector<Masina> SortByTip(int optiune);

	vector<Masina> SortByProducatorModel(int optiune);


	friend bool ComparaCrescatorNrInmatriculare(const Masina& m1, const Masina& m2);
	friend bool ComparaDescrescatorNrInmatriculare(const Masina& m1, const Masina& m2);
	friend bool ComparaCrescatorTip(const Masina& m1, const Masina& m2);
	friend bool ComparaDescrescatorTip(const Masina& m1, const Masina& m2);
	friend bool ComaparaCrescatorProducatorModel(const Masina& m1,const Masina& m2);
	friend bool ComparaDescrescatorProducatorModel(const Masina& m1,const Masina& m2);
	
};

