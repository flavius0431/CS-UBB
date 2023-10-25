class Emisiune_TV:
    def __init__(self,nume,tip,durata,descriere):
        self.__nume=nume
        self.__tip=tip
        self.__durata=durata
        self.__descriere=descriere

    def getNume(self):
        return self.__nume

    def getTip(self):
        return self.__tip

    def getDurata(self):
        return self.__durata

    def getDescriere(self):
        return self.__descriere

    def setNume(self,value):
        self.__nume= value

    def setTip(self,value):
        self.__tip = value

    def setDurata(self,value):
        self.__durata = value

    def setDescriere(self, value):
        self.__descriere = value

    def __eq__(self,other):
        if self.__nume == other.getNume() and self.__tip == other.getTip():
            return True
        return False
