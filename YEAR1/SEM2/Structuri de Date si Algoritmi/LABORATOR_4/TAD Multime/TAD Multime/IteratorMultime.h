#pragma once
#include"Multime.h"
typedef int TElem;

class Multime;

class IteratorMultime{
	friend class Multime;
private:

	//constructorul primeste o referinta catre Container
	//iteratorul va referi primul element din container
	IteratorMultime(const Multime& m);

	//contine o referinta catre cointainerul pe care il itereaza
	const Multime& multime;
	/*reprezentarea specifica a iteratorului*/
	int iterator;
	int* urm;
	TElem* elemente;
	int nrElem;

public:
	//reseteaza pozitia iteratorului la inceputul containerului
	void prim();

	//muta iteratorul in container
	//arunca exceptie daca iteratorul nu este valid
	void urmator();

	//verifica daca iteratorul este valid(indica un element al containerului)
	bool valid()const;

	//returneaza valoarea elementului din container referitor la iterator
    //arunca eroare daca iteratorul nu e valid
	TElem element()const;

};

