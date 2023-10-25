from exceptions.exceptions import ValidationException, ExistException


class Consola:
    def __init__(self,srv):
        self.__srv= srv

    def adauga(self):
        limbas = input('Limba Sursa: ')
        cuvants = input('Cuvant Sursa: ')
        limbad = input('Limba Destinatie: ')
        cuvantd = input('Cuvant Destinatie: ')

        try:
            self.__srv.adauga(limbas,cuvants,limbad,cuvantd)
        except ValidationException as ve:
            print(str(ve))
        except ExistException as e:
            print(str(e))

    def vizualizare(self):
        limba = input('Limba: ')
        lista= self.__srv.vizualizare(limba)
        for traducere in lista:
            print(traducere)

    def traducere(self):
        fisierIN= input('Fisier intrare:')
        limbaS= input ('Limba Sursa:')
        fisierIE = input ('Fisier Iesire:')
        limbaD = input('Limba Destinatie:')
        self.__srv.traducere(fisierIN,limbaS,fisierIE,limbaD)

    def tranzitiv(self):
        pass



    def show_ui(self):
        while True:
            cmd= input('Comanda este: ')
            if cmd == 'adauga':
                self.adauga()
            elif cmd =='vizualizare':
                self.vizualizare()
            elif cmd =='traducere':
                self.traducere()
            elif cmd =='tranzitiv':
                self.tranzitiv()
            elif cmd =='exit':
                return
            else:
                print('Comanda invalida')
