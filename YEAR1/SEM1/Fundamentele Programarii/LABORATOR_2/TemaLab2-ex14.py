"""
14. Generați cel mai mic număr perfect mai mare decât un număr dat. Un număr este perfect daca
este egal cu suma divizorilor proprii. Ex. 6 este un număr perfect (6=1+2+3).
"""
import math
def sum_Div(n):
    s = 1
    for d in range(2,int(n/2)+1):
        if n % d == 0: s=s+d
    return s



    
n = input("da numarul: ")
n = int(n)
n = n + 1
while sum_Div(n) != n:
    n+=1

print (n)
