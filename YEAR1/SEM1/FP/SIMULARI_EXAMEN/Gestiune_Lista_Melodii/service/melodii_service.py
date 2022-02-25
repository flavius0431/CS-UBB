import datetime
from datetime import date
from domain.entitate import Melodie
import random





class MusicService:
    def __init__(self,repo,val):
        self.__repo=repo
        self.__val=val

    def adauga(self,titlu,artist,gen,data):
        music= Melodie(titlu,artist,gen,data)
        self.__val.validare(music)
        self.__repo.adauga(music)
        return music

    def modifica(self,titlu,artist,gen,data):
        music = Melodie(titlu, artist, gen, data)
        self.__repo.modifica(music)
        return music


    def random_titlu_artist(self,list):
        c = random.randint(0,len(list)-1)
        return list[c]

    def random_gen(self):
        list=['Rock','Pop','Jazz','Altele']
        c = random.randint(0,len(list)-1)
        return list[c]

    def random_date(self):
        data1=date(2010,2,14)
        data2=date(2022,2,14)
        time_between =data2-data1
        days_between = time_between.days
        random_nr_days= random.randrange(days_between)
        random_date =data1+datetime.timedelta(days=random_nr_days)
        data_string= str(random_date.day)+'.'+str(random_date.month)+'.'+str(random_date.year)
        return data_string



    def aleatoriu(self,nr_melodii,titluri,artisti):
        titluri_list=titluri.split(',')
        artisti_list=artisti.split(',')
        list=self.get_all()
        for i in range(nr_melodii):
            titlu = self.random_titlu_artist(titluri_list)
            artist = self.random_titlu_artist(artisti_list)
            gen = self.random_gen()
            date =self.random_date()
            music=Melodie(titlu,artist,gen,date)
            list.append(music)

        self.__repo.adauga_aleatoriu(list)
        return nr_melodii

    def export(self,fisier):
        lista= self.get_all()
        for i in range(len(lista)-1):
            for j in range(i+1,len(lista)):
                dayj,monthj,yearj=lista[j].getData().split('.')
                dataj=date(int(yearj),int(monthj),int(dayj))
                dayi, monthi, yeari = lista[i].getData().split('.')
                datai =date(int(yeari), int(monthi), int(dayi))
                if (dataj-date.today()).days <= (datai-date.today()).days:
                    lista[j],lista[i]=lista[i],lista[j]

        self.__repo.export(lista,fisier)


    def get_all(self):
        return self.__repo.get_all()