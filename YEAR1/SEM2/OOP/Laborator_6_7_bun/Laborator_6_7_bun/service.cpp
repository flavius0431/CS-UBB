#include "service.h"

#include<functional>
#include<algorithm>
#include<assert.h>
#include"vector.h"
#include"Masina.h"

void service::addMasina(string nrInmatriculare, string producator, string model, string tip) {
	Masina m{ nrInmatriculare,producator,model,tip };
	val.validate(m);
	rep.inchiriere(m);
}


void service::deleteMasina(string nrInmatriculare) {
	Masina m{ "","","","" };
	auto all = rep.getAll();
	for (int i = 0; i < all.size(); i++)
		if (all[i].getnrInmatriculare() == nrInmatriculare)
			m = all[i];
	rep.sterge(m);

}

void service::modifyMasina(string nrInmatriculare1, string nrInmatriculare2, string producator, string model, string tip) {
	Masina m1{ nrInmatriculare1,"","","" };
	Masina m2{ nrInmatriculare2,producator,model,tip };
	auto masini = rep.getAll();
	val.validate(m2);
	for (const auto& masina : masini) {
		if (masina.getnrInmatriculare() == m1.getnrInmatriculare())
		{
			m1 = masina;
		}
	}
	rep.modifica(m1, m2);
}

bool service::cautaMasina(string nrInmatriculare) {
	Masina m{ nrInmatriculare,"","","" };
	return rep.cauta(m);
}

vector<Masina> service::filtrareDupaTip(string tip) {
	vector<Masina> filt;
	auto all = rep.getAll();
	for (int i = 0; i < all.size(); i++) {
		if (all[i].gettip() == tip) {
			filt.push_back(all[i]);
		}
	}
	return filt;
}

vector<Masina> service::filtrareDupaProducator(string producator) {
	vector<Masina> filt;
	auto all = rep.getAll();
	for (int i = 0; i < all.size(); i++) {
		if (all[i].getproducator() == producator) {
			filt.push_back(all[i]);
		}
	}
	return filt;
}



bool ComparaCrescatorNrInmatriculare(const Masina& m1, const Masina& m2)
{
	if (m1.getnrInmatriculare() < m2.getnrInmatriculare())
		return 1;
	return 0;
}

bool ComparaDescrescatorNrInmatriculare(const Masina& m1, const Masina& m2)
{
	if (m1.getnrInmatriculare() > m2.getnrInmatriculare())
		return 1;
	return 0;
}

bool ComparaCrescatorTip(const Masina& m1, const Masina& m2)
{
	if (m1.gettip() < m2.gettip())
		return 1;
	return 0;
}

bool ComparaDescrescatorTip(const Masina& m1, const Masina& m2)
{
	if (m1.gettip() > m2.gettip())
		return 1;
	return 0;
}

bool ComaparaCrescatorProducatorModel(const Masina& m1, const Masina& m2){
	if (m1.getproducator() < m2.getproducator()) {
		return 1;}
	else if (m1.getproducator() == m2.getproducator() && m1.getmodel() < m2.getmodel()) {
		return 1;}
	return 0;
}

bool ComparaDescrescatorProducatorModel(const Masina& m1, const Masina& m2)
{
	if (m1.getproducator() > m2.getproducator()) {
		return 1;}
	else if (m1.getproducator() == m2.getproducator() && m1.getmodel() > m2.getmodel()) {
		return 1;
	}
	return 0;

}

vector<Masina> service::generalSort(bool(*cmp)(const Masina&, const Masina&))
{
	vector<Masina> v{ rep.getAll() };
	for (int i = 0; i < v.size(); i++) {
		for (int j = i + 1; j < v.size(); j++) {
			if (!cmp(v[i], v[j])) {
				Masina aux = v[i];
				v[i] = v[j];
				v[j] = aux;
			}
		}
	}
	return v;
}

vector<Masina> service::SortByNrInmatriculare(int optiune)
{
	auto copyAll= rep.getAll();
	if (optiune == 0) {
		return generalSort(ComparaCrescatorNrInmatriculare);}
	else if (optiune == 1) {
		return generalSort(ComparaDescrescatorNrInmatriculare);}}

vector<Masina> service::SortByTip(int optiune)
{
	auto copyAll = rep.getAll();
	if (optiune == 0) {
		return generalSort(ComparaCrescatorTip);}
	else if (optiune == 1) {
		return generalSort(ComparaDescrescatorTip);}}

vector<Masina> service::SortByProducatorModel(int optiune)
{
	auto copyAll = rep.getAll();
	if (optiune == 0) {
		return generalSort(ComaparaCrescatorProducatorModel);}
	else if (optiune == 1) {
		return generalSort(ComparaDescrescatorProducatorModel);}}
