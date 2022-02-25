import random
class  EmisiuneTVService:
    def __init__(self,repo,val):
        self.__repo=repo
        self.__val=val

    def delet(self,nume,tip):
        return self.__repo.delet(nume,tip)

    def modifica(self,nume,tip,descriere,durata):
        return self.__repo.modifica(nume, tip,descriere,durata)

    def get_all(self):
        return self.__repo.get_all()

    def aleatoriu(self,ora1,ora2):
        c=ora2-ora1
        
        ora = random.randint(ora1,ora2)



