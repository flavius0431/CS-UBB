#include "coada.h"
#include <exception>
#include <iostream>

using namespace std;
#define INITIAL_SIZE 2


//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(1)
//Complexitatea totala: Theta(1)
Coada::Coada() {
	this->inceput = this->sfarsit = -1;
	this->lungime = INITIAL_SIZE;
	this->nrdeElemente = 0;
	this->elemente= new TElem[this->lungime];\
}


//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(n)(cand marim capacitatea)
//Complexitatea totala: O(n)
void Coada::adauga(TElem elem) {
	if (this->nrdeElemente >= this->lungime)// daca numarul de elemente depaste lungimea
	{
		TElem* ElementeNoi = new TElem[this->lungime * 2]; //facem o noua coada de dimensiune dubla
		int j = 0;

		for (int i = this->inceput; i < this->lungime; i++)//copiem elementele in noua coada
		{
			ElementeNoi[j++] = this->elemente[i];

		}

		for (int i = 0; i < this->inceput; i++) 
		{
			ElementeNoi[j++] = this->elemente[i];
		}
		this->inceput = 0;
		this->sfarsit = j - 1;
		delete[] this->elemente;
		this->elemente = ElementeNoi;
		this->lungime *= 2;

	}
	
	if (this->inceput == -1) //nu avem elemente
	{
		this->inceput = this->sfarsit = 0;
		elemente[this->sfarsit] = elem;
	}
	else if (this->inceput > 0 && this->sfarsit == this->lungime - 1) //avem doar un element 
	{
		this->sfarsit = 0;
		elemente[this->sfarsit] = elem;
	}
	else
	{
		this->elemente[++this->sfarsit] = elem;
	}

	this->nrdeElemente++;


	
}

//arunca exceptie daca coada e vida
TElem Coada::element() const {
	if (this->inceput == -1)
	{
		throw exception();
	}
	return this->elemente[this->inceput];
}

//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(1)
//Complexitatea totala: Theta(1)
TElem Coada::sterge() {
	if (this->sfarsit == -1)
	{
		throw exception();
	}
	TElem elem = this->elemente[this->inceput];

	if (this->inceput == this->sfarsit) //avem un singur element in coada
	{
		this->inceput = this->sfarsit = -1;
	}
	else if (this->inceput == this->lungime - 1)
	{
		this->inceput = 0;
	}
	else
	{
		this->inceput++;
	}
	this->nrdeElemente--;
	return elem;
}
//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(1)
//Complexitatea totala: Theta(1)
bool Coada::vida() const {
	return (this->inceput == -1 && this->sfarsit == -1);
}

//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(1)
//Complexitatea totala: Theta(1)
Coada::~Coada() {

	delete[] this->elemente;
}