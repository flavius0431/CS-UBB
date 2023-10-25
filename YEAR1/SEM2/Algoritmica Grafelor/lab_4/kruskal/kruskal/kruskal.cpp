#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

struct Muchie;

struct Muchie {
	int x, y; //varfuri
	int w; //pondere
	int nr; //nr de ordine

	bool operator==(const Muchie& other) {
		return x == other.x && y == other.y && w == other.w;
	}
};

int main(int argc, char** argv)
{
	ifstream fin("in2.txt");
	ofstream fout("out2.txt");

	int cost{ 0 }, V{ 0 }, E{ 0 };
	fin >> V >> E;
	vector<Muchie> muchie;

	//citire
	int x{ 0 }, y{ 0 }, w{ 0 };
	for (int i = 0; i < E; ++i) {
		fin >> x >> y >> w;
		muchie.push_back(Muchie{ x, y, w, i });
	}

	//vector sortat de muchii (in functie de pondere)
	vector<Muchie> srtMuchie = muchie;
	sort(srtMuchie.begin(), srtMuchie.end(), [](Muchie& m1, Muchie& m2) {return m1.w < m2.w; });

	//reprezentantii fiecarui varf
	vector<int> inSet(V);
	for (int i = 0; i < V; ++i)
		inSet[i] = i;

	//kruskal
	vector<bool> inMST(E, false);
	int i = 0;
	for (auto& m : srtMuchie) {
		if (inSet[m.x] != inSet[m.y]) {
			inMST[m.nr] = true;

			cost += m.w;

			int ai = inSet[m.x], aj = inSet[m.y];

			for (int j = 0; j < V; ++j)
				if (inSet[j] == aj)
					inSet[j] = ai;
		}
		i++;
	}

	//afisare
	cout << cost << endl << V - 1 << endl;
	i = 0;
	for (auto& m : muchie) {
		if (inMST[i])
			cout << m.x << " " << m.y << endl;
		i++;
	}

	return 0;
}