"""
8. Pentru un număr natural n dat găsiți numărul natural maxim m format cu
 aceleași cifre. Ex. n=3658, m=8653.
"""

n = input("Dati numarul natural: ")
n = int(n)
c= int(0)
m=0
mylist=[]

while n!=0:
    mylist.append(n%10)
    n = int(n /10)
    c+=1
print(c)
mylist.sort(reverse= True)
for i in range(c):
    m = m * 10+ mylist[i]
 
print(m)

