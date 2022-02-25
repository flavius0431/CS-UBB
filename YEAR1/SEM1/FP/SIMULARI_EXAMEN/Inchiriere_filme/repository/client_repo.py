from Inchiriere_filme.exceptions.exceptions import *
from Inchiriere_filme.domain.entities import Client


class ClientRepository:
    def __init__(self):
        self.__clients = []

    def getClients(self):
        return self.__clients

    def addClient(self, client):
        """
            Adauga client in lista de clienti, daca id-ul clientului dorit spre adaugare exista, se ridica exceptie
        """
        if client in self.__clients:
            raise RepoException("Id-ul clientului este deja in lista!")
        self.__clients.append(client)

    def searchClientById(self, id_client) -> Client:
        """
            Cauta in lista clientul avand id-ul = id_client
            Returneaza * clientul, daca acesta exista in lista
                       * ridica exceptie, in caz contrar
        """
        for client in self.__clients:
            if client.getId_client() == id_client:
                return client
        raise RepoException("Id-ul clientului nu exista!")

    def deleteClient(self, id_client):
        """
            Sterge clientul in functie de id = id_client
        """
        for i in range(len(self.__clients)):
            if self.__clients[i].getId_client() == id_client:
                self.__clients.pop(i)
                return
        raise RepoException("Clientul nu exista!")

    def updateClient(self, newClient):
        """
            Modifica clientul avand id-ul egal id-ul clientului newClient, setandu-i campurile cu noile date.
        """
        for i in range(len(self.__clients)):
            if self.__clients[i].getId_client() == newClient.getId_client():
                self.__clients[i] = newClient
                return
        raise RepoException("Clientul nu exista!")
