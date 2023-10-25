'''
1.Pentru listă de monede cu valorile a1,....,an, și o valoare S. Tipăriţi toate modalităţile de a
plăti suma S cu monedele disponibile. Tipăriți un mesaj dacă suma nu se poate plăti.
'''
from termcolor import colored


def isSet(modalitate_plata, s, monede):
    if len(modalitate_plata) >= 2:
        if modalitate_plata[-1] < modalitate_plata[-2]:
            return False

    copie = monede[:]
    for i in range(0, len(modalitate_plata)):
        ok = 0
        for j in range(0, len(copie)):
            if modalitate_plata[i] == copie[j]:
                copie.pop(j)
                ok = 1
                break

        if ok == 0:
            return False

    if sum(modalitate_plata) <= s:
        return True
    return False

#####
#recursiv
def backtracking(modalitate_plata, s, monede):
    ok = 0
    if sum(modalitate_plata) == s:
        ok = 1
        print(modalitate_plata)
        return
    modalitate_plata.append(0)
    for i in range(0, len(monede)-1):
        modalitate_plata[-1] = monede[i]
        if isSet(modalitate_plata, s, monede):
            backtracking(modalitate_plata, s, monede)
        modalitate_plata.pop()
    if ok == 0:
        print('nu se poate face suma!')


########
#iterativ
def backIter(modalitate_plata, s, monede):
    ok = 0
    modalitate_plata = [0]
    while len(modalitate_plata) > 0:
        choosen = False
        while not choosen and sum(modalitate_plata) < s:
            modalitate_plata[-1] = modalitate_plata[-1] + 1
            if isSet(modalitate_plata, s, monede):
                choosen = True
        if choosen:
            if sum(modalitate_plata) == s:
                ok = 1
                print(modalitate_plata)
            modalitate_plata.append(0)
        else:
            modalitate_plata = modalitate_plata[:-1]

    if ok == 0:
        print('nu se poate face suma!')





monede = [1, 5, 10, 20,25,50,75]
s = 75
modalitate_plata = []

backIter(modalitate_plata, s, monede)
#backtracking(modalitate_plata,s, monede)

