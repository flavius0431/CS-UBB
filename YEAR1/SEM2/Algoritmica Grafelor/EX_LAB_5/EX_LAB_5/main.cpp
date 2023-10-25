#include <iostream>
#include <fstream>
#include <vector>
#include <stack>
using namespace std;

ifstream fin("1-in.txt");
ofstream fout("out.txt");

int varfuri, arce, radacina = 0, t, maxFlow = 0;
vector<vector<int>> capacitate, flow;

void citire() {
	int x, y, ecapacitate;
	fin >> varfuri >> arce;
	capacitate.assign(varfuri, vector<int>(varfuri, 0));
	flow.assign(varfuri, vector<int>(varfuri, 0));
	t = varfuri - 1;
	for (int i = 0; i < arce; i++) {
		fin >> x >> y >> ecapacitate;
		capacitate[x][y] = ecapacitate;
		capacitate[y][x] = ecapacitate;
		flow[x][y] = 0;
		flow[y][x] = ecapacitate;
	}
}

void DFS(int radacina, bool vizitat[], int parinti[]) {
	vizitat[radacina] = true;
	for (int i = 0; i < varfuri; i++) {
		if (!vizitat[i] && capacitate[radacina][i] - flow[radacina][i] > 0) {
			parinti[i] = radacina;
			DFS(i, vizitat, parinti);
		}
	}
}

int redoflux(int parinti[]) {
	int curent = t, previous = -1, flux = 0;
	stack<int> drum;
	while (curent != -1) {
		drum.push(curent);
		if (previous != -1) {
			flux = (flux == 0) ? (capacitate[curent][previous] - flow[curent][previous]) : min(flux, capacitate[curent][previous] - flow[curent][previous]);
		}
		previous = curent;
		curent = parinti[curent];
	}
	int x, y;
	while (!drum.empty()) {
		x = drum.top();
		drum.pop();
		if (!drum.empty()) {
			y = drum.top();
			flow[x][y] += flux;
			flow[y][x] -= flux;
		}
	}
	return flux;
}

void FordFulkerson() {
	bool vizitat[1000];
	int parinti[1000], d = -1, maxFlow = 0, flux;
	vizitat[t] = true;
	while (vizitat[t]) {
		for (int j = 0; j < varfuri; j++) {
			vizitat[j] = false;
			parinti[j] = -1;
		}
		DFS(radacina, vizitat, parinti);
		if (vizitat[t]) {
			flux = redoflux(parinti);
			maxFlow += flux;
		}
	}
	cout << maxFlow;
}

int main() {
	citire();
	FordFulkerson();
	fin.close();
	fout.close();
	return 0;
}