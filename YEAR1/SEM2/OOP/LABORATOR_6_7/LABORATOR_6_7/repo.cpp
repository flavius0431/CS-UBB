#include "repo.h"
#include<assert.h>
#include<iostream>
#include<sstream>
#include<string>
#include<algorithm>
#include<fstream>


using std::ostream;
using std::stringstream;
using std::to_string;

void repo::inchiriere(const Masina& m)
{
	for (const Masina& masina : all) {
		if (masina.getnrInmatriculare() == m.getnrInmatriculare()) {
			throw repoException("Masina exista deja!");
		}
	}
	all.push_back(m);
}

void repo::sterge(const Masina& m){
	if (cauta(m) == false) {
		throw Errors().MasinaNotFound;
	}
	int i = 0;
	for (const Masina& masina : all) {
		if (masina.getnrInmatriculare() == m.getnrInmatriculare()) {
			for (int j = i; j < int(all.size()) - 1; j++) {
				all.at(j) = all.at(j + 1);
			}
		}
		i++;
	}
	all.pop_back();

}

const Masina& repo::find(string nrI)
{
	vector<Masina>::iterator f = std::find_if(this->all.begin(), all.end(), [=](const Masina& m) {
		return m.getnrInmatriculare() == nrI; });
	if (f != this->all.end())
		return(*f);
	else
		throw Errors().MasinaNotFound;}



/*
* cauta
* arunca exceptie daca nu exista animal
*/
bool repo::cauta(const Masina& m) {
	auto masina = find_if(all.begin(), all.end(), [m](const Masina masina) {return m.getnrInmatriculare() == masina.getnrInmatriculare(); });
	if (masina != all.end()) {
		return true;}
	else {
		return false;}}

void repo::modifica( Masina& m1, const Masina& m2) {
	if (cauta(m1) == false) {
		throw Errors().MasinaNotFound;
	}
	for (Masina& masina : all) {
		if (masina.getnrInmatriculare() == m1.getnrInmatriculare()) {
			masina = m2;
		}
	}
}


/*
* returneaza toate masinile salvate
*/
const vector<Masina>& repo::getAll()  {
	return all;
}





string repoException::getMesaj()const{
	return msg;
}