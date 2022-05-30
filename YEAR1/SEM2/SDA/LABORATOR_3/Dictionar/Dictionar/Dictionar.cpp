#include "Dictionar.h"
#include <iostream>
#include<exception>
#include "IteratorDictionar.h"

void deleteList(Node* head_ref) {

	Node* current = head_ref;
	Node* next = NULL;

	while (current != NULL) {
		next = current->next;
		delete[] current;
		current = next;
	}

	head_ref = NULL;
}

//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(1)
//Complexitatea totala: Theta(1)
Dictionar::Dictionar() {
	/* de adaugat */
	start = new Node;
	start = NULL;
}
//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(1)
//Complexitatea totala: Theta(1)
Dictionar::~Dictionar() {
	/* de adaugat */
	auto head = start;
	deleteList(head);
}

//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(n)
//Complexitatea totala: O(n)
TValoare Dictionar::adauga(TCheie c, TValoare v) {
	/* de adaugat */
	Node* elem_nou = new Node;
	elem_nou->Key = std::make_pair(c, v);
	elem_nou->next = NULL;
	if (start == NULL) { //cand lista este goala
		start = elem_nou;
		return NULL_TVALOARE;
	}
	auto head = start;
	while (head->Key.first != elem_nou->Key.first && head->next != NULL) {
		head = head->next;
	}
	if (head->Key.first == elem_nou->Key.first) {
		TValoare ret = head->Key.second;
		head->Key = elem_nou->Key;
		return ret;
	}
	head->next = elem_nou;
	return NULL_TVALOARE;
}



//cauta o cheie si returneaza valoarea asociata (daca dictionarul contine cheia) sau null
//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(n)
//Complexitatea totala: O(n)
TValoare Dictionar::cauta(TCheie c) const {
	/* de adaugat */
	auto head = start;
	while (head != NULL) {
		if (head->Key.first == c) {
			TValoare ret = head->Key.second;
			return ret;
		}
		head = head->next;
	}
	return NULL_TVALOARE;
}

//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(n)
//Complexitatea totala: O(n)
TValoare Dictionar::sterge(TCheie c) {
	/* de adaugat */
	if (start == NULL) { //lista e goala
		return NULL_TVALOARE;
	}
	
	if (start->next != NULL) { 
		if (start->Key.first == c) {
			TValoare s = start->Key.second;
			start = start->next;
			return s;
		}
	}
	if (start->Key.first == c) {
		Node* aux = start;
		TValoare ret = start->Key.second;
		start = start->next;
		delete aux;
		return ret;
	}
	auto head = start;
	while (head->next != NULL && head->next->Key.first != c) {
		head = head->next;
	}
	if (head->next == NULL) {
		return NULL_TVALOARE;
	}
	else {
		TValoare ret = head->next->Key.second;
		head->next = head->next->next;

		return ret;
	}

	return NULL_TVALOARE;
}

//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(n)
//Complexitatea totala: Theta(n)
int Dictionar::dim() const {
	/* de adaugat */
	int size = 0;
	Node* head = start;
	while (head != NULL) {
		head = head->next;
		++size;
	}
	return size;
}
//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(1)
//Complexitatea totala: Theta(1)
bool Dictionar::vid() const {
	/* de adaugat */
	if(start==NULL)
	    return true;
	return false;
}


IteratorDictionar Dictionar::iterator() const {
	return  IteratorDictionar(*this);
}


//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(n)
//Complexitatea totala: O(n)
int Dictionar::diferentaCheieMaxMin()const {
	int min = 10000000, max = -1;
	Node* head = start;
	if (start == NULL) {
		return NULL_TVALOARE;
	}
	while (head != NULL) {
		if (head->Key.first < min ) {
			min = head->Key.first;
		}
		if (head->Key.first > max) {
			max = head->Key.first;
		}
		head = head->next;
	}
	int c = max - min;
	return c;
}

//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(n)
//Complexitatea totala: O(n)
void Dictionar::inlocuiesteToate(Transformator transformator)
{
	Node* head = start;
	while (head != NULL) {
		head->Key.second = transformator(head->Key.first, head->Key.second);
		head = head->next;
	}
}

//Caz favorabil: Theta(1)
//Caz defavorabil:Theta(1)
//Complexitatea totala:Theta(1)
TValoare Dictionar::transformator(TCheie c, TValoare v) {
	TValoare ret = v * 2;
	return ret;
}


