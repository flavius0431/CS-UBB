#include "service.h"
#include<functional>
#include<algorithm>
#include<assert.h>
#include<fstream>

void service::addMasina(string nrInmatriculare,  string producator, string model, string tip) {
	Masina m{ nrInmatriculare,producator,model,tip };
	val.validate(m);
	rep.inchiriere(m);
	undoActiuni.push_back(std::make_unique<UndoAdauga>(rep, m));
}

void service::deleteMasina( string nrInmatriculare) {
	Masina m{"","","",""};
	const auto& masini = rep.getAll();
	for (const auto& masina : masini)
		if (masina.getnrInmatriculare() == nrInmatriculare)
			m = masina;
	rep.sterge(m);
	undoActiuni.push_back(std::make_unique<UndoSterge>(rep, m));
}

void service::modifyMasina(string nrInmatriculare1, string nrInmatriculare2, string producator, string model, string tip) {
	Masina m1{ nrInmatriculare1,"","","" };
	Masina m2{ nrInmatriculare2,producator,model,tip };
	const auto& masini = rep.getAll();
	for (const auto& masina : masini) {
		if (masina.getnrInmatriculare() == m1.getnrInmatriculare())
		{
			m1 = masina;
		}
	}
	rep.modifica(m1, m2);
	undoActiuni.push_back(std::make_unique<UndoModify>(rep, m1, m2));
}

bool service::cautaMasina(string nrInmatriculare) {
	Masina m{ nrInmatriculare,"","","" };
	return rep.cauta(m);
}

bool service::exportFile(const string filename, const string type)
{
	string nume_fis = filename + '.' + type;
	std::ofstream fout(nume_fis);
	auto lista = spalatorie.getAllMasiniS();
	if (type == "html" || type == "csv") {
		for (auto masina:lista )
		{
			fout << "Numar de inmatriculare: " << masina.getnrInmatriculare() << " Producator: " << masina.getproducator() << " Model: " << masina.getmodel() << " Tip:" << masina.gettip() << '\n';}
		return true;
	}
	return false;
}


vector<Masina> service::filtrareDupaTip(string tip)
{
	const vector<Masina>& copyAll = getAll();
	vector<Masina> filtrareTip;
	std::copy_if(copyAll.begin(), copyAll.end(), back_inserter(filtrareTip), [tip](const Masina& m) {
		return m.gettip() == tip;
		});
	return filtrareTip;

}

vector<Masina> service::filtrareDupaProducator(string producator)
{
	const vector<Masina>& copyAll = getAll();
	vector<Masina> filtrareProducator;
	std::copy_if(copyAll.begin(), copyAll.end(), back_inserter(filtrareProducator), [producator](const Masina& m) {
		return m.getproducator() == producator;
	});
	return filtrareProducator;
}

//vector<Masina> service::generalSort(bool(*cmp)(const Masina&, const Masina&))
//{
//	vector<Masina> v{ rep.getAll() };
//	for (int i = 0; i < v.size(); i++) {
//		for (int j = i + 1; j < v.size(); j++) {
//			if (!cmp(v[i], v[j])) {
//				Masina aux = v[i];
//				v[i] = v[j];
//				v[j] = aux;
//			}
//		}
//	}
//	return v;
//}

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

bool ComaparaCrescatorProducatorModel(const Masina& m1, const Masina& m2) {
	if (m1.getproducator() < m2.getproducator()) {
		return 1;}
	else if (m1.getproducator() == m2.getproducator() && m1.getmodel() < m2.getmodel()) {
		return 1;
	}
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


vector<Masina> service::SortByNrInmatriculare(int optiune)
{
	auto copyAll = rep.getAll();
	if (optiune == 0) {
		 sort(copyAll.begin(), copyAll.end(), ComparaCrescatorNrInmatriculare);
	}
	else if (optiune==1) {
		 sort(copyAll.begin(), copyAll.end(), ComparaDescrescatorNrInmatriculare);
	}
	return copyAll;
}

vector<Masina> service::SortByTip(int optiune)
{
	auto copyAll = rep.getAll();
	if (optiune == 0) {
		sort(copyAll.begin(),copyAll.end(),ComparaCrescatorTip);}
	else if(optiune ==1 ) {
		sort(copyAll.begin(),copyAll.end(),ComparaDescrescatorTip);}
	return copyAll;
}

vector<Masina> service::SortByProducatorModel(int optiune)
{
	auto copyAll = rep.getAll();
	if (optiune == 0) {
		sort(copyAll.begin(),copyAll.end(),ComaparaCrescatorProducatorModel);}
	else if(optiune==1) {
		sort(copyAll.begin(),copyAll.end(),ComparaDescrescatorProducatorModel);}
	return copyAll;
}

void service::addtoSpalatorie(string nrI)
{
	const auto& masina = rep.find(nrI);
	spalatorie.addMasinaS(masina);

}

void service::emptySpalatorie()
{
	spalatorie.golesteS();
}

int service::addRandomToSpalatorie(int numar)
{
	spalatorie.addRandomMasini(this->getAll(), numar);
	return int(spalatorie.getAllMasiniS().size());
}

const vector<Masina>& service::getAllOferteSpalatorie()
{
	return spalatorie.getAllMasiniS();
}

void service::undo()
{
	if (undoActiuni.empty())
		throw repoException{ "Nu mai exista operatii\n" };
	undoActiuni.back()->doUndo();
	undoActiuni.pop_back();
}

int service::count_masini(string producator) noexcept
{
	
	vector<Masina> Copy{ rep.getAll() };
	int nr =(int) std::count_if(Copy.begin(), Copy.end(), [producator](const Masina& m) {
		return m.getproducator() == producator;
		});
	return nr;
	
}




