#pragma once


class Colectie;
typedef int TElem;

class IteratorColectie {
	friend class Colectie;
private:
	//constructorul primeste o referinta catre container
	//iteratorul va referi primul element din container
	IteratorColectie(const Colectie& c);

	//contine o referinta catre containerul pe care il itereaza
	const Colectie& col;

	/* aici e reprezentarea specifica a iteratorului*/
	//locatia curenta din tabela
	int curent;

	//deplasare iterator pe o pozitie care e ocupata(nu are memorat NIL)
	void deplasare();
public:
	//reseteaza pozitia iteratorului la inceputul containerului
	void prim();

	//muta iteratorul in container
	//arunca exceptie daca iteratorul nu e valid
	void urmator();

	//verifica daca iteratorul e valid(indica un element al containerului)
	bool valid()const;

	//returneaza valoarea elementului din container referit de iterator
	//arunca exceptie daca iteratorul nu e valid
	TElem element() const;
};