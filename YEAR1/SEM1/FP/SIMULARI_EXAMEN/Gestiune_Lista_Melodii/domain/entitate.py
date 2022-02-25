

class Melodie:
    def __init__(self,titlu,artist,gen,data):
        self.__titlu = titlu
        self.__artist = artist
        self.__gen = gen
        self.__data = data

    def getTitlu(self):
        return self.__titlu

    def getArtist(self):
        return self.__artist

    def getGen(self):
        return self.__gen

    def getData(self):
        return self.__data

    def setGen(self,value):
        self.__gen = value

    def setData(self,value):
        self.__data = value

    def __str__(self):
        return str(self.__titlu)+';'+str(self.__artist)+';'+str(self.__gen)+';'+str(self.__data)+'\n'

    def __eq__(self, other):
        if self.__titlu == other.getTitlu() and self.__artist == other.getArtist():
            return True
        return False

    def __le__(self,other):
        if self.__data <= other.getData():
            return True
        return False