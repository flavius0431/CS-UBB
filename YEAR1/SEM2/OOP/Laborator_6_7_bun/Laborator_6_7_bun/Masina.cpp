#include "Masina.h"
#include <string>


Masina::Masina()
{
}

Masina::~Masina()
{
}

Masina& Masina::operator=(const Masina& m)
{
	if (this == &m) {
		return *this;
	}
	this->nrInmatriculare = m.nrInmatriculare;
	this->producator = m.producator;
	this->model = m.model;
	this->tip = m.tip;
	return *this;
	
}

string Masina::getnrInmatriculare()const {
	return nrInmatriculare;
}
string Masina::getproducator()const {
	return producator;
}
string Masina::getmodel()const {
	return model;
}
string Masina::gettip()const {
	return tip;
}

void Masina::setnrInmatriculare(string nrI) {
	this->nrInmatriculare = nrI;
}
void Masina::setproducator(string p) {
	this->producator = p;
}
void Masina::setmodel(string m) {
	this->model = m;
}
void Masina::settip(string t) {
	this->tip = t;
}





//string Masina::to_string()const {
//	string text;
//	text.append("numar de inmatriculare: ");
//	text.append(this->getnrInmatriculare());
//	text.append(" producator: ");
//	text.append(this->producator);
//	text.append(" model: ");
//	text.append(this->model);
//	text.append(" tip: ");
//	text.append(this->tip);
//	return text;
//}
