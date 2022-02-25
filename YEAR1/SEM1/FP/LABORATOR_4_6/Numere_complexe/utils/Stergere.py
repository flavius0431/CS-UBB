

def sterge_numar_complex_din_lista_de_pe_o_pozitie_data(lista, pozitia):
    # functie care sterge un numar complex numarul_complex din lista de
    # numere complexe lista de pe o anumita pozitie pozitia
    # input: lista - lista de numere complexe
    #       pozitia - pozitia de pe care trebuie sters numarul complex
    # output: lista = lista - numar_complex (de pe pozitia data)
    lista.pop(pozitia)


def sterge_numere_complexe_din_lista_de_pe_un_un_interval(lista, pozitia_initiala, pozitia_finala):
    # functie care sterge  numere compleze dintr-un interval dat
    # input: lista - lista de numere complexe
    #      pozitia_initia- pozitia de unde incepe intervalul de unde trebuie sa stergem numere complexe
    #      pozitia_finala - pozitia de  se termina intervalul unde trebuie sa stergem numerele complexe
    # output: lista = lista - intervalul de numere complexe sterse
    lista[pozitia_initiala:pozitia_finala + 1] = []
    return lista