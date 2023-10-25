'''4. Dându-se numărul natural n, determina numerele prime p1 si p2 astfel ca
 n = p1 + p2
 (verificarea ipotezei lui Goldbach). Pentru ce fel de n exista soluție?'''

n = input ("Citeste un numar: ")
n = int(n)
if n % 2 == 0 and n >=3:
    print("Verifica Ipoteza lui Goldbach")
else:
    print("Nu verifica Ipoteza lui Goldbach")

    
def prim(m):
    if m < 2:return 0
    if m ==2: return 1
    if m % 2 == 0:return 0
    for i in range(3,m+1,2):
        if m % i ==0:return 0
        return 1
      
p1=int (2)
while p1 <= n/2:
    if prim(p1)==1 and prim(n-p1)==1:
        print(p1,"  ",n-p1)
    p1+=1
        


       
           


   
