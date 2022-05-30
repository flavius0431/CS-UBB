#include "Multime.h"
#include"IteratorMultime.h"
#include<iostream>
using namespace std;

bool Multime::relatie(TElem e1, TElem e2) {
	if (e1 <= e2)return true;
	return false;
}

Multime::Multime()
{
	urm = new int[200];
	size = 200;
	nrElem = 0;
	prim = -1;
	primLiber = 0;
	elemente = new TElem[200];// multimea
}

bool Multime::adauga(TElem e)
{
	 //Caz Defavorabil: n+n multimea este plina si elementul se afla pe penultima pozitie in lista =>Theta(2*n)
	 //Caz Favorabil: Theta(n) elementul se afla pe prima pozitie , in afara multimii sau pe penulttima pozitie
     //Caz Mediiu: Theta (n)
	 // O(n)
	if (cauta(e)) {
		return false;
	}
	if (nrElem == size) {
		size *= 2;
		TElem* elementeNoi = new TElem[size];
		int* urmNoi = new int[size];
		for (int i = 0; i < nrElem; i++) {
			urmNoi[i] = urm[i];
			elementeNoi[i] = elemente[i];
		}
		delete elemente;
		delete urm;
		elemente = elementeNoi;
		urm = urmNoi;
	}

	if (nrElem == 0)//nu avem deloc elemente si punem elementul pe prima pozitie
	{
		urm[0] = 0;
		elemente[0] = e;
		nrElem++;
		return true;
	}
	int i = 0;
	while (i < nrElem - 1) {
		if (relatie(elemente[urm[i]], e) == true && relatie(elemente[urm[i + 1]], e) == false) {
			for (int j = nrElem; j > i; j--) {
				if (relatie(e, elemente[urm[j - 1]]) == true)
					urm[j] = urm[j - 1];
			}
			elemente[nrElem] = e;
			urm[i + 1] = nrElem;
			nrElem++;
			return true;
		}
		i++;
	}
	if (relatie(elemente[urm[nrElem - 1]], e) == true) { //cand avem doar un element
		urm[nrElem] = nrElem;
		elemente[nrElem] = e;
		nrElem++;
		return true;
	}
	if (relatie(elemente[urm[0]], e) == false) {
		for (int j = nrElem; j > 0; j--) {
			urm[j] = urm[j - 1];
		}
		elemente[nrElem] = e;
		urm[0] = nrElem;
		nrElem++;
		return true;
	}
	return false;
}

bool Multime::sterge(TElem e)
{
	//Caz Defavorabil: este in multime pe ultima pozitie = n*(n-1)*(n-1)=>Theta(n^3)
	//Caz Favorabil : nu este in multime  =>Theta(n)
	//Caz Mediu: Theta(n^3)
	// O(n^3)

	for (int i = 0; i < nrElem; i++) {
		if (elemente[urm[i]] == e) {
			TElem* elementeNoi = new TElem[size];
			int* urmNoi = new int[size];
			for (int j = 0; j < urm[i]; j++) {
				elementeNoi[j] = elemente[j];
			}
			for(int j=urm[i];j<nrElem-1;j++){
				elementeNoi[j] = elemente[j + 1];
			}
			for (int j = 0; j < nrElem; j++) {
				if (urm[j] > urm[i])
					urmNoi[j] = urm[j] - 1;
				else
					urmNoi[j] = urm[j];
			}
			delete elemente;
			delete urm;
			elemente = elementeNoi;
			urm = urmNoi;
			nrElem--;
			return true;
		}
	}
	return false;
}

bool Multime::cauta(TElem e)const {
	//Caz Defavorabil: cand e pe ultima pozitie sau nu e in multime Theta(n)
	//Caz Favorabil: cand primul element este cel dorit Theta(1)
	//Caz Mediu: Theta(n)
	//O(n)


	for (int i = 0; i < nrElem; i++)
	{
		if (elemente[i] == e)
			return true;
	}
	return false;
}


int Multime::dim() const
{   //Theta(1)
	return this->nrElem;
}

bool Multime::vida() const
{
	//Theta(1)
	if (this->nrElem == 0)
		return true;
	return false;
}

int Multime::diferentaMaxMin() const
{   
	int min=99999999, max=-99999999;
	if (vida())
		return -1;
	for (int i = 0; i < nrElem; i++)
	{
		if (elemente[i] < min)
			min = elemente[i];
		if (elemente[i] > max)
			max = elemente[i];
	}

	return max - min;
}

IteratorMultime Multime::iterator() const
{
	return IteratorMultime(*this);
}

Multime::~Multime()
{
	delete elemente;
	delete urm;
}
