
#include <iostream>
#include<fstream>
using namespace std;
ifstream fin("matrice.in");

int a[11][11];

int main()
{

	int n;
	fin >> n;
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			fin >> a[i][j];
		}
	}
	//matricea de adiacenta citita de la tastatura
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++)
		{
			if (a[i][j] != 0) {
				cout << "[" << i << "," << j << "]"<<"\n";
			}
		}
	}
	//lista de muchii
	return 0;
}

