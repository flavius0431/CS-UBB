'''
5. Determina numerele prime p1 si p2 gemene imediat superioare numărului
 natural nenul n dat. Doua numere prime p si q sunt gemene
 daca q-p = 2.
'''
import math

def prim(m):
    if m < 2:return 0
    if m == 2:return 1
    if m % 2 == 0:return 0
    for i in range(3,int(math.sqrt(m))+1,2):
        if m % i == 0:return 0
        return 1
    
n = input("Da numarul: ")
n = int (n)
n+=1
while prim(n) != 1:
    n+=1

if prim(n+2) == 1:
    print(n, n+2)
else:
    print(" p1 si p2 nu sunt gemene")

