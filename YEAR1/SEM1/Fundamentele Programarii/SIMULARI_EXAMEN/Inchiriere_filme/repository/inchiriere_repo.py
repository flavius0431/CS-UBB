from Inchiriere_filme.exceptions.exceptions import *
from Inchiriere_filme.domain.entities import Film

class InchiriereRepository:
    def __init__(self):
        self.__inchirieri= []

    def getInchirieri(self):
        return self.__inchirieri

    def checkInchiriatByClient(self, id_film, id_client):
        """
            Cauta in lista inchirierea avand id-ul clientului egal cu id_client si id-ul filmului egal cu id_film
            Returneaza * inchirierea, daca acesta exista in lista
                       * ridica exceptie, in caz contrar
        """
        for i in range (len(self.__inchirieri)):
            if self.__inchirieri[i].getId_film() == id_film and self.__inchirieri[i].getId_client() == id_client:
                return self.__inchirieri[i]
        raise RepoException("Inchirierea cu id-ul dat nu exista!")

    def addInchiriere(self, inchiriere):
        if inchiriere in self.__inchirieri:
            raise RepoException("Id-ul inchirierii este deja in lista!")
        self.__inchirieri.append(inchiriere)

    def deleteInchiriere(self, id_film, id_client):
        """
            Sterge inchirierea in functie de id film si id client.
        """
        for i in range(len(self.__inchirieri)):
            if self.__inchirieri[i].getId_film() == id_film and self.__inchirieri[i].getId_client() == id_client:
                self.__inchirieri.pop(i)
                return
        raise RepoException("Delete error: Nu exista inchiriere cu id-urile specificate!")

    def updateInchiriere(self, newInchiriere):
        """
            Modifica data inchirierii in functie de id film si id client aflate deja in corelatie.
        """
        for i in range(len(self.__inchirieri)):
            if self.__inchirieri[i] == newInchiriere:
                self.__inchirieri[i] = newInchiriere
                return
        raise RepoException("Update error: Nu exista inchiriere cu id-urile specificate!")


