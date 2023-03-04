#include <iostream>
#include <fstream>
#include <vector>
#include <queue>

using namespace std;

int main(int argc, char** argv)
{
	ifstream fin("in1.txt");
	ofstream fout("out1.txt");

	int nPrufer{ 0 };
	fin >> nPrufer;

	queue<int> K;
	vector<int> inK(nPrufer + 1, 0);
	vector<int> parinte(nPrufer + 1, -1);

	int nr{ 0 };
	while (fin >> nr) {
		K.push(nr);
		inK[nr]++;
	}

	for (int i = 1; i <= nPrufer; ++i) {
		int x{ 0 }, y{ 0 };
		x = K.front();

		for (int nod = 0; nod <= nPrufer; ++nod)
			if (!inK[nod]) {
				y = nod;
				break;
			}

		parinte[y] = x;
		inK[x]--;
		K.pop();

		K.push(y);
		inK[y]++;
	}

	cout << nPrufer + 1 << endl;
	for (int i = 0; i < nPrufer + 1; ++i)
		cout << parinte[i] << " ";


	return 0;
}