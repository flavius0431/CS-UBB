#include"Colectie.h"
#include"IteratorColectie.h"
#include<exception>
#include<iostream>
#include<climits>
#define NIL INT_MIN //indica o locatie libera in memorie
#define STERS INT_MIN+10
using namespace std;


//TElem este intreg
//THETA(1)
int hasdCode(TElem e) {
	return abs(e);
}
//THETA(1)
int Colectie::d(TElem e, int i) //dispersia dubla
{
	unsigned long c1, c2;
	c1 = hasdCode(e) % m;
	c2 = (1 + hasdCode(e) % (m - 2)) % m;
	
	return int((c1 + (i * c2) % m) % m);
}

//THETA(m)
Colectie::Colectie()
{
	/* de adaugat */
	m = MAXIM;//initializam m cu o valoare predefinita

	e = new TElem[m];//alocam spatiu de memeorie pt vectorul de elemente
	//se marcheaza pozitiile din tabela ca fiin libere
	for (int i = 0; i < m; i++) {
		e[i] = NIL;
	}
}
//complex
int prim(int nr) {
	if (nr <= 1 || nr % 2 == 0)
		return 0;
	for (int d = 3; d * d <= nr; d = d + 2)
		if (nr % d == 0)
			return 0;
	return 1;
}

//CAZ FAVORABIL: THETA(m)
//CAZ DEFAVORABIL:THETA(m^2)
//CAZ TOTAL:O(m^2)
void Colectie::redim()
{
	int mv = m;
	m = m + 2;
	while (!prim(m))
		m = m + 2;
	//alocam un spatiu de capacitate dubla
	TElem* eNou = new int[m];
	TElem* copie = new int[mv];

	//copiem vechile valori in zona nou auziliara
	for (int i = 0; i < mv; i++) {
		copie[i] = e[i];
	}

	//initializam noul vector
	for (int i = 0; i < m; i++) {
		eNou[i] = NIL;
	}

	//eliberam vechea zona 
	delete[] e;

	//vectorul indica spre noua locatie
	e = eNou;

	//redimensionam capacitatea din tabela
	for (int i = 0; i < mv; i++) {
		adauga(copie[i]);
	}

	delete[] copie;
}

//CAZ FAVORABIL: THETA(m)
//CAZ DEFAVORABIL:THETA(m^2) -redimensionare
//CAZ TOTAL:O(m^2)
void Colectie::adauga(TElem el)
{
	/* de adaugat */
	//locatia de dispersie a cheii
	int i = 0; //nr de verificare
	bool gasit = false;
	do {
		int j = d(el, i); 
		if (e[j] == NIL || e[j] == STERS) {
			e[j] = el;
			gasit = true;
		}
		else i++;
	} while (i<m && !gasit);

	if (i == m) {
		//depasire tabela
		redim();
		adauga(el);
	}
}

//CAZ FAVORABIL: THETA(1)
//CAZ DEFAVORABIL:THETA(m)
//CAZ TOTAL:O(m)
bool Colectie::sterge(TElem el)
{
	int i = 0;
	bool gasit=false;
	do {
		int j = d(el, i);
		if (e[j] == NIL)
			break;
		if (e[j] == el) {
			e[j] = STERS;
			gasit = true;
		}
		else i++;
	} while (i < m && !gasit);
	return gasit;
}

//CAZ FAVORABIL: THETA(1)
//CAZ DEFAVORABIL:THETA(m)
//CAZ TOTAL:O(m)
bool Colectie::cauta(TElem el)
{
	int i = 0;
	bool gasit = false;
	do {
		int j = d(el, i);
		if (e[j] == NIL)
			return false;
		if (e[j] == el) {
			gasit = true;
		}
		else i++;
	} while (i < m && !gasit);
	return gasit;	
}

//THETA(m)
int Colectie::nrAparitii(TElem el) const
{
	int nrAparitii = 0;
	for (int i = 0; i < m; i++)
		if (e[i] == el)
			nrAparitii++;
	return nrAparitii;
	
}
//THETA(m)
int Colectie::dim() const
{
	int nrElem = 0;
	for (int i = 0; i < m; i++)
		if (e[i] != NIL && e[i] != STERS)
			nrElem++;
	return nrElem;
}

//THETA(m)
bool Colectie::vida() const
{
	if (dim() == 0)
		return true;
	return false;
}

//THETA(1)
IteratorColectie Colectie::iterator() const
{
	return IteratorColectie(*this);
}


//THETA(m)
int Colectie::diferentaMaxMin() const
{
	int min = 999999, max = -9999999;
	if (vida())
		return -1;
	for (int i = 0; i < m; i++)
		if (e[i] != NIL && e[i] != STERS) {
			{
				if (e[i] < min)
					min = e[i];
				if (e[i] > max)
					max = e[i];
			}
		}
	return max - min;
}

//THETA(1)
Colectie::~Colectie()
{
	delete[] e;
}


//Functia diferentaMinMax()
//     min: intreg
//     max : intreg
//     daca vida() atunci
//        returneaza - 1
//     sfdaca
//     pentru i = 0, m atunci
//         daca elemente.i!= NIL si elemente.i != STERS atunci
//            daca min > elemente.i atunci
//                min ← elemente.i
//            sfdaca
//            daca  max < elemente.i atunci
//             	  max ← elemente.i
//	          sfdaca
//          sfdaca
//	   sf pentru
//	returneaza max - min
//	sf functia