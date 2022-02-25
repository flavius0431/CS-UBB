
def get_partea_reala(numar_complex):
    # functie care returneaza partea reala intreaga parte_reala al numarului complex numar_complex
    # input : un numarcomplex
    # output : rez - intreg, partea reala parte_reala al numarului complex numar_complex
    return numar_complex["partea_reala"]


def get_partea_imaginara(numar_complex):
    # functie care returneaza partea imaginara intreaga parte_imaginara al numarului complex numar_complex
    # input : un numar complex
    # output : rex - intreg,  partea imaginara parte_imaginara al numarului complex numar_complex
    return numar_complex["partea_imaginara"]


def set_partea_reala(numar_complex, partea_reala):
    numar_complex["partea_reala"] = partea_reala


def set_partea_imaginara(numar_complex, partea_imaginara):
    numar_complex["partea_imaginara"] = partea_imaginara


def extragere_comanda(comanda):
    sir_comanda = comanda.split(' ')
    return sir_comanda[0]


def extragere_paretea_reala(comanda):
    sir_comanda = comanda.split(' ')
    return sir_comanda[1]


def extragere_paretea_imaginara(comanda):
    sir_comanda = comanda.split(' ')
    return sir_comanda[2]


def extrage_pozitia(comanda):
    sir_comanda = comanda.split(' ')
    return sir_comanda[1]


def extrage_pozitia_initiala(comanda):
    sir_comanda = comanda.split(' ')
    return sir_comanda[1]


def extrage_pozitia_finala(comanda):
    sir_comanda = comanda.split(' ')
    return sir_comanda[2]