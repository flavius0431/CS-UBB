from exceptions.exceptions import ValidationException, NotFoundException


class Consola:
    def __init__(self,srv):
        self.__srv = srv

    def adauga(self):
        nume = input('Nume: ')
        prenume = input('Prenume: ')
        inaltime = float(input('Inaltime: '))
        post = input('Post: ')

        try:
            adauga_jucator = self.__srv.adauga(nume,prenume,inaltime, post)
        except ValidationException as ve:
            print(str(ve))

    def modifica(self):
        nume = input('Nume: ')
        prenume = input('Prenume: ')
        inaltime = int(input('Inaltime: '))

        try:
            modifica_jucator = self.__srv.modifica(nume,prenume,inaltime)
        except  ValidationException as ve:
            print(str(ve))

    def echipa_valida(self):
        lista = self.__srv.echipa_valida()
        if len(lista) == 5:
            print('Prenume Nume Post Inaltime ')
            for jucator in lista:
                print(str(jucator.getPrenume()) + ' ' + str(jucator.getNume()) + ' ' + str(jucator.getPost()) + ' ' + str(jucator.getInaltime()))
        else:
            print('Echipa nu este valida!')

    def jucatori_import(self):
        fisier = input('Fisier(nume_prenume): ')
        print(self.__srv.importa(fisier))


    def show_ui(self):
        while True:
            cmd = input('Comanda este: ')
            if cmd == 'adauga':
                self.adauga()
            elif cmd == 'modifica':
                self.modifica()
            elif cmd == 'echipa':
                self.echipa_valida()
            elif cmd =='import':
                self.jucatori_import()
            elif cmd == 'exit':
                return
            else:
                print("comanda invalida")


