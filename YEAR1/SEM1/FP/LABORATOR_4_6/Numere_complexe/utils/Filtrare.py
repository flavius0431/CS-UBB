import math

from domain.Gettere import get_partea_reala
from utils.Cautare import calculare_modul


def partea_reala_numar_prim(partea_reala):
    # functie care returneaza 1, daca partea_reala este numar prim si 0 in caz contrar
    # input: partea reala - parte reala
    # output : 1, daca numarul este prim
    #         0, daca numarul nu este prim
    partea_reala=int(partea_reala)
    if partea_reala < 2:
        return 0
    if partea_reala == 2:
        return 1
    if partea_reala % 2 == 0:
        return 0

    for i in range(3, int(math.sqrt(partea_reala)) + 1, 2):
        if partea_reala % i == 0:
            return 0
    return 1


def filtrare_dupa_partea_reala_numar_prim(lista):
    # functia filtreaza lista in functie de partea reala, astefel
    # daca partea reala este numar prim sterge numarul complex din lista
    # imput: lista- lista
    # output: lista_filtrata-lista, lista fara numerele complexe cu partea reala numar prim
    lista_filtrata = []
    for el in lista:
        if (partea_reala_numar_prim(get_partea_reala(el)) == 0):
            lista_filtrata.append(el)
    lista = lista_filtrata[:]
    return lista


def filtrare_dupa_modul_fata_de_un_numar_dat(lista, modulul_unui_nr):
    # functia filtreza lista in fuctie de modul,
    #  daca modulul numerelor complexe din lista de dictionare cu numere complexe
    # este mai mare decat un numar dat atunci se sterg
    # input : lista - lista de numere complexe
    #       : modulul_unui_nr- numarul dupa care se face filtrarea
    # output: lista, filtarat fara numerele cu modulul mai mare decar modulul_unui_nr
    lista_noua = []
    for el in lista:
        if modulul_unui_nr > calculare_modul(el):
            lista_noua.append(el)
    return lista_noua
