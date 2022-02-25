
from termcolor import colored

from domain.Afisare import afiseaza_lista_de_numere_complexe
from domain.Gettere import extragere_comanda, extragere_paretea_reala, extragere_paretea_imaginara, extrage_pozitia, \
    extrage_pozitia_initiala,extrage_pozitia_finala
from domain.Numere_complexe import genereaza_numere_complexe, creeaza_numar_complex
from ui.UiMeniu import print_menu2
from utils.Adaugare import adauga_numar_complex_in_lista
from utils.Cautare import tipareste_partea_imaginara_din_interval_dat
from utils.Filtrare import filtrare_dupa_partea_reala_numar_prim
from utils.Operatii_cu_nr import suma_dintr_o_subsecventa_data
from utils.Stergere import sterge_numar_complex_din_lista_de_pe_o_pozitie_data


def interfata_noua():
    lista_de_numere_complexe=[]
    while True:
        print_menu2()
        afiseaza_lista_de_numere_complexe(lista_de_numere_complexe)
        comanda = input("Da comanda: ")
        if extragere_comanda(comanda) == "adauga":
            adauga_numar_complex_in_lista(lista_de_numere_complexe,creeaza_numar_complex(extragere_paretea_reala(comanda),extragere_paretea_imaginara(comanda)))
        elif extragere_comanda(comanda) == "stergere":
            sterge_numar_complex_din_lista_de_pe_o_pozitie_data(lista_de_numere_complexe, int(extrage_pozitia(comanda)))
        elif extragere_comanda(comanda) == "filtrare":
            print(filtrare_dupa_partea_reala_numar_prim(lista_de_numere_complexe))
        elif extragere_comanda(comanda) == "tipareste":
            lista_de_numere_complexe=tipareste_partea_imaginara_din_interval_dat(lista_de_numere_complexe,int(extrage_pozitia_initiala(comanda)),int(extrage_pozitia_finala(comanda)))
        elif extragere_comanda(comanda) == "suma":
           print(suma_dintr_o_subsecventa_data(lista_de_numere_complexe,int(extrage_pozitia_initiala(comanda)), int(extrage_pozitia_finala(comanda))))
        elif comanda =="exit":
            return
        else:
            print(colored("Comanda invalida ",'red'))

