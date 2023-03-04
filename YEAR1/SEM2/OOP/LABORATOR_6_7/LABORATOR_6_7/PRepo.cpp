#include"repo.h"
#include"errors.h"

//crapa?

void PRepo::pass()const
{
	double r = ((double)rand() / (RAND_MAX));
	if (r > prob)
		throw repoException("Nu se poate executa functia.Mai incearca o data!");}

void PRepo::setProb(double f)
{
	prob = f;
}

void PRepo::inchiriere(const Masina& m)
{
	pass();
	if (cauta(m)) {
		throw repoException("Masina exista deja!!\n");
	}
	int index = int(masini.size());
	Masina of = m;
	masini.insert(std::make_pair(index, of));
}

void PRepo::sterge(const Masina& m)
{
	pass();
	int i = 0;
	while (i < int(this->masini.size()) && this->masini.at(i).getnrInmatriculare() != m.getnrInmatriculare()) {
		i++;
	}
	if (i > int(this->masini.size()))throw repoException("Masina nu exista!\n");
	else
	{
		for (int j = i; j < int(masini.size()) - 1; ++j) {
			masini[j] = masini[j + 1];
		}
		masini.erase((int)masini.size() - 1);
	}
}

const Masina& PRepo::find(string nrI)
{
	pass();
	auto it = find_if(masini.begin(), masini.end(), [=](const std::pair<int, Masina> masina) { return masina.second.getnrInmatriculare() == nrI; });

	if (it == masini.end())
		throw repoException("Masina cautata nu exista");
	
	return (*it).second;
}

bool PRepo::cauta(const Masina& m)
{
	pass();
	for (int i = 0; i < int(masini.size()); ++i) {
		if (masini[i].getnrInmatriculare() == m.getnrInmatriculare())
			return true;}
	return false;
}

void PRepo::modifica(Masina& m1, const Masina& m2) {
	pass();
	int i = 0;
	while (i < int(this->masini.size()) && this->masini.at(i).getnrInmatriculare() != m1.getnrInmatriculare()) { i++; }
	if (i > int(this->masini.size()))throw repoException("Masina nu exista");
	else
		masini[i] = m2;
}

const vector<Masina>& PRepo::getAll()
{
	pass();
	vector<Masina> m;
	for (auto& o : masini) { m.push_back(o.second); }
	MasiniV = m;
	return MasiniV;
}




