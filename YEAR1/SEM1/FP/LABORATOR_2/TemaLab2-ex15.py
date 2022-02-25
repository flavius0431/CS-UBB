'''
15. Găsiți cel mai mare număr prim mai mic decât un număr dat. Daca nu exista un astfel de număr,
tipăriți un mesaj.
'''
import math
def prim(m):
    if m < 2:return 0
    if m == 2: return 1
    if m % 2 == 0: return 0
    for i in range(3, int(math.sqrt(m))+1,2):
        if m % i == 0:return 0
        return 1

n = input("dati un numar: ")
n = int(n)

n = n - 1
while prim(n) == 0:
    n-=1
print(n)

        
    
