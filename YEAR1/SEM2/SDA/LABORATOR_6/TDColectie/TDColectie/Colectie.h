#pragma once
#define MAXIM 13 //nr maxim de locatii in tabela
#define NULL_TELEM -1

typedef int TElem;

class IteratorColectie;

class Colectie {
	friend class IteratorColectie;
	
private:
	/* aici e reprezentarea */
	TElem* e;
	int d(TElem e, int i);//functia de dispersie
	void redim();
public:
	int m; //numarul de locatii din tabela de dispersie
	
    //Constructoe implicit

	Colectie();

	//adauga un element in colectie
	void adauga(TElem e);

	//sterge o aparitie a unui element din colectie
	// returneaza adevarat daca s-a putut sterge
	bool sterge(TElem e);

	//verifica daca un elememnt se afla in colectie
	bool cauta(TElem el);

	//returneaza numarul de aparitii ale unui element in colectie
	int nrAparitii(TElem el)const;

	//aduga aparitii multiple?

	//intoarce numarul de elemente din colectie;
	int dim() const;

	//verifica daca colectia e vida;
	bool vida()const;

	//returneaza un iterator pe colectie

	IteratorColectie iterator()const;

	//returneaza diferenta dintre valoarea maxima si cea minima
	//daca colectia este vida, se returneaza -1
	int diferentaMaxMin()const;


	//destructorul colectiei
	~Colectie();
};