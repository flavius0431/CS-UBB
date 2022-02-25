from termcolor import colored

from domain.Afisare import afiseaza_lista_de_numere_complexe
from ui.UiConsola2 import interfata_noua
from ui.UiMeniu import print_menu
from utils.Adaugare import adauga_numar_complex_in_lista,adauga_numar_complex_in_lista_pe_o_pozitie_data
from utils.Stergere import sterge_numar_complex_din_lista_de_pe_o_pozitie_data, sterge_numere_complexe_din_lista_de_pe_un_un_interval
from utils.Operatii_cu_nr import suma_dintr_o_subsecventa_data,produsul_numerelor_din_subsecventa_data
from utils.Filtrare import filtrare_dupa_partea_reala_numar_prim, filtrare_dupa_modul_fata_de_un_numar_dat
from tests.Teste_rulare import run_teste
from domain.Numere_complexe import creeaza_numar_complex,genereaza_numere_complexe
from utils.Cautare import inlocuire_toate_aparitiile_unui_numar_comlex, tipareste_partea_imaginara_din_interval_dat, \
    numere_complexe_cu_modulul_mai_mic_decat_10, numere_complexe_cu_modulul_egal_cu_10
from utils.Undo import undo, copiaza_lista, adauga_in_undo, undo_ui


def start():
    run_teste()
    run()
    #interfata_noua()


def adauga_numar_complex_la_sfarsitul_listei_ui(lista):
    try:
        partea_reala = int(input("Partea reala: "))
        partea_imaginara = int(input("Partea imaginara: "))
        numar_complex = creeaza_numar_complex(partea_reala, partea_imaginara)
        adauga_numar_complex_in_lista(lista, numar_complex)
        print("Adaugare realizata cu succes!")
    except ValueError:
        print(colored("Introduceti numere valide pentru partea reala si imaginara.", 'red'))



def adauga_numar_complex_pe_o_pozitie_data_ui(lista):
    try:
        partea_reala = int(input("Partea reala: "))
        partea_imaginara = int(input("Partea imaginara: "))
        pozitia = int(input("Pozitia: "))

    except ValueError:
        print(colored("Introduceti numere valide pentru partea reala , imaginara si pozitie.", 'red'))
        return

    numar_complex = creeaza_numar_complex(partea_reala, partea_imaginara)
    try:
        adauga_numar_complex_in_lista_pe_o_pozitie_data(lista, numar_complex, pozitia)
        print(colored("Adaugare realizata cu succes.", 'green'))
    except ValueError as ve:
        print(colored(ve, 'red'))
    return lista



def stege_numar_complex_de_pe_o_pozitie_data_ui(lista):
    try:
        pozitia = int(input("Pozitia: "))
    except ValueError:
        print(colored("Introduceti o pozitie valida ", 'red'))
        return

    try:
        sterge_numar_complex_din_lista_de_pe_o_pozitie_data(lista, pozitia)
        print(colored("Stegerea s-a realizat cu succes.", 'green'))
    except ValueError as ex:
        print(colored(ex, 'red'))
    return lista

def sterge_numere_complexe_din_lista_de_pe_un_un_interval_ui(lista):
    try:
        pozitia_initiala = int(input("Pozitia initiala: "))
        pozitia_finala = int(input("Pozitia finala: "))
    except ValueError:
        print(colored("Introduceti o pozitie valida ", 'red'))
        return
    try:
        sterge_numere_complexe_din_lista_de_pe_un_un_interval(lista, pozitia_initiala, pozitia_finala)
        print(colored("Stergere pe un interval s-a realizat cu suuces", 'green'))
    except ValueError as ex:
        print(colored(ex, 'red'))
    return lista


def suma_dintr_o_subsecventa_data_ui(lista):
    try:
        pozitia_initiala = int(input("Pozitia initiala: "))
        pozitia_finala = int(input("Pozitia finala: "))
    except ValueError:
        print(colored("Introduceti o pozitie valida ", 'red'))
        return

    try:
        print(suma_dintr_o_subsecventa_data(lista, pozitia_initiala, pozitia_finala))
        print(colored("Adunarea s-a efectuat cu succes", 'green'))
    except ValueError as ex:
        print(colored(ex, 'red'))


def produsul_numerelor_din_subsecventa_data_ui(lista):
    try:
        pozitia_initiala = int(input("Pozitia initiala: "))
        pozitia_finala = int(input("Pozitia finala: "))
    except ValueError:
        print(colored("introduceti o pozitie valida", 'red'))
        return
    try:
        print(produsul_numerelor_din_subsecventa_data(lista, pozitia_initiala, pozitia_finala))
        print(colored("Produsul s-a realizat cu succes.", 'green'))
    except ValueError as ve:
        print(colored(ve, 'red'))


