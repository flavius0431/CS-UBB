from Inchiriere_filme.exceptions.exceptions import *
from Inchiriere_filme.domain.entities import Film

class FilmRepository:
    def __init__(self):
        self.__films = []

    def getFilms(self):
        return self.__films

    def addFilm(self, film):
        if film in self.__films:
            raise RepoException("Id-ul filmului este deja in lista!")
        self.__films.append(film)

    def searchFilmById(self, id_film):
        """
            Cauta in lista filmul avand id-ul = id_film
            Returneaza * filmul, daca acesta exista in lista
                       * ridica exceptie, in caz contrar
        """
        for film in self.__films:
            if film.getId_film() == id_film:
                return film
        raise RepoException("Nu exista filmul cu id-ul dat!")

    def deleteFilm(self, id_film):
        """
            Sterge filmul in functie de id = id_film
        """
        for i in range(len(self.__films)):
            if self.__films[i].getId_film() == id_film:
                self.__films.pop(i)
                return
        raise RepoException("Delete error: Nu exista id-ul filmului specificat in lista!")

    def updateFilm(self, newFilm):
        """
            Modifica filmul avand id-ul egal id-ul filmului newFilm, setandu-i campurile cu noile date
        """
        for i in range(len(self.__films)):
            if self.__films[i].getId_film() == newFilm.getId_film():
                self.__films[i]=newFilm
                return
        raise RepoException("Update error: Nu exista id-ul specificat in lista!")