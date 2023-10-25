from domain.entitate_echipa_baschet import Baschetbalist
import random

class Service_Bachetbalist:
    def __init__(self,repo,val):
        self.__repo=repo
        self.__val=val


    def adauga(self,nume,prenume,inaltime,post):
        baschetbalist=Baschetbalist(nume,prenume,inaltime,post)
        self.__val.validare(baschetbalist)
        self.__repo.adauga(baschetbalist)
        return baschetbalist

    def modifica(self, nume, prenume, inaltime):
        return self.__repo.modifica(nume,prenume,inaltime)


    def ordonare_max(self,jucatori):
        for i in range(len(jucatori)-1):
            for j in range (i+1,len(jucatori)):
                if jucatori[i].getInaltime() < jucatori[j].getInaltime():
                    jucatori[i],jucatori[j]=jucatori[j],jucatori[i]
        return jucatori

    def formare_echipa(self,fundasi,pivot,extreme):
        lista = []
        fundasi = self.ordonare_max(fundasi)
        for i in range(2):
            lista.append(fundasi[i])
        pivot = self.ordonare_max(pivot)
        for i in range(1):
            lista.append(pivot[i])
        extreme = self.ordonare_max(extreme)
        for i in range(2):
            lista.append(extreme[i])
        return lista

    def echipa_valida(self):
        lista = self.__repo.get_all()
        fundasi=[]
        extreme=[]
        pivot=[]
        for jucator in lista:
            if jucator.getPost() =='Fundas':
                fundasi.append(jucator)
            elif jucator.getPost() =='Extrema':
                extreme.append(jucator)
            elif jucator.getPost() =='Pivot':
                pivot.append(jucator)
        lista=[]
        lista = self.formare_echipa(fundasi, pivot, extreme)
        return lista

    def random_inaltime(self):
        return random.randint(140,249)

    def random_post(self):
        lista =['Fundas','Extrema','Pivot']
        return lista[random.randint(0,2)]

    def importa(self,fisier):
        nr_import = 0
        lista_np=self.__repo.get_all_2(fisier)
        lista=self.__repo.get_all()
        for i in range(len(lista_np)):
            ok = 0
            for j in range(0, len(lista)):
                if lista[j].getNume() == lista_np[i][0] and lista[j].getPrenume() == lista_np[i][1]:
                    ok = 1
            if ok == 0:
                inaltime = self.random_inaltime()
                post = self.random_post()
                self.adauga(lista_np[i][0],lista_np[i][1],inaltime,post)
                nr_import = nr_import+1

        return nr_import





