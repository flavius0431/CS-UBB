#pragma once

#include"service.h"
#include"Masina.h"

class consola{
	service& crt;

	void adaugaUI();

	void stergeUI();

	void modificaUI();

	bool cautaUI();

	void tipareste(const vector<Masina>& masini);

	void filtrare();

	void sortare();

	void spalatorie();

	void addSpalatoorieUI();
	
	void addRandomSpalatorieUI();
	
	void emptySpalatorieUI();

	void comenziUI(string filename);

	void exportMasini();

public:
	consola(service& crt)noexcept : crt{ crt } {
	}
	//nu permitem copierea obiectelor
	consola(const consola& ot) noexcept = delete;

	void start();
};

