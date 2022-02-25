from datetime import date

from domain.entitate import Melodie
from exceptions.exceptions import ValidationException


class MusicRepoFile:
    def __init__(self, filename):
        self.__filename = filename
        self.__load_from_file()

    def __load_from_file(self):
        with open(self.__filename,"r") as f:

            music_list=[]
            lines = f.readlines()
            for line in lines:
                titlu,artist,gen,data=[token.strip() for token in line.split(';')]
                music = Melodie(titlu,artist,gen,data)
                music_list.append(music)
            return music_list


    def __save_to_file(self,music_list):
        with open(self.__filename,"w") as p:
            for music in music_list:
                music_string = str(music.getTitlu())+';'+str(music.getArtist())+';'+str(music.getGen())+';'+str(music.getData()) +'\n'
                p.write(music_string)

    def save_to_file_2(self,list,fisier):
        with open('data/'+fisier+'.txt',"w") as c:
            for music in list:
                music_string = str(music.getTitlu()) + ';' + str(music.getArtist()) + ';' + str(music.getGen()) + ';' +str(music.getData()) + '\n'
                c.write(music_string)

    def adauga(self,music):
        lista= []
        lista=self.__load_from_file()
        lista.append(music)
        self.__save_to_file(lista)
        return lista

    def modifica(self,music):
        lista = self.__load_from_file()
        ok=0
        for melodie in lista:
            if melodie.getTitlu() == music.getTitlu() and melodie.getArtist() == music.getArtist():
                ok=1
                melodie.setGen(music.getGen())
                melodie.setData(music.getData())
        self.__save_to_file(lista)
        if ok == 1:
            return True
        return False

    def adauga_aleatoriu(self,lista):
        return self.__save_to_file(lista)

    def export(self,list,fisier):
        return self.save_to_file_2(list,fisier)


    def get_all(self):
        return self.__load_from_file()