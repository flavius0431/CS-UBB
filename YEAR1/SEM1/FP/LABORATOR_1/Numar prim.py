a = input("Da numarul: ")
a = int (a)
ok = False

while ok == False:
    d = int (2)
    ok = True
    while d <= a/2:
        if a % d == 0:
            ok = False
        d+=1
   

if ok == True:
    print("numarul ", a,"  este prim")



