'''
7. Fie n un număr natural dat. Calculați produsul p al tuturor factorilor
 proprii ai lui n.
'''

n = input ("Dati un numat: ")
n = int(n)
d = int(2)
p = int(1)

while n > 1:
    k=int(0)
    while n % d == 0:
        n = n / d
        k+=1
    if k != 0:
        p=p*k
    d=d+1
     
input(p)       
