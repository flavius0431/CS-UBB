#include "repo.h"
#include<assert.h>
#include<iostream>
#include<sstream>
#include<string>
#include<algorithm>
#include "errors.h"


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
/// <summary>
/// de aici
/// </summary>
/// <param name="m"></param>
void repo::sterge(const Masina& m){
	int index = -1;
	
	for (int i = 0; i < this->lungime(); i++){
		if (this->all[i].getnrInmatriculare() == m.getnrInmatriculare())
			index = i;
	}
	int i = 0;
	for (const Masina& masina : all) {
		if (masina.getnrInmatriculare() == m.getnrInmatriculare()) {
			index = i;
		}
	}

	if (index == -1) {
		throw Errors().MasinaNotFound;
	}
	all.remove(index);

}

int repo::lungime() {
	return all.size();
}


///*
//* cauta
//* arunca exceptie daca nu exista animal
//*/
//bool repo::cauta(const Masina& m) {
//	auto masina = find_if(all.begin(), all.end(), [m](const Masina masina) {return m.getnrInmatriculare() == masina.getnrInmatriculare(); });
//	if (masina != all.end()) {
//		return true;}
//	else {
//		return false;}}

void repo::modifica( Masina& m1, const Masina& m2) {
	int index = -1;
	for(int i=0;i< this->lungime();i++){
		if (all[i].getnrInmatriculare() == m1.getnrInmatriculare()) {
			index = i;
		}
	}

	if (index == -1) {
		throw Errors().MasinaNotFound;
	}
	all[index] = m2;
}


/*
* returneaza toate masinile salvate
*/
const vector<Masina>& repo::getAll() const noexcept {
	return all;
}




string repoException::getMesaj()const{
	return msg;

}