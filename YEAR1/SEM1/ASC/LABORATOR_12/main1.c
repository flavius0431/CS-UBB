#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


int determina_maxim(int*, int);
void citeste_numar(char*);
bool e_numar(char*);
void desparte_numar(char*, int[], int*);
void scrie_numar(int);


int arr[1024], len = 0;
char numere[1024];


int main()
{
    citeste_numar(numere);
    desparte_numar(numere, arr, &len);
    int maxim = determina_maxim(arr, len);
    scrie_numar(maxim);
        
    return 0;
}

void citeste_numar(char* numere)
{
    scanf("%1023[^\n]", numere);
}


void scrie_numar(int numar)
{
    FILE* fp;
    fp = fopen("max.txt", "w");
    fprintf(fp, "Numarul maxim este %x", numar);
    fclose(fp);
}

bool e_numar(char* string)
{
    
   

    for (int i = 0; i < strlen(string); i++)
    {
        if (!(string[i] >= '0' && string[i] <= '9'))
        {
            return false;
        }
    }

    return true;
}


void desparte_numar(char* numere, int array[], int* len)
{
    
    
    (*len) = 0;

    char* p = strtok(numere, " ");


    while (p)
    {
        if (!e_numar(p))
        {
            printf("Sari peste `%s` pentru ca nu este un numar valid\n", p);
        }
        else
        {
            array[(*len)++] = atoi(p);
        }

        p = strtok(NULL, " ");
    }
}