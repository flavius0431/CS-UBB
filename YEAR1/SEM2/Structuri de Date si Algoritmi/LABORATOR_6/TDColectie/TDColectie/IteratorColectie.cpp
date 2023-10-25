#include"IteratorColectie.h"
#include"Colectie.h"
#include<exception>

#define NIL INT_MIN

//CAZ FAVORABIL: THETA(1)
//CAZ DEFAVORABIL:THETA(m)
//CAZ TOTAL:O(m)
void IteratorColectie::deplasare()
{
	while ((curent < col.m) && col.e[curent] == NIL)
		curent++;
}

//CAZ FAVORABIL: THETA(1)
//CAZ DEFAVORABIL:THETA(m)
//CAZ TOTAL:O(m)
IteratorColectie::IteratorColectie(const Colectie& c):col(c)
{
	curent = 0;
	deplasare();
}

//CAZ FAVORABIL: THETA(1)
//CAZ DEFAVORABIL:THETA(m)
//CAZ TOTAL:O(m)
void IteratorColectie::prim()
{
	//se determina prima locatie ocupata
	curent = 0;
	deplasare();
}

//CAZ FAVORABIL: THETA(1)
//CAZ DEFAVORABIL:THETA(m)
//CAZ TOTAL:O(m)
void IteratorColectie::urmator()
{
	if (!valid())
		throw std::exception();
	curent++;
	deplasare();
}

//THETA(1)
bool IteratorColectie::valid()const
{
	return(curent < col.m);
}

//THETA(1)
TElem IteratorColectie::element() const
{
	if (!valid())
		throw std::exception();
	return col.e[curent];
}

