from domain.entitate import Eveniment


class ServiceEvenimet:
    def __init__(self,repo,val):
        self.__repo=repo
        self.__val=val

    def adauga_even(self,data,ora,descriere):
        eveniment=Eveniment(data,ora,descriere)
        return self.__repo.adauga(eveniment)

