from domain.Gettere import get_partea_reala, get_partea_imaginara


def genereaza_numere_complexe():
    # functie care gereneaza o lista de numere complexe
    return [{"partea_reala": 2, "partea_imaginara": 3},
            {"partea_reala": 4, "partea_imaginara": 1},
            {"partea_reala": -1, "partea_imaginara": 5},
            {"partea_reala": 7, "partea_imaginara": 8},
            {"partea_reala": 6, "partea_imaginara": 8},
            {"partea_reala": -4, "partea_imaginara": -2},
            {"partea_reala": 0, "partea_imaginara": 0}]




def creeaza_numar_complex(partea_reala, partea_imaginara):
    # functie care creaza un numar complex cu partea reala intrega parte_reala
    # si partea imaginara intreaga parte_imaginara
    # input : parte_reala - intreg
    #      : parte_imaginara -intreg
    # output : rex - un  numar complex cu partea reala
    # intreaga parte_reala si cu partea imaginara intreaga parte_imaginara
    return {"partea_reala": partea_reala, "partea_imaginara": partea_imaginara}



def valideaza_pozitie(pozitia):
    # functie care verifica daca o pozitie - pozitia este numar intreg strict pozitiv
    # input: pozitia - intreg stric pozotiv
    # output: - , daca pozitia este un numar intreg pozitiv
    # raises : Exception cu numerele intregi negative
    #          "pozitia invalid!\n" daca pozitia nu este un intreg stric pozitiv
    error = []
    if int(pozitia) != pozitia:
        error.append("Pozitia invalid!")
    if pozitia < 0:
        error.append("Pozitia invalid!")
    if len(error) > 0:
        error_string = '\n'.join(error)
        raise ValueError(error_string)

def valideaza_numar_complex(numar_complex):
    # functie care verifica daca un numar complex numarul_complex are partea reala intreaza si partea imaginara intreaga
    # input : numar_complex - un numar complex
    # output : - , daca numarul complex e valid
    # raises : Exception cu numerele intregi
    #         "partea_reala invalid!\n" daca partea_reala a numarului_complex nu este intreaga
    #         "partea_imaginara invalid!\n" daca partea_imaginara a numarului_complex nu este intreaga
    errors = []
    if int(get_partea_reala(numar_complex)) != get_partea_reala(numar_complex):
        errors.append("Partea_reala invalid!")
    if int(get_partea_imaginara(numar_complex)) != get_partea_imaginara(numar_complex):
        errors.append("Partea_imaginara invalid!")
    if len(errors) > 0:
        error_string = '\n'.join(errors)
        raise ValueError(error_string)

def test_valideaza_pozitie():
    pozitia1 = -3
    try:
        valideaza_pozitie(pozitia1)
    except ValueError as ex:
        assert str(ex) == "Pozitia invalid!"

    pozitia2 = 5.6
    try:
        valideaza_pozitie(pozitia2)
    except ValueError as ex:
        assert str(ex) == "Pozitia invalid!"

def test_valideaza_numar_complex():
    numar_complex1 = creeaza_numar_complex(1.6, 7)
    try:
        valideaza_numar_complex(numar_complex1)
    except ValueError as ve:
        assert str(ve) == "Partea_reala invalid!"

    numar_complex2 = creeaza_numar_complex(3, 4.2)
    try:
        valideaza_numar_complex(numar_complex2)
    except ValueError as ve:
        assert str(ve) == "Partea_imaginara invalid!"

    numar_complex3 = creeaza_numar_complex(-3.5, 2.5)
    try:
        valideaza_numar_complex(numar_complex3)
    except ValueError as ve:
        assert str(ve) == "Partea_reala invalid!\nPartea_imaginara invalid!", 'red'
