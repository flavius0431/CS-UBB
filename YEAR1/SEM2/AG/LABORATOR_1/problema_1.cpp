#include<iostream>
#include<fstream>
#define INF 100000

using namespace std;

ifstream fin("in.txt");



void DFS(int c[101][101], int n, int v[101], int k)
{
	v[k] = 1;
	for (int i = 1; i <= n; i++)
	{
		if (c[k][i] == 1 && v[i] == 0)
		{
			DFS(c, n, v, i);
		}
	}
}

bool conex(int c[101][101], int n)
{
	int v[101] = {};
	DFS(c, n, v, 1);
	for (int i = 1; i <= n; i++)
	{
		if (v[i] == 0)
			return false;
	}
	return true;
}


void roy_floyd(int a[101][101],int n)
{
	int i, j, k;
	for (k = 1; k <= n; k++)
	{
		for (i = 1; i <= n; i++)
		{
			for (j = 1; j <= n; j++)
			{
				if (a[i][k] != INF && a[k][j] != INF)
				{
					if (a[i][j] > a[i][k] + a[k][j])
					{
						a[i][j] = a[i][k] + a[k][j];
					}
				}
			}
		}
	}
}


void matricea_distantelor(int a[101][101], int n, int roy[101][101])
{
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			if (i != j and a[i][j] == 0)
			{
				roy[i][j] = INF;
			}
			else
			{
				roy[i][j] = a[i][j];
			}
		}
	}
	roy_floyd(roy, n);
}

int main() {

	int a[101][101] = {}, b[101][101] = {};
	int n, i, j,nr,nr1;
	fin >> n;
	int k = 0;
	
	//citirea muchiilor din fisier
	while (fin >> nr>>nr1)
	{
		k++;
		a[nr][nr1] = a[nr1][nr] = 1;
	}
	
	cout << "MATRICEA DE ADIACENTA\n";
	//matricea de adiacenta
	for (i = 1; i <= n; i++)
	{
		for (j = 1; j <= n; j++)
		{
			cout << a[i][j] << " ";
		}
		cout << '\n';
		
	}
	
	//matricea de incidenta(din matricea de adiancenta)
	cout << "MATRICEA DE INCIDENTA\n";
	int muchie = 1;
	for (i = 1; i <= n; i++)
	{
		for (j = 1; j <= n; j++)
		{
			if(i < j)
			{ 
				if (a[i][j] == 1)
				{
					
					b[muchie][i] = b[muchie][j] = 1;
					b[i][muchie] = b[j][muchie] = 1;
					muchie++;
				}
			}
		}
	}
	

	for (i = 1; i <= n; i++)
	{
		for (j = 1; j < muchie; j++)
		{
			cout << b[i][j] << " ";
		}
		cout << '\n';
	}
	

	//exercitiul 2
	//determina varfurile izolate
	cout << '\n';
	for (i = 1; i <= n; i++)
	{
		int vfiz = 0;
		for (j = 1; j <= n; j++)
		{
			if (a[i][j] !=0)
			{
				vfiz = 1;

			}
			
		}
		if (vfiz == 0)
		{
			cout << "varful " << i << " este izolat"<<'\n';
		}
	}

	//verifica gr regulat= un graf este regulat daca are acelasi grad pentru toate vf

	cout << '\n';
	int reg[10];
	for (i = 1; i <= n; i++)
	{
		for (j = 1; j <= n; j++)
		{
			if (a[i][j] != 0)
				reg[i]=1;
		}
	}
	int ok = 0;
	for (i = 1; i < n; i++)
	{
		if (reg[i] != reg[i + 1])
			ok = 1;
	}
	if (ok == 0)
		cout << "graf regulat";
	else
		cout << "graf neregulat";



	cout << '\n';
	//verifica graf conex=verifica toate vf vizitate

	if (conex(a,n) == true)
		cout << "graf conex\n";
	else
		cout << "graful nu e conex\n";



	cout << "\n MATRICEA DISTANTELOR\n";
	//c. pentru un graf reprezentat cu matricea de adiacenta sa se determine matricea distantelor.
	int roy[101][101];
	matricea_distantelor(a,n,roy);
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			cout << roy[i][j] << " ";
		}
		cout << '\n';
	}


	return 0;
}
