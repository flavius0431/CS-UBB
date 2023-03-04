#include "service.h"
#include<functional>
#include<algorithm>
#include<assert.h>
#include"vector.h"

void service::addMasina(string nrInmatriculare,  string producator, string model, string tip) {
	Masina m{ nrInmatriculare,producator,model,tip };
	val.validate(m);
	rep.inchiriere(m);
}
/// <summary>
/// 
/// </summary>
/// <param name="nrInmatriculare"></param>
void service::deleteMasina( string nrInmatriculare) {
	Masina m{"","","",""};
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

