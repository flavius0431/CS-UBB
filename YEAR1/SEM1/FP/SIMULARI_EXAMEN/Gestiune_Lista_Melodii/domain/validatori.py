from datetime import date
from exceptions.exceptions import ValidationException


class Validator:
    def validare(self,melodie):

        errors=[]
        if melodie.getTitlu() == '':
            errors.append('Titlul trebuie specificat')
        if melodie.getArtist() == '':
            errors.append('Artistul trebuie specificat')
        if melodie.getGen() != 'Rock' and melodie.getGen() !='Pop' and melodie.getGen() !='Jazz' and melodie.getGen() !='Altele' :
            errors.append('Genul trebuie sa fie Rock, Pop,Jazz sau Altele')
        day,month,year=melodie.getData().split('.')
        if int(day) >31 or int(month)>12 or int(year) > 2022:
            errors.append('Data trebuie sa fie valida')
        if len(errors)>0:
            errors_string = '\n'.join(errors)
            raise ValidationException(errors_string)
