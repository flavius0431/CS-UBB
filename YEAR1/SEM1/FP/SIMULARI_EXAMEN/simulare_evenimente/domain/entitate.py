

class Eveniment:
    def __init__(self,data,ora,descriere):
        self.__data=data
        self.__ora=ora
        self.__descriere=descriere

    def getData(self):
        return self.__data

    def getOra(self):
        return self.__ora

    def getDescriere(self):
        return self.__descriere

    def __str__(self):
        return str(self.__data)+';'+str(self.__ora)+';'+str(self.__descriere)+'\n'

    def __cmp__(self, other):
        if self.__ora > other.getOra():
            return 1
        return 0