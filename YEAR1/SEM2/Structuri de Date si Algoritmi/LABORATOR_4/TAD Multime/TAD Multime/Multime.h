#pragma once

#define NULL_TELEM -1
#define ISIZE 10
#include<cstdlib>

typedef  int TComparabil;
typedef TComparabil TElem;

typedef bool(*Relatie)(TElem, TElem);

//in implementarea operatiilor se va folosi functia (relatia)rel(de ex, pentru <=)
//va fi declarata in .h si implementata in .cpp ca unctie externa colectiei bool rel(TElem,TElem)


class IteratorMultime;

class Multime {
	friend class IteratorMultime;

private:
	/*aiai e reprezentarea*/
	int size;
	int nrElem;
	int primLiber;
	TElem* elemente;
	int prim;
	int* urm;

public:
	//constructorul implicit
	Multime();

	bool relatie(TElem e1, TElem e2);

	//adauga un element in multime 
	//returneaza adevarat daca elementul a fost adaugat(nu exista deja in multime)
	bool adauga(TElem e);

	//sterge un element din multime
	//returneaza adevarat dacaa elementul a existat si a fost sters
	bool sterge(TElem e);


	//verifica daca un element este din multime
	bool cauta(TElem e)const;

	//intoarce numarul de elemente din multime;
	int dim()const;
	
	//verifica daca multimea este vida;
	bool vida() const;

	//returneaza diferenta dintre valoarea masima si cea minima(presupunem valori intregi)
	//daca multimea este vida, se returneaza -1;
	int diferentaMaxMin()const;

	//retureaza un iterator pe multime
	IteratorMultime iterator() const;

	//destructorul multimii
	~Multime();
	 

};

