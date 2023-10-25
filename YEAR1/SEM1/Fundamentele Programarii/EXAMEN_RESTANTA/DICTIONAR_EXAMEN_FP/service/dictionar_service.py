from domain.entitate import Dictionar


class DictionarService:
    def __init__(self,repo,val):
        self.__repo=repo
        self.__val = val

    def adauga(self, limbas,cuvants,limbad,cuvantd):
        '''
        verificam daca traducerea formata este valida altfel ridicam exceptie
        :param limbas: string
        :param cuvants: string
        :param limbad: string
        :param cuvantd: string
        :return:
        '''
        traducere=Dictionar(limbas,cuvants,limbad,cuvantd)
        self.__val.validare(traducere)
        self.__repo.adauga(traducere)

    def sortare(self,lista):
        '''
        sortam  alfabetic datele din lista  dupa Cuvantul sursa
        :param lista: list, lista pe care o sortam
        :return: list, lista sortata
        '''
        for i in range(len(lista)-1):
            for j in range (i+1,len(lista)):
                if lista[j].getCuvantS() < lista[i].getCuvantS():
                    lista[i],lista[j]=lista[j],lista[i]
        return lista

    def vizualizare(self,limba):
        '''
        formeaza o lista cu toate traducerile din limba limba
        :param limba:string, limba dupa care este formata lista
        :return:list
        '''
        lista = self.get_all()
        lista_noua=[]
        for traducere in lista:
            if traducere.getLimbaS() == limba:
                lista_noua.append(traducere)

        lista_noua=self.sortare(lista_noua)
        return lista_noua

    def cauta_cuvant(self,cuvantS, limbaS,limbaD):
        '''
        cautam un cuvant  in dictionarul de traduceri
        :param cuvantS:  string
        :param limbaS: string
        :param limbaD:  string
        :return: string, cuvantul cautat
        '''
        dictionar=self.get_all()
        ok = 0
        for traducere in dictionar:
            if traducere.getCuvantS() == cuvantS and traducere.getLimbaS() == limbaS and traducere.getLimbaD() == limbaD:
                ok =1
                cuvant =  traducere.getCuvantD()
        if ok ==1:
            return cuvant
        else:
            return '{'+cuvantS+'}'


    def traducere(self,IN,limbaS,IE,limbaD):
        '''
        face
        :param IN:string, numele fisierului din care luam textul
        :param limbaS:sting, limba din care traducem
        :param IE:string, fisierul in care incarcam textul tradus
        :param limbaD: string, limba in care traducem textul
        :return:
        '''
        lista= self.__repo.load_from_file_2(IN)
        for i in range(len(lista)):
            lista[i] = self.cauta_cuvant(lista[i], limbaS,limbaD)
        self.salveaza(IE,lista)


    def salveaza(self,IE,lista):
        '''
        salvam lista in fisierul dat
        :param IE: string, numelefisierului in care salvam
        :param lista: list
        :return:
        '''
        return self.__repo.save_to_file_2(IE,lista)



    def get_all(self):
        '''
        luam toate datele fin fisier
        :return: lista de date din fisier
        '''
        return self.__repo.get_all()


