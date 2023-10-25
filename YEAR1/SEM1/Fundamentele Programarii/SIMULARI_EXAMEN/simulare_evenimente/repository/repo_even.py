from datetime import date
from datetime import time

from domain.entitate import Eveniment


class RepoEvenimentFile:
    def __init__(self,filename):
        self.__filename=filename
        self.__load_from_file()


    def __load_from_file(self):
        with open(self.__filename,"r") as c:
            evenimente=[]
            lines=c.readlines()
            for line in lines:
                data,ora,descriere=[token.strip() for token in line.split(';')]
                year,mounth,day=map(int,data.split('-'))
                data=date(year,mounth,day)
                hour,minutes ,seconds= map(int,ora.split(':'))
                ora=time(hour,minutes)
                eveniment=Eveniment(data,ora,descriere)
                evenimente.append(eveniment)
        c.close()
        return evenimente

    def __save_to_file(self,evenimente):

        f = open(self.__filename, "w")
        for eveniment in evenimente:
            eveniment_string = str(eveniment.getData()) + ';' + str(eveniment.getOra()) + ';' + str(eveniment.getDescriere()) + '\n'
            f.write(eveniment_string)


    def adauga(self,eveniment):
        even=self.__load_from_file()
        even.append(eveniment)
        self.__save_to_file(even)

    def all_even(self):
        return self.__load_from_file()



