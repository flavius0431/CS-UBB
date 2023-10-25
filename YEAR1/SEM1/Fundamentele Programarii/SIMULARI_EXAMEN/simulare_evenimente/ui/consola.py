from datetime import date
from datetime import time

class Consola:
    def __init__(self,srvEven):
        self.__srvEvem=srvEven

    def adauga(self):
        try:
            data_string = input('Data: ')
            year, month,day=map(int,data_string.split('-'))
            data= date(year, month,day)
            ora_string = input('Ora: ')
            hour,minute= map(int,ora_string.split(':'))
            ora = time(hour,minute)
            descriere = input('Descriere: ')
        except ValueError:
            print("Introduceti data/ora valide")

        try:
            adaugat = self.__srvEvem.adauga_even(data, ora, descriere)
            print("evenimentul",adaugat," a fost adaugat cu suuces")
        except ValueError :
            print("nu s-a facut adaugarea")


    def show_ui(self):
        while True:
            cmd=input('Comanda:')
            if cmd=='adauga':
                self.adauga()