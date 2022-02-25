"""
10. Pentru un număr natural n dat găsiți numărul natural minim m format cu
 aceleași cifre. Ex. n=3658, m=3568.
"""
n = input("dati numarul: ")
n = int(n)

m =[]
i=1
while n!=0:
    m.append(n%10)
    n = int(n/10)
    i += 1

m.sort()
nr = 0
for i in m:
    nr = nr *10 + i
print(nr)


