from domain.Gettere import get_partea_reala, get_partea_imaginara
from domain.Numere_complexe import genereaza_numere_complexe, creeaza_numar_complex, test_valideaza_numar_complex, \
    test_valideaza_pozitie
from utils.Adaugare import adauga_numar_complex_in_lista_pe_o_pozitie_data, adauga_numar_complex_in_lista
from utils.Cautare import inlocuire_toate_aparitiile_unui_numar_comlex, numere_complexe_cu_modulul_egal_cu_10, \
    calculare_modul, numere_complexe_cu_modulul_mai_mic_decat_10, tipareste_partea_imaginara_din_interval_dat, \
    inlocuirea_tuturor_aparitiilor_unui_numar_complex_cu_un_alt_nr
from utils.Filtrare import partea_reala_numar_prim, filtrare_dupa_modul_fata_de_un_numar_dat, \
    filtrare_dupa_partea_reala_numar_prim
from utils.Operatii_cu_nr import produsul_numerelor_din_subsecventa_data, suma_dintr_o_subsecventa_data
from utils.Stergere import sterge_numere_complexe_din_lista_de_pe_un_un_interval, \
    sterge_numar_complex_din_lista_de_pe_o_pozitie_data
from utils.Undo import test_undo


def test_produsul_numerelor_din_subsecventa_data():
    lista1 = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    pozitia_initiala1 = 0
    pozitia_finala1 = 2
    produs_complex1 = {"partea_reala": 1, "partea_imaginara": 1}
    produs_complex1 = produsul_numerelor_din_subsecventa_data(lista1, pozitia_initiala1, pozitia_finala1)
    assert produs_complex1["partea_reala"] == -75
    assert produs_complex1["partea_imaginara"] == 11

    lista2 = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8], [6, 8], [-4, -2], [0, 0]]
    pozitia_initiala2 = 2
    pozitia_finala2 = 5
    produs_complex2 = {"partea_reala": 1, "partea_imaginara": 1}
    produs_complex2 = produsul_numerelor_din_subsecventa_data(lista2, pozitia_initiala2, pozitia_finala2)
    assert produs_complex2["partea_reala"] == 1564
    assert produs_complex2["partea_imaginara"] == 1852


def test_inlocuire_toate_aparitiile_unui_numar_complex():
    lista = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    numar_complex_de_inlocuit1 = creeaza_numar_complex(2, 3)
    numar_complex_nou1 = creeaza_numar_complex(-2, -3)
    lista = inlocuire_toate_aparitiile_unui_numar_comlex(lista, numar_complex_de_inlocuit1, numar_complex_nou1)
    assert lista[0] == numar_complex_nou1


def test_suma_dintr_o_subsecventa_data():
    lista = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8], [6, 8], [-4, -2], [0, 0]]
    pozitia_initiala = 1
    pozitia_finala = 4
    suma = {"partea_reala": 0, "partea_imaginara": 0}
    suma = suma_dintr_o_subsecventa_data(lista, pozitia_initiala, pozitia_finala)
    assert suma["partea_reala"] == 16
    assert suma["partea_imaginara"] == 22


def test_numere_complexe_cu_modulul_egal_cu_10():
    lista = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    lista = numere_complexe_cu_modulul_egal_cu_10(lista)
    assert calculare_modul(lista[0]) == 10


def test_numere_complexe_cu_modulul_mai_mic_decat_10():
    lista = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    lista = numere_complexe_cu_modulul_mai_mic_decat_10(lista)
    for i in range(len(lista)):
        modul_nr_complex = calculare_modul(lista[i])
        assert modul_nr_complex < 10


def test_tipareste_parte_imaginara_din_interval_dat():
    lista = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    pozitia_initiala = 1
    pozitia_finala = 3
    lista_imaginara = []
    lista_imaginara = tipareste_partea_imaginara_din_interval_dat(lista, pozitia_initiala, pozitia_finala)
    assert lista_imaginara[0] == 1
    assert lista_imaginara[1] == 5
    assert lista_imaginara[2] == 8


def test_inlocuirea_tuturor_aparitiilor_unui_numar_complex_cu_un_alt_numar():
    lista1 = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    numar_complex = creeaza_numar_complex(2, 3)
    numar_nou = creeaza_numar_complex(1, 1)
    lista1 = inlocuirea_tuturor_aparitiilor_unui_numar_complex_cu_un_alt_nr(lista1, numar_complex, numar_nou)
    assert (get_partea_reala(lista1[0]) == get_partea_reala(numar_nou))

    lista2 = [{"partea_reala": -2, "partea_imaginara": 3},
              {"partea_reala": 3, "partea_imaginara": 3},
              {"partea_reala": 7, "partea_imaginara": 0},
              {"partea_reala": -2, "partea_imaginara": 3},
              {"partea_reala": 2, "partea_imaginara": -2},
              {"partea_reala": -2, "partea_imaginara": 3}]

    numar_complex = creeaza_numar_complex(-2, 3)
    numar_nou = creeaza_numar_complex(3, 1)
    lista2 = inlocuirea_tuturor_aparitiilor_unui_numar_complex_cu_un_alt_nr(lista2, numar_complex, numar_nou)
    assert (lista2[0] == numar_nou)
    assert (lista2[3] == numar_nou)
    assert (lista2[5] == numar_nou)


