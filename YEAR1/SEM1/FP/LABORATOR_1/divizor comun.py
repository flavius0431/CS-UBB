a= input(" primul numar ")
b= input("al doilea numar ")
a=int (a)
b=int(b)
if a == 0: print( b)
if b == 0 : print(a)

while a != b:

   if a > b:
      a = a - b
   else:
      b = b - a

print(a)
  
