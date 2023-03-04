/*2. Genereaza primele n (n natural dat) numere prime.*/
#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>

int prim(int n)
{
	if (n < 2) return 0;
	if (n == 2)return 1;
	if (n % 2 == 0)return 0;
	for (int d = 3; d * d <= n; d = d + 2)
		if (n % d == 0) return 0;
	return 1;


}
int citire()
{
	int n;
	printf("Introduce numarul:");
	scanf("%d", &n);
	return n;
}

void afisare(int n)
{
	int i = 0,k=0;

	while (k < n)
	{
		if (prim(i) == 1) {
			k++;
			printf("%d ", i);
		}
		i++;
	}
	printf("\n");

}
void afisare_meniu()
{
	printf("1.Citire\n");
	printf("2.Exit\n");
}

int main() {
	int n, k = 0, s;
	while (k == 0)
	{
		afisare_meniu();
		printf("Comanda este: ");
		scanf("%d", &s);

		if (s == 1)
		{
			n = citire();
			afisare(n);
		}
		else
			if (s == 2)
			{
				k = 1;
				return 0;
			}
	}
}