#include "IteratorDictionar.h"
#include "Dictionar.h"

using namespace std;

IteratorDictionar::IteratorDictionar(const Dictionar& d) : dict(d) {
	/* de adaugat */
	act = dict.start;

}


void IteratorDictionar::prim() {
	/* de adaugat */
	act = dict.start;
}


void IteratorDictionar::urmator() {
	/* de adaugat */
	act = act->next;
}


TElem IteratorDictionar::element() const {
	/* de adaugat */
	return pair <TCheie, TValoare>(act->Key.first,act->Key.second);
}


bool IteratorDictionar::valid() const {
	/* de adaugat */
	if(act==NULL)
		return false;
	return true;
}

