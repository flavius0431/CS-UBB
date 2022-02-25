from domain.entitate import Dictionar
from exceptions.exceptions import ValidationException


class Validator:
    def validare(self, dictionar):
        '''
        valideaza datele de intrare
        :param dictionar:  obiect de tip dictionar
        :return: ridica exceprii de tipul ValidationException
        '''

        errors=[]

        if dictionar.getLimbaS() != 'En' and dictionar.getLimbaS() != 'Ro' and dictionar.getLimbaS() != 'Fr':
            errors.append('Limba sursa trebuie sa fie En,Fr sau Ro')
        if dictionar.getLimbaD() != 'En' and dictionar.getLimbaD() != 'Ro' and dictionar.getLimbaD() != 'Fr':
            errors.append('Limba destinatie trebuie sa fie En,Fr sau Ro')

        if dictionar.getLimbaS() ==dictionar.getLimbaD():
            errors.append('Limba destinatie trebuie sa fie diferita de limba sursa')
        if dictionar.getCuvantS() =='':
            errors.append('Cuvantul Sursa nu poate fi vid')
        if dictionar.getCuvantD() =='':
            errors.append('Cuvantul Destinatie nu poate fi vid')


        if len(errors)>0:
            errors_string = '\n'.join(errors)
            raise ValidationException(errors_string)

def test_validare():
    val = Validator()
    traducere = Dictionar('Ro', 'animal', 'En', 'animal')
    try:
        val.validare(traducere)
        assert True
    except ValidationException:
        assert False

    traducere1 = Dictionar('It', 'animal', 'En', 'animal')
    try:
        val.validare(traducere1)
        assert False
    except ValidationException:
        assert True

    traducere1 = Dictionar('It', '', 'En', '')
    try:
        val.validare(traducere1)
        assert False
    except ValidationException:
        assert True


test_validare()

