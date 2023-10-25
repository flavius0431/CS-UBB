import random
def generare():
    cuvant=''
    consoane=['q','w','r','t','y','p','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m']
    vocale=['a','e','i','o','u']
    c=random.randint(4,6)
    for i in range(c):
        cuvant = cuvant+consoane[random.randint(0,len(consoane)-1)]
        cuvant = cuvant +vocale[random.randint(0,len(vocale)-1)]
        if i ==1:
            cuvant=cuvant+' '
    return cuvant


def aleatoriu():
    nr_generari=int(input('Nr generari: '))
    for i in range(nr_generari):
        print(generare())


aleatoriu()



