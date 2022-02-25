class Consola:
    def __init__(self,srv):
        self.__srv=srv

    def delet(self):
        nume= input('Nume: ')
        tip = input('Tip: ')
        return self.__srv.delet(nume,tip)

    def modifica(self):
        nume= input('Nume:')
        tip = input('Tip: ')
        durata= int(input('Durata:'))
        descriere= input('Descriere: ')
        return self.__srv.modifica(nume,tip,durata,descriere)

    def aleatoriu(self):
        ora1=input('Ora de inceput:')
        ora2= input('Ora de sfarsit: ')
        return self.__srv.aleatoriu(ora1,ora2)


    def show_ui(self):
        while True:
            cmd = input('Comanda este: ')
            if cmd =='delet':
                self.delet()
            elif cmd =='modifica':
                self.modifica()
            elif cmd =='aleatoriu':
                self.aleatoriu()
            elif cmd =='exit':
                return
            else:
                print('Comanda invalida')