def test_sterge_numere_complexe_din_lista_de_pe_un_interval():
    lista = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    lungime = len(lista)
    pozitia_initiala = 2
    pozitia_finala = 4
    sterge_numere_complexe_din_lista_de_pe_un_un_interval(lista, pozitia_initiala, pozitia_finala)
    lungime_noua = len(lista)
    assert (lungime != lungime_noua)


def test_sterge_numar_complex_din_lista_de_pe_o_pozitie_data():
    lista = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    pozitia = 0
    sterge_numar_complex_din_lista_de_pe_o_pozitie_data(lista, pozitia)
    assert lista[0] != creeaza_numar_complex(2, 3)


def test_adaugare_numar_complex_in_lista_pe_o_pozitie_data():
    lista = []
    assert (len(lista) == 0)
    numar_complex = creeaza_numar_complex(4, -3)
    pozitia = 0
    adauga_numar_complex_in_lista_pe_o_pozitie_data(lista, numar_complex, pozitia)
    assert (get_partea_reala(numar_complex) == get_partea_reala(lista[pozitia]))
    assert (get_partea_imaginara(numar_complex) == get_partea_imaginara(lista[pozitia]))


def test_adauga_numar_complex_in_lista():
    lista = []
    assert (len(lista) == 0)  # verificam sa fie lista goala
    numar_complex1 = creeaza_numar_complex(5, -2)
    numar_complex2 = creeaza_numar_complex(4, 0)

    adauga_numar_complex_in_lista(lista, numar_complex1)
    assert (len(lista) == 1)
    assert (get_partea_reala(lista[0]) == 5)
    assert (get_partea_imaginara(lista[0]) == -2)

    adauga_numar_complex_in_lista(lista, numar_complex2)
    assert (len(lista) == 2)
    assert (get_partea_reala(lista[1]) == 4)
    assert (get_partea_imaginara(lista[1]) == 0)

def test_creeaza_numar_complex():
    partea_reala = 12
    partea_imaginara = -4
    numar_complex = creeaza_numar_complex(partea_reala, partea_imaginara)
    assert (get_partea_reala(numar_complex) == partea_reala)
    assert (get_partea_imaginara(numar_complex) == partea_imaginara)


def testare_partea_reala_numar_prim():
    assert (partea_reala_numar_prim(-4) == 0)
    assert (partea_reala_numar_prim(0) == 0)
    assert (partea_reala_numar_prim(1) == 0)
    assert (partea_reala_numar_prim(2) == 1)
    assert (partea_reala_numar_prim(5) == 1)
    assert (partea_reala_numar_prim(7) == 1)
    assert (partea_reala_numar_prim(17) == 1)
    assert (partea_reala_numar_prim(25) == 0)

def test_filtrare_dupa_modul_fata_de_un_numar_dat():
    lista = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    modulul_unui_nr = 6
    lista = filtrare_dupa_modul_fata_de_un_numar_dat(lista, modulul_unui_nr)
    assert len(lista) == 5


def test_filtrare_dupa_partea_reala_numar_prim():
    lista = genereaza_numere_complexe()  # [[2, 3], [4, 1], [-1, 5], [7, 8],[6, 8], [-4, -2], [0, 0]]
    lista = filtrare_dupa_partea_reala_numar_prim(lista)
    assert (get_partea_reala(lista[0]) == 4)
    assert (get_partea_reala(lista[1]) == -1)
    assert (get_partea_reala(lista[2]) == 6)
    assert (get_partea_reala(lista[3]) == -4)
    assert (get_partea_reala(lista[4]) == 0)


def run_teste():
    test_creeaza_numar_complex()
    test_valideaza_numar_complex()
    test_valideaza_pozitie()
    test_adauga_numar_complex_in_lista()
    test_adaugare_numar_complex_in_lista_pe_o_pozitie_data()
    test_sterge_numar_complex_din_lista_de_pe_o_pozitie_data()
    test_sterge_numere_complexe_din_lista_de_pe_un_interval()
    testare_partea_reala_numar_prim()
    test_filtrare_dupa_partea_reala_numar_prim()
    test_inlocuirea_tuturor_aparitiilor_unui_numar_complex_cu_un_alt_numar()
    test_tipareste_parte_imaginara_din_interval_dat()
    test_numere_complexe_cu_modulul_mai_mic_decat_10()
    test_numere_complexe_cu_modulul_egal_cu_10()
    test_suma_dintr_o_subsecventa_data()
    test_inlocuire_toate_aparitiile_unui_numar_complex()
    test_produsul_numerelor_din_subsecventa_data()
    test_filtrare_dupa_modul_fata_de_un_numar_dat()
    test_undo()