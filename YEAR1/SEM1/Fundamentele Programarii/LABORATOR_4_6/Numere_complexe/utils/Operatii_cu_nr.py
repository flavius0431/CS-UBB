from domain.Gettere import get_partea_reala, get_partea_imaginara


def suma_dintr_o_subsecventa_data(lista, pozitia_initiala, pozitia_finala):
    # functie care calculeaza suma dintr-o subsecventa data
    # input : lista- lista de numere complexe
    #       : pozitia_initiala- pozitia de unde incepe functia sa calculeze suma
    #       : pozitia_finala - pozitia pana unde face functia suma
    # output : suma_subsecventa- list , suma numerelor complexe din subsecventa data
    suma_subsecventa = {"partea_reala": 0, "partea_imaginara": 0}

    for el in range(pozitia_initiala, pozitia_finala + 1):
        suma_subsecventa["partea_reala"] += int(get_partea_reala(lista[el]))
        suma_subsecventa["partea_imaginara"] += int(get_partea_imaginara(lista[el]))

    return suma_subsecventa

def produsul_numerelor_din_subsecventa_data(lista, pozitia_initiala, pozitia_finala):
    # functie care calculeaza produsul numerelor complexe dintr-o subsecventa data
    # input : lista -lista de numere complexe
    #       : pozitia_initiala- pozitia de unde se incepe produsul subsecventei
    #       : pozitia_finala - pozitia de unde se termina produsul subsecventei
    # output : produs_complex - produsul numerelor complexe din subsecventa data
    produs_complex = {"partea_reala": 0, "partea_imaginara": 0}
    produs_complex["partea_reala"] = get_partea_reala(lista[pozitia_initiala])
    produs_complex["partea_imaginara"] = get_partea_imaginara(lista[pozitia_initiala])
    pozitia_initiala += 1
    pozitia_finala += 1
    for el in range(pozitia_initiala, pozitia_finala):
        partea_reala1 = produs_complex["partea_reala"]
        partea_imaginara1 = produs_complex["partea_imaginara"]
        partea_reala2 = get_partea_reala(lista[el])
        partea_imaginara2 = get_partea_imaginara(lista[el])
        produs_complex["partea_reala"] = partea_reala1 * partea_reala2 - partea_imaginara1 * partea_imaginara2
        produs_complex["partea_imaginara"] = partea_reala1 * partea_imaginara2 + partea_reala2 * partea_imaginara1

    return produs_complex

