#pragma once
#include"Masina.h"

#include"vector.h"
#include<string>
#include<ostream>


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
	void inchiriere(const Masina& m);

	void sterge(const Masina& m);

	/*
     cauta 
	 arunca exceptie daca nu exista masina
	*/
	bool cauta(const Masina& m);

	/*
	modifica

	*/
	void modifica( Masina& m1, const Masina& m2);

	int lungime();

	/*
	* returneaza toate masinile salvate
	*/

	const vector<Masina>& getAll() const noexcept;


};


class repoException {
	string msg;
public:
	repoException(string m):msg{m}{}
	string getMesaj()const;
	//functie friend (vreau sa folosesc membru privat msg)
};

void testeRepo();