def inlocuire_toate_aparitiile_unui_numar_comlex_ui(lista):
    try:
        partea_reala_nr_de_inlocuit = int(input("Partea reala la numar de inlocuit: "))
        partea_imaginara_nr_de_inlocuit = int(input("Partea imaginara la numar de inlocuit: "))
        parte_reala_nr_nou = int(input("Partea reala la numar nou: "))
        parte_imaginara_nr_nou = int(input("Partea imaginara la numar nou: "))
    except ValueError:
        print(colored("Introduceti o pozitie valida ", 'red'))
        return
    numar_complex_de_inlocuit = creeaza_numar_complex(partea_reala_nr_de_inlocuit, partea_imaginara_nr_de_inlocuit)
    numar_complex_nou = creeaza_numar_complex(parte_reala_nr_nou, parte_imaginara_nr_nou)
    lista_noua = []
    try:
        lista_noua = inlocuire_toate_aparitiile_unui_numar_comlex(lista, numar_complex_de_inlocuit, numar_complex_nou)
        print(colored("Inlocuirea s-a efectuat cu succes", 'green'))
    except ValueError as ex:
        print(colored(ex, 'red'))
    return lista_noua


def tipareste_partea_imaginara_din_interval_dat_ui(lista):
    try:
        pozitia_initiala = int(input("Pozitia initiala: "))
        pozitia_finala = int(input("Pozitia finala: "))
    except ValueError:
        print(colored("Introduceti o pozitie valida!", 'red'))
        return
    lista_noua = []
    try:
        lista_noua = tipareste_partea_imaginara_din_interval_dat(lista, pozitia_initiala, pozitia_finala)
    except ValueError as ex:
        print(colored(ex, 'red'))
    return lista_noua


def filtrare_dupa_modul_fata_de_un_numar_dat_ui(lista):
    try:
        modulul_unui_nr = int(input("Modulul unui numar: "))
    except ValueError:
        print(colored("Introduceti un  modul valid!", 'red'))
        return
    lista_noua = []

    try:
        lista_noua = filtrare_dupa_modul_fata_de_un_numar_dat(lista, modulul_unui_nr)
    except ValueError as ve:
        print(colored(ve, 'red'))

    return lista_noua

def run():
    undo_lista=[]
    lista_complexa=[]

    while True:
        print_menu()
        afiseaza_lista_de_numere_complexe(lista_complexa)
        comanda = input(colored("Da comanda: ", 'cyan'))
        if comanda == '1':
            adauga_in_undo(undo_lista, copiaza_lista(lista_complexa))
            adauga_numar_complex_la_sfarsitul_listei_ui(lista_complexa)

        elif comanda == '2':
            adauga_in_undo(undo_lista, copiaza_lista(lista_complexa))
            adauga_numar_complex_pe_o_pozitie_data_ui(lista_complexa)

        elif comanda == '3':
            adauga_in_undo(undo_lista, copiaza_lista(lista_complexa))
            stege_numar_complex_de_pe_o_pozitie_data_ui(lista_complexa)

        elif comanda == '4':
            adauga_in_undo(undo_lista, copiaza_lista(lista_complexa))
            sterge_numere_complexe_din_lista_de_pe_un_un_interval_ui(lista_complexa)

        elif comanda == '5':
            adauga_in_undo(undo_lista, copiaza_lista(lista_complexa))
            lista_complexa = inlocuire_toate_aparitiile_unui_numar_comlex_ui(lista_complexa)

        elif comanda == '6':
            print(tipareste_partea_imaginara_din_interval_dat_ui(lista_complexa))

        elif comanda == '7':
            print(numere_complexe_cu_modulul_mai_mic_decat_10(lista_complexa))
            print(colored("Lista de numere complexe cu modulul mai mic decat 10", 'blue'))

        elif comanda == '8':
            print(numere_complexe_cu_modulul_egal_cu_10(lista_complexa))

        elif comanda == '9':
            suma_dintr_o_subsecventa_data_ui(lista_complexa)

        elif comanda == '10':
            produsul_numerelor_din_subsecventa_data_ui(lista_complexa)

        elif comanda == '12':
            adauga_in_undo(undo_lista, copiaza_lista(lista_complexa))
            lista_complexa = filtrare_dupa_partea_reala_numar_prim(lista_complexa)

            print(colored("Filtrarea s-a realizat cu succes", 'green'))
        elif comanda == '13':
            lista_complexa= filtrare_dupa_modul_fata_de_un_numar_dat_ui(lista_complexa)

        elif comanda == '14':
           undo_lista = undo_ui(lista_complexa)

        elif comanda == 'p':
            afiseaza_lista_de_numere_complexe(undo_lista)
            print(colored("Afisarea s-a realizat cu succes", 'green'))

        elif comanda == 'x':
            return
        else:
            print(colored("Comanda invalida ", 'red'))