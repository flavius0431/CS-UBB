from Inchiriere_filme.repository.film_repo import *
from Inchiriere_filme.repository.client_repo import *
from Inchiriere_filme.repository.inchiriere_repo import *
from Inchiriere_filme.validators.validators import *
from datetime import date


class InchiriereService:
    def __init__(self, inchiriere_repo: InchiriereRepository, inchiriere_validator: InchiriereValidator,
                 film_repo: FilmRepository, film_validator: FilmValidator, client_repo: ClientRepository,
                 client_validator: ClientValidator):
        self.__inchiriere_repo = inchiriere_repo
        self.__inchiriere_validator = inchiriere_validator
        self.__film_repo = film_repo
        self.__film_validator = film_validator
        self.__client_repo = client_repo
        self.__client_validator = client_validator

    def getInchirieri(self):
        return self.__inchiriere_repo.getInchirieri()

    def addInchiriere(self, id_client, id_film, data_inchirierii):
        newInchiriere = Inchiriere(id_client, id_film, data_inchirierii)

        self.__inchiriere_validator.validate(newInchiriere)
        try:
            self.__inchiriere_repo.addInchiriere(newInchiriere)
        except RepoException as ex:
            raise ServiceException(ex)

    def deleteInchiriere(self, id_client, id_film):
        try:
            self.__inchiriere_repo.deleteInchiriere(id_film, id_client)
        except RepoException as ex:
            raise ServiceException(ex)

    def searchInchiriereById(self, id_film, id_client):
        return self.__inchiriere_repo.checkInchiriatByClient(id_film, id_client)

    def inchiriazaFilm(self, id_client, id_film, data):
        # verifica daca exista vreun client cu id-ul id_client si vreun film cu id-ul id_film
        client = self.__client_repo.searchClientById(id_client)
        film = self.__film_repo.searchFilmById(id_film)
        if film.getNr_filme_disponibile() == 0:
            raise ServiceException("Nu exista exemplare disponibile!")
        self.addInchiriere(id_client, id_film, data)
        film.decrease_nr_filme_disponibile()
        self.__film_repo.updateFilm(film)

    def returneazaFilm(self, id_client, id_film):
        # verifica daca exista vreun client cu id-ul id_client si vreun film cu id-ul id_film
        client = self.__client_repo.searchClientById(id_client)
        film = self.__film_repo.searchFilmById(id_film)
        self.deleteInchiriere(id_client, id_film)
        film.increase_nr_filme_disponibile()
        self.__film_repo.updateFilm(film)

    def numarFilmeInchiriate(self, id_client):
        """
            Returneaza numarul de filme inchiriate la momentul actual al unui client cu id-ul = id_client.
        """
        inchirieri = self.__inchiriere_repo.getInchirieri()
        numarFilmeInchiriate = 0
        for inchiriere in inchirieri:
            if inchiriere.getId_client() == id_client:
                numarFilmeInchiriate = numarFilmeInchiriate + 1
        return numarFilmeInchiriate

    def getClientsOrdonatiDupaFilmeInchiriate(self):
        """
            Ordoneaza clientii in functie de numarul de filme inchiriate.
        """
        clients = self.__client_repo.getClients()
        clients.sort(key=lambda x: self.numarFilmeInchiriate(x.getId_client()), reverse=True)
        return clients

    def numarClientiPeFilm(self, id_film):
        """
            Returneaza numarul de clienti pe care il are la momentul actual un film cu un id-ul = id_film
        """
        inchirieri = self.__inchiriere_repo.getInchirieri()
        numarClienti = 0
        for inchiriere in inchirieri:
            if inchiriere.getId_film() == id_film:
                numarClienti = numarClienti + 1
        return numarClienti

    def getFilmeCeleMaiInchiriate(self):
        """
            Returneaza lista de filme in functie de cele mai inchiriate filme.
        """
        films = self.__film_repo.getFilms()
        films.sort(key = lambda x: self.numarClientiPeFilm(x.getId_film()) , reverse= True)
        return films

    def nrFilmeInchiriate(self , films):
        """
            Returneaza numarul de filme inchiriate la momentul actual.
        """
        nrFilmeInchiriate = 0
        for film in films:
            if self.numarClientiPeFilm(film.getId_film()) != 0:
                nrFilmeInchiriate = nrFilmeInchiriate + 1
        return nrFilmeInchiriate

    def nrClientiCuFilmeInchiriate(self, clients):
        """
            Returneaza numarul de clienti cu filme inchiriate la momentul actual.
        """
        nrClienti = 0
        inchirieri = self.__inchiriere_repo.getInchirieri()
        for client in clients:
            ok = 0
            for inchiriere in inchirieri:
                if inchiriere.getId_client() == client.getId_client() and ok == 0:
                    nrClienti = nrClienti + 1
                    ok = 1
        return nrClienti
