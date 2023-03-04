#include "consola.h"

#include"Masina.h"
#include<iostream>
#include<string>
#include<fstream>

using std::cout;
using std::cin;

void consola::tipareste(const vector<Masina>& masini)
{
	cout << "Masini:\n";
	for (const auto& masina : masini) {
		cout << ' ' << masina.getnrInmatriculare() << ' ' << masina.getproducator() << ' ' << masina.getmodel() << ' ' << masina.gettip()<<'\n';
	}
	cout << "sfarsit lista masini\n";
}

void consola::filtrare() {
	cout << "0 dupa producator\n1 dupa tip\n";
	int nr;
	cin >> nr;
	if (nr == 0) {
		string producator;
		cout << "Producator: ";
		cin >> producator;
		if (crt.filtrareDupaProducator(producator).size() == 0) {
			cout << "Nu exista Masini cu acest producator!\n";
		}
		else {
			tipareste(crt.filtrareDupaProducator(producator));
		}

	}if (nr == 1) {
		string tip;
		cout << "Tip: ";
		cin >> tip;
		if (crt.filtrareDupaTip(tip).size() == 0) {
			cout << "Nu exista Masini cu acest tip!\n";
		}
		else {
			tipareste(crt.filtrareDupaTip(tip));
		}

	}

}

void consola::sortare() {
	int optiune;
	cout << "\n0 sortare dupa numarul de inmatriculare\n1 sortare dupa tip\n2 sortare dupa producator si model\n";
	cin >> optiune;
	if (optiune == 0) {
		int nr;
		cout << "\n0 crescator\n1 descrescator\n";
		cin >> nr;
		tipareste(crt.SortByNrInmatriculare(nr));
	}
	else if (optiune == 1) {
		int nr;
		cout << "\n0 crescator\n1 descrescator\n";
		cin >> nr;
		tipareste(crt.SortByTip(nr));
	}
	else if (optiune == 2) {
		int nr;
		cout << "\n0 crescator\n1 descrescator\n";
		cin >> nr;
		tipareste(crt.SortByProducatorModel(nr));
	}
}



void consola::adaugaUI() {
	string nrI, p, t,m;
	
	cout << "Numarul de Inmatriculare: ";
	cin >> nrI;
	cout << "Producator: ";
	cin >> p;
	cout << "Model: ";
	cin >> m;
	cout << "Tipul: ";
	cin >> t;
	try {
		crt.addMasina(nrI, p, m, t);
		cout << "Adaugat cu succes!\n";
	}
	catch (repoException ex) {
		std::cout << ex.getMesaj() << '\n';
	}
	
}

void consola::stergeUI() {
	string nrI;
	cout << "Numarul de Inmatriculare: ";
	cin >> nrI;
	crt.deleteMasina(nrI);
	cout << "Sters cu succes!\n";
	
}

void consola::modificaUI() {
	string nrI1, nrI2, p, m, t;

	cout << "Numarul de Inmatriculare a masinei de inlocuit: ";
	cin >> nrI1;
	cout << "Numarul de Inmatriculare: ";
	cin >> nrI2;
	cout << "Producator: ";
	cin >> p;
	cout << "Model: ";
	cin >> m;
	cout << "Tipul: ";
	cin >> t;
	crt.modifyMasina(nrI1, nrI2, p, m, t);
	cout << " Masina a fost modificata cu succes!\n";
}

bool consola::cautaUI() {
	string nrI;
	cout << "Numarul de Inmatriculare: ";
	cin >> nrI;
	const bool v = crt.cautaMasina(nrI);
	if (v == true) {
		cout << "Masina cu numarul de inmatriculare: " << nrI << " se afla in inchirieri\n";
	}
	else {
		cout << "Masina cu numarul de inmatriculare: " << nrI << " nu se afla in inchirieri\n";
	}
	return 0;
}

int readInteger()
{
	int number = -1;

	
	std::cin >> number;

	while (std::cin.fail())
	{
		std::cout << "Citeste un numar!\n";
		std::cin.clear();
		std::cin.ignore(256, '\n');
		system("CLS");
		std::cout << "\nMeniu:\n";
		std::cout << "0 exit\n1 adauga\n2 tipareste\n3 sterge\n4 modifica\n5 cauta\nDati comanda: ";
		std::cin >> number;
		
	}

	return number;
}
/// eroare??
void consola::addSpalatoorieUI()
{
	string nrI;
	cout << "Numarul de inmatriculare: ";
	cin >> nrI;
	try {
		crt.addtoSpalatorie(nrI);
		std::cout << "Masina s-a adaugat in lista de la spalatorie\n";
	}
	catch (repoException ex) {
		std::cout << ex.getMesaj() << '\n';
	}
}

