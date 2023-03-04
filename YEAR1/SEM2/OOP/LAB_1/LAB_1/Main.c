//1. Genereaza numere prime mai mici decat un numar natural dat.
#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<string.h>

int prim(int n)
{
	/*
	* functia verifica daca un numar este prim
	* parm n: un numar intreg
	* n trebuie sa fie numar intreg
	* rezultatul  e de tip boolean
	*/
	if (n < 2) return 0;
	if (n == 2)return 1;
	if (n % 2 == 0)return 0;
	for (int d = 3; d * d <= n; d = d + 2)
		if (n % d == 0) return 0;
	    return 1;

	
}
int citire()
{
	/* functia citeste un numar de la tastatura
	* numarul citit  trebuie sa fie intreg
	* returneaza numarul citit 
	*/

	int n;
	printf("Introduce numarul:");
	scanf("%d", &n);
	return n;
}

void afisare(int n)
{
	/*
	* functia afiseaza numerele prime mai mici decat numarul n
	* parm n: numar intreg
	* n trebuie sa fie intrg
	*/
	for (int i = 1; i < n; i++)
		if (prim(i) == 1)
			printf("%d ", i);
	printf("\n");
}
void afisare_meniu()
{
	/*
	* functia afiseaza meniul
	*/
	printf("1.Citire\n");
	printf("2.Exit\n");
}

int main() {
	int n,k=0,s;
	while (k == 0)
	{
		afisare_meniu();
		printf("Comanda este:");
		scanf("%d", &s);

		if (s==1)
		{
			n = citire();
			afisare(n);
		}
		else
			if (s==2)
			{ 
				k = 1;
				return 0;
				
			}
				
	}

}