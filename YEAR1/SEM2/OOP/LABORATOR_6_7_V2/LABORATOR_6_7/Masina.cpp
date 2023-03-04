#include "Masina.h"

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

string Masina::toString()const {
	string text;
	text.append("Numar de inmatriculare: ");
	text.append(nrInmatriculare);
	text.append(" Producator: ");
	text.append(producator);
	text.append(" Model: ");
	text.append(model);
	text.append(" Tip: ");
	text.append(tip);
	return text;
}
