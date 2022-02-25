from domain.entitate_echipa_baschet import Baschetbalist


class BaschetbalitRepoFile:
    def __init__(self, filename):
        self.__filename=filename
        self.__load_from_file()


    def __load_from_file(self):
        with  open(self.__filename, "r") as f:

            baschetbalisti=[]
            lines=f.readlines()

            for line in lines:
                nume, prenume, inaltime, post= [token.strip() for token in line.split(';')]
                baschetbalist=Baschetbalist(nume, prenume, float(inaltime), post)
                baschetbalisti.append(baschetbalist)
        return baschetbalisti

    def load_from_file_2(self,numeprenume):
        with open('data/'+numeprenume+'.txt', "r") as c:
            nume_prenume=[]
            lines = c.readlines()
            for line in lines:
                nume, prenume =[token.strip() for token in line.split(';')]
                nume_prenume.append([nume,prenume])
        return nume_prenume

    def __save_to_file(self,baschetbalisti):
        with open(self.__filename,"w") as p:
            for baschetbalist in baschetbalisti:
                baschetbalisti_string=str(baschetbalist.getNume())+';'+str(baschetbalist.getPrenume())+';'+str(baschetbalist.getInaltime())+';'+str(baschetbalist.getPost())+'\n'
                p.write(baschetbalisti_string)

    def adauga(self,baschetbalist):
        lista=self.__load_from_file()
        lista.append(baschetbalist)
        self.__save_to_file(lista)
        return lista

    def modifica(self,nume, prenume, inaltime):
        lista = self.__load_from_file()
        for jucator in lista:
            if jucator.getNume() == nume and jucator.getPrenume() == prenume:
                jucator.setInaltime(inaltime)
        self.__save_to_file(lista)
        return lista

    def get_all(self):
        return self.__load_from_file()

    def get_all_2(self,fisier):
        return self.load_from_file_2(fisier)






