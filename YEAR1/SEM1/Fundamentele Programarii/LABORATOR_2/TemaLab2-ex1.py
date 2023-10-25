#1. Găsiți primul număr prim mai mare decât un număr dat.
import math

def prim(m):
    if m < 2: return 0
    if m == 2: return 1
    if m % 2 == 0: return 0
    for i in range(3, int(math.sqrt(m))+1,2):
        if m % i == 0:return 0
        return 1

n = input("Dati numarul: ")
n = int(n)
'''
n=n+1

while prim(n) == 0:
    n=n+1
    '''

print("Primul numar prim mai mare decat  n este: ", n)

    
   
