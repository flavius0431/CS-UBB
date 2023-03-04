#pragma once

#include"service.h"
#include"Masina.h"

class consola{
	service& crt;

	void adaugaUI();

	void stergeUI();

	void modificaUI();

	bool cautaUI();

	void tipareste( vector<Masina> masini);
public:
	consola(service& crt)noexcept : crt{ crt } {
	}
	//nu permitem copierea obiectelor
	consola(const consola& ot) noexcept = delete;

	void start();
};

