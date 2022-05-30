#include "IteratorMultime.h"
#include"Multime.h"
#include<iostream>

IteratorMultime::IteratorMultime(const Multime& m) : multime{ m } {
	iterator = 0;
	urm = m.urm;
	elemente = m.elemente;
	nrElem = m.nrElem;

   
}

void IteratorMultime::prim()
{
	iterator = 0;
}

void IteratorMultime::urmator()
{
	iterator++;
}

bool IteratorMultime::valid() const
{
	if (nrElem > 0 && iterator < nrElem && iterator >= 0)
		return true;
	return false;
}

TElem IteratorMultime::element() const
{
	if (valid()) {
		return elemente[urm[iterator]];
	}
}