void consola::addRandomSpalatorieUI()
{
	int numar;
	char str[1001];
	std::cout << "Cate Masini sa se adauge in lista de la spalatorie?\n";
	cin >> str;
	numar = std::stoi(str);
	if (!((numar < 0 || strlen(str)>10 || strlen(str) == 10) && str[0] > '2'))
	{
		try {
			int adaugate = crt.addRandomToSpalatorie(numar);
			std::cout << "s-au adaugat " << adaugate << "masini in spalatorie\n";
		}
		catch (repoException ex) {
			std::cout << ex.getMesaj() << '\n';
		}

	}
	else
		std::cout << "trebuie sa nu introduceti caractere si  numarul sa fie mai mic!\n";
}

void consola::emptySpalatorieUI()
{
	crt.emptySpalatorie();
	cout << "s-au sters toate masinile din lista de spalatorie\n";
}

void consola::comenziUI(string filename)
{
	string line, comanda;
	std::ifstream fin(filename);
	const string delimitator = ",";
	while (fin >> comanda) {
		if(comanda =="print")
			tipareste(crt.getAll());
		else
		{
			std::getline(fin, line);
			line.erase(0, 1);
			std::vector<string> v;
			string token = line.substr(0, line.find(delimitator));
			size_t pos = 0;
			while((pos=line.find(delimitator))!= string ::npos){
				token = line.substr(0, pos);
				v.push_back(token);
				line.erase(0, pos + delimitator.length());
			}
			if (line != "")
				v.push_back(line);
			if (comanda == "add") {
				if (v.size() != 4) {
					std::cout << "comanda invalida\n";
					continue;
				}
				try {
					crt.addMasina(v[0], v[1], v[2], v[3]);
					std::cout << "masina  adaugata cu succes\n";
				}
				catch (repoException ex) {
					std::cout << ex.getMesaj() << '\n';
				}
			}
			if (comanda == "delete"){
				if (v.size()!=1){
					std::cout << "Comanda invalida\n";
					continue;
				}
				try {
					crt.deleteMasina(v[0]);
					std::cout << "Masina s-a sters cu succes!\n";
				}
				catch (repoException ex) {
					std::cout << ex.getMesaj();
				}
			}
			if (comanda == "update") {
				if (v.size() != 5){
					std::cout << "Comanda invalida!\n";
					continue;
				}
				try {
					crt.modifyMasina(v[0], v[1], v[2], v[3], v[4]);
					std::cout << "Masina a fost modificata";
				}
				catch (repoException ex) {
					std::cout << ex.getMesaj();
				}
			}
		}

	}

}

void consola::exportMasini()
{
	string filename, type;
	cout << "Introduceti numele fisierului: ";
	cin >> filename;
	cout << "Introduceti extensia fisierului(html/csv): ";
	cin >> type;
	if (crt.exportFile(filename, type))
		cout << "Export cu succes!\n";
	else
		cout << "Export esuat!\n";
}


//meniu spalatorie
void consola::spalatorie()
{
	while (true) {
		system("CLS");
		cout << "\nMeniu:\n";
		cout << "0 exit\n1 adauga\n2 adaugaRandom\n3 goleste\n4 afiseaza\n5 export\nDati comanda: ";
		const int cmd = readInteger();
		switch (cmd) {
		case 0:
			return;
		case 1:
			addSpalatoorieUI();
			break;
		case 2:
			addRandomSpalatorieUI();
			break;
		case 3:
			emptySpalatorieUI();
			break;
		case 4:
			tipareste(crt.getAllOferteSpalatorie());
			break;
		case 5:
			exportMasini();
			break;
		default:
			cout << "Comanda invalida\n";
		}
		system("PAUSE");
	}
}

void consola::start() {
	while (true) {
		system("CLS");
		cout << "\nMeniu:\n";
		cout << "0 exit\n1 adauga\n2 tipareste\n3 sterge\n4 modifica\n5 cauta\n6 filtrare\n7 sorteaza\n8  meniu spalatorie\n9 comenzi string\n10 undo\nDati comanda: ";
		const int cmd = readInteger();
		try {
			switch (cmd) {
			case 0:
				return;
			case 1:
				adaugaUI();
				break;

			case 2:
				tipareste(crt.getAll());
				break;
			case 3:
				stergeUI();
				break;
			case 4:
				modificaUI();
				break;
			case 5:
				cautaUI();
				break;
			case 6:
				filtrare();
				break;
			case 7:
				sortare();
				break;
			case 8:
				spalatorie();
				break;
			case 9:
				comenziUI("linii.txt");
				break;
			case 10:
				try {
					crt.undo();
					cout << "undo realizat cu succes\n";
				}
				catch (repoException& e) {
					cout << e.getMesaj();
				}
				break;
			default:
				cout << "Comanda invalida\n";

			}
		}
		catch (repoException ex) {
			cout << ex.getMesaj() << '\n';
		}
		catch (ValidateException ex) {
			cout << ex << '\n';
		}
		catch (std::string error) {
			cout << error << "\n";
		}

		system("PAUSE");
	}
}