from termcolor import colored

from domain.Numere_complexe import creeaza_numar_complex
from utils.Adaugare import adauga_numar_complex_in_lista
from utils.Stergere import sterge_numar_complex_din_lista_de_pe_o_pozitie_data


def copiaza_lista(lista_de_numere):
    copie = []
    for el in lista_de_numere:
        element_curent={}
        for key, value in el.items():
            element_curent[key] = value
        copie.append(element_curent)
    return copie

def adauga_in_undo(undo_list, lista_modificata):
    # functie care adauga la undo_list fiecare modificare a listei noastre
    # input : undo_list - lista de liste cu toate modificarile listei de numere complexe
    #       : lista_modifica - noua lista modificata, pe care o adaugam la undo_list
    # output: undo_list +lista+modificata
    undo_list.append(lista_modificata)

def undo(undo_list):
    # functie care face undo la ultima operatie
    # input:undo_list - list cu toate modificarile listei_complexe
    # output: .
    if len(undo_list) == 0:
        raise ValueError('Nu se mai poate face undo!')
    else:
        return undo_list.pop()

def undo_ui(lista_complexa):
    try:
        undo(lista_complexa)
        print(colored('Undo realizat cu succes','green'))
    except ValueError as ve:
        print(colored(ve,'red'))





def test_undo():
    test_undo_lista=[]
    lista_complexa=[]
    assert len(test_undo_lista) == 0
    numar_complex1 = creeaza_numar_complex(5,2)
    numar_complex2 = creeaza_numar_complex(4, 1)
    numar_complex3 = creeaza_numar_complex(-3, 0)

    adauga_in_undo(test_undo_lista, copiaza_lista(lista_complexa))
    adauga_numar_complex_in_lista(lista_complexa, numar_complex1)

    assert len(test_undo_lista) == 1

    adauga_in_undo(test_undo_lista, copiaza_lista(lista_complexa))
    adauga_numar_complex_in_lista(lista_complexa, numar_complex1)

    assert len(test_undo_lista) == 2

    adauga_in_undo(test_undo_lista, copiaza_lista(lista_complexa))
    adauga_numar_complex_in_lista(lista_complexa, numar_complex1)
    assert len(test_undo_lista) == 3
    '''[[5,2],[4,1],[-3,0]]'''
    adauga_in_undo(test_undo_lista, copiaza_lista(lista_complexa))
    sterge_numar_complex_din_lista_de_pe_o_pozitie_data(lista_complexa, 1)
    assert len(test_undo_lista) == 4
    test_undo_lista = undo(test_undo_lista)
    assert len(test_undo_lista) == 3
