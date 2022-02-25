import pytest


class Baschetbalist:
    def __init__(self,nume, prenume,inaltime,post):
        self.__nume = nume
        self.__prenume = prenume
        self.__inaltime = inaltime
        self.__post = post

    def getNume(self):
        return self.__nume

    def getPrenume(self):
        return self.__prenume

    def getInaltime(self):
        return self.__inaltime

    def getPost(self):
        return self.__post

    def setInaltime(self,value):
        self.__inaltime = value

    def __eq__(self, other):
        if self.__nume == other.getNume() and self.__prenume == other.getPrenume():
            return True
        return False

    def __str__(self):
        return str(self.__prenume)+';'+str(self.__nume)+';'+str(self.__post)+';'+str(self.__inaltime)+'\n'


def test_creeaza_baschetbalist():
    nume = 'Simbotin'
    prenume = 'Flavius'
    inaltime = 190
    post ='Fundas'
    baschetbalist = Baschetbalist(nume,prenume,inaltime,post)
    assert  baschetbalist.getNume() == 'Simbotin'
    assert baschetbalist.getPrenume() == 'Simbotin'
    assert baschetbalist.getInaltime() == 190
    assert baschetbalist.getPost() == 'Fundas'

def test_egalitate():
    b1=Baschetbalist('Simbotin','Flavius',190,'Fundas')
    b2=Baschetbalist('Simbotin','Flavius',180,'Pivot')
    assert b1 == b2
    b3 = Baschetbalist('Mirunescu', 'Diana', 170, 'Extrema')
    b4 = Baschetbalist('Maier', 'Dana', 166, 'Pivot')
    assert b3 != b4


