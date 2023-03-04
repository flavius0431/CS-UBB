#include "Spalatorie.h"
using std::shuffle;

void Spalatorie::addMasinaS(const Masina& m)
{
	this->spalatorie.push_back(m);
}


void Spalatorie::golesteS()
{
	this->spalatorie.clear();
}

void Spalatorie::addRandomMasini(vector<Masina> Masini, int numar)
{
	shuffle(Masini.begin(), Masini.end(), std::default_random_engine(std::random_device{}()));
	while (int(spalatorie.size()) < numar && int(Masini.size()) > 0) {
		spalatorie.push_back(Masini.back());
		Masini.pop_back();
	}
}

const vector<Masina>& Spalatorie::getAllMasiniS()
{
	return this->spalatorie;
}
