

class Dictionar:
    def __init__(self,limba_sursa,cuvant_sursa,limba_destinatie,cuvant_destinatie):
        self.__limba_sursa=limba_sursa
        self.__cuvant_sursa=cuvant_sursa
        self.__limba_destinatie= limba_destinatie
        self.__cuvant_destinatie=cuvant_destinatie
        """"
        creeaza o entitate de tip dictionar cu  limba_sursa,cuvant_sursa,limba_destinatie,cuvant_destinatie
        fiecare este de tip string
        """

    def getLimbaS(self):
        return self.__limba_sursa

    def getCuvantS(self):
        return self.__cuvant_sursa

    def getLimbaD(self):
        return self.__limba_destinatie

    def getCuvantD(self):
        return self.__cuvant_destinatie

    def __str__(self):
        return str(self.__limba_sursa)+';'+str(self.__cuvant_sursa)+';'+str(self.__limba_destinatie)+';'+str(self.__cuvant_destinatie)+'\n'

    def __lt__(self, other):
        if self.__cuvant_sursa < other.getCuvantS():
            return True
        return False

    def __eq__(self, other):
        if self.__limba_sursa == other.getLimbaS() and self.__cuvant_sursa == other.getCuvantS() and  self.__limba_destinatie == other.getLimbaD():
            return True
        return False

def test_creeaza():
    traducere=Dictionar('Ro','animal','En','animal')
    assert traducere.getLimbaS() == 'Ro'
    assert traducere.getCuvantS() == 'animal'
    assert traducere.getLimbaD() == 'En'
    assert traducere.getCuvantD() == 'animal'


test_creeaza()