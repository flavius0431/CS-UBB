from domain.entitate import Dictionar
from exceptions.exceptions import ExistException


class DictionarRepoFile:
    def __init__(self,filename):
        self.__filename = filename
        self.__load_from_file()

    def __load_from_file(self):
        '''
        preia datele din fisier
        :return: lista cu datele de tipul Dictionar
        '''
        with open(self.__filename, "r") as f:
            lista_dictionar=[]
            lines = f.readlines()
            for line in lines:
                limbas,cuvants,limbad,cuvantd=[token.strip() for token in line.split(';')]
                traducere= Dictionar(limbas,cuvants,limbad,cuvantd)
                lista_dictionar.append(traducere)

            return lista_dictionar

    def load_from_file_2(self,IN):
        '''
        preluam datele din fisier
        :param IN: string, numele fisierului din care preluam datele
        :return: lista cu datele din fisier
        '''
        with open("data/"+IN+".txt", "r") as c:
            lista = []
            lines = c.readlines()
            for line in lines:
                lista=[token.strip() for token in line.split(' ')]
        return lista

    def save_to_file_2(self,IE,lista):
        '''
        salveaza datele in fisier
        :param IE:  string, numele fisierului in care salvam datele
        :param lista:list, lista pe care o salvam in fisier
        :return:
        '''
        with open("data/"+IE+".txt","w") as k:
            cuvant_string = ''
            for cuvant in lista:
                cuvant_string = cuvant_string+cuvant+' '
            k.write(cuvant_string)


    def __save_to_file(self,lista):
        '''
        salvam datele de tip Dictionar in fisier
        :param lista: list
        :return:
        '''
        with open(self.__filename,"w") as p:
            for traducere in lista:
                traducere_string= str(traducere.getLimbaS())+';'+str(traducere.getCuvantS())+';'+str(traducere.getLimbaD())+';'+str(traducere.getCuvantD())+'\n'
                p.write(traducere_string)


    def adauga(self,traducere):
        '''
        adaugam o traducere in fisier
        :param traducere: obiect de tip Dictionar,
        :return: list, lista nou formata
        '''
        lista= self.get_all()
        ok=0
        for traduceri in lista:
            if  traduceri.getLimbaD() == traducere.getLimbaD():
                ok=1
                raise ExistException()

        if ok == 0:
            lista.append(traducere)
            self.__save_to_file(lista)

        return lista


    def get_all(self):
        '''
        luam toate datele fin fisier
        :return: lista de date din fisier
        '''
        return self.__load_from_file()


