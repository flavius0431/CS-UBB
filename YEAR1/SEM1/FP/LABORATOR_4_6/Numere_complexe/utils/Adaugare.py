

def adauga_numar_complex_in_lista(lista, numar_complex):
    # functie care adauga numarul complex numar_complex in lista de numere complexe lista
    # input : lista - lista de numere complexe
    #        numar_complex - numar complex
    # output - lista = lista +numar_complex
    lista.append(numar_complex)


def adauga_numar_complex_in_lista_pe_o_pozitie_data(lista, numar_complex, pozitia):
    # functie care adauga numarul complex numar_complex in lista de numere complexe  lista pe o anumita pozitie pozitia
    # input : lista - lista de  numere complexe
    #        numar_complex - numar complex
    #        pozitia - pozitia pe care trebuie adaugat numarul comple
    # output - lista = lista + numar_complex ( pe pozitia data)
    lista.insert(pozitia, numar_complex)
