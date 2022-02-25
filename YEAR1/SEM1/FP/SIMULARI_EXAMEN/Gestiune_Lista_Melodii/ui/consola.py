from exceptions.exceptions import ValidationException


class Consola:
    def __init__(self,srv):
        self.__srv=srv

    def modifica(self):
        titlu = input('Titlu:')
        artist = input('Artist: ')
        gen = input('Gen: ')
        data = input('Data(dd.mm.yyyy): ')
        try:
            modificat= self.__srv.modifica(titlu,artist,gen,data)
        except ValidationException as ve:
            print(str(ve))

    def adauga(self):
        titlu = input('Titlu:')
        artist = input('Artist: ')
        gen = input('Gen: ')
        data= input('Data(dd.mm.yyyy): ')
        try:
            adaugat= self.__srv.adauga(titlu,artist,gen,data)
        except ValidationException as ve:
            print(str(ve))

    def aleatoriu(self):
        nr_melodii=int(input('Numarul de melodii: '))
        titluri=input('Titluri: ')
        artisti= input('Artisti: ')
        print(self.__srv.aleatoriu(nr_melodii,titluri,artisti))

    def export(self):
        fisier=input('Fisier: ')
        self.__srv.export(fisier)

    def show_ui(self):
        while True:
            cmd=input('Comanda este: ')
            if cmd == 'adauga':
                self.adauga()
            elif cmd =='modifica':
                self.modifica()
            elif cmd =='aleatoriu':
                self.aleatoriu()
            elif cmd =='export':
                self.export()
            elif cmd =='exit':
                return
            else:
                print('Comanda invalida')