#include "consola.h"

#include"Masina.h"
#include<iostream>
#include<string>

using std::cout;
using std::cin;

void consola::tipareste( vector<Masina> v)
{
	cout << "Masini:\n";
	for (int i = 0; i < v.size();i++) {
		cout << ' ' << v[i].getnrInmatriculare() << ' ' << v[i].getproducator() << ' ' << v[i].getmodel() << ' ' << v[i].gettip() << '\n';
	}
	cout << "sfarsit lista masini\n";
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
	crt.addMasina(nrI, p, m, t);
	cout << "Adaugat cu succes!\n";
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


void consola::start() {
	while (true) {
		system("CLS");
		cout << "\nMeniu:\n";
		cout << "0 exit\n1 adauga\n2 tipareste\n3 sterge\n4 modifica\n5 cauta\nDati comanda: ";
		const int cmd= readInteger();
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
			default:
				cout << "Comanda invalida\n";

			}
		}
		catch ( repoException ex) {
			cout << ex.getMesaj() << '\n';
		}
		catch ( ValidateException ex) {
			cout << ex<< '\n';
		}
		
		system("PAUSE");
	}
}
