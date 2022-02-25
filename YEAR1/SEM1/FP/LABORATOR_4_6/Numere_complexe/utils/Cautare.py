from domain.Gettere import get_partea_imaginara, get_partea_reala
import math

def inlocuirea_tuturor_aparitiilor_unui_numar_complex_cu_un_alt_nr(lista, numar_complex, numar_nou):
    # functia care inlocuieste toate aparitiile unui numar complex cu un alt numar complex
    # input : lista - lista de numere complexe
    #       : numar_complex - numarul complex care trebuie inlocuit in toate pozitiile
    #       : numar_nou - numarul cu care inlocuim toate pozitiile numarului complex numar_complex
    # output: lista_noua, care are toate numerele complexe numar_complex inlocuite cu numarul complex numar_nou
    lista_noua = []
    assert (len(lista_noua) == 0)
    for el in lista:
        if el != numar_complex:
            lista_noua.append(el)
        else:
            lista_noua.append(numar_nou)
    lista = lista_noua[:]
    return lista


def tipareste_partea_imaginara_din_interval_dat(lista, pozitia_initiala, pozitia_finala):
    # functie care afiseaza partea imaginara a numerelor din lista dintr-un interval
    # input : lista - lista de numere complexe
    #         pozitia_initiala - pozitia de unde incepem sa afisam partea imaginara
    #         pozitia_finala - pozitia pana unde se afiseaza partea imaginara
    # output : lista_imaginara -  o lista nu numerele imaginare din intervalul dat
    lista_imaginara = []
    for el in range(pozitia_initiala, pozitia_finala + 1):
        lista_imaginara.append(get_partea_imaginara(lista[el]))
    return lista_imaginara


def calculare_modul(numar_complex):
    # functie care calculeaza modulul unui numar complex
    # input: numar_complex - numar complex
    # output: modul- modulul numarului complex
    partea_reala = get_partea_reala(numar_complex)
    partea_imaginara = get_partea_imaginara(numar_complex)
    return math.sqrt(partea_reala * partea_reala + partea_imaginara * partea_imaginara)


def numere_complexe_cu_modulul_mai_mic_decat_10(lista):
    # functie care returneaza numerele compleze care au modulul mai mic decat 10
    # input : lista -  lista de numere complexe
    # output: lista_modul_mai_mic_decat_10 - lista cu numere complexe ce au modulul mai mic decat 10
    lista_modul_mai_mic_decat_10 = []
    for el in lista:
        if calculare_modul(el) < 10:
            lista_modul_mai_mic_decat_10.append(el)
    return lista_modul_mai_mic_decat_10


def numere_complexe_cu_modulul_egal_cu_10(lista):
    # functie care returneaza numerele complexe care au modulul egal cu 10
    # input:  lista- lista de numere complexe
    # outout: lista_modul_egal_cu_10 - lista de numere complexe care au modulul egal cu 10
    lista_modul_egal_cu_10 = []
    for el in lista:
        if calculare_modul(el) == 10:
            lista_modul_egal_cu_10.append(el)
    return lista_modul_egal_cu_10





def inlocuire_toate_aparitiile_unui_numar_comlex(lista, numar_complex_de_inlocuit, numar_complex_nou):
    # functie care inlocuieste toate aparitiile unui numar complex
    # numar_complex_de_inlocuit cu un alr numar complex numar_complex_nou
    # input: lista- lista de numere complexe
    #      : numar_complex_de_inlocuit - numarul complex pe care il inlocuim
    #      : numar_complex_nou - numarul cu care inlocuim numar_complex_de_inlocuim
    # output: lista- lista cu numerele complexe in care toate
    # aparitiile lui numar_complex_de_inlocuit sunt inlocuite cu numar_complex
    lista_noua = []
    for el in lista:
        if el != numar_complex_de_inlocuit:
            lista_noua.append(el)
        else:
            lista_noua.append(numar_complex_nou)
    return lista_noua


