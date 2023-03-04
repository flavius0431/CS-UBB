#pragma once
#include"Masina.h"
#include<vector>
#include<algorithm>
#include<random>
#include<chrono>
using std::vector;

class Spalatorie{
private:
	vector<Masina> spalatorie;
public:
	Spalatorie() = default;

	void addMasinaS(const Masina& m);


	void golesteS();

	void addRandomMasini(vector<Masina> Masini, int numar);

	const vector<Masina>& getAllMasiniS();
};

