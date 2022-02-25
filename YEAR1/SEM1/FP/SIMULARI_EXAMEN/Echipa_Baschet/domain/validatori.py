from domain.entitate_echipa_baschet import Baschetbalist
from exceptions.exceptions import ValidationException


class Validatori:
    def validare(self,baschetbalist):

        errors=[]
        if baschetbalist.getNume() == '':
            errors.append('Numele nu poate fi vid!')
        if baschetbalist.getPrenume() == '':
            errors.append('Prenumele nu poate fi vid!')
        if baschetbalist.getPost() !='Fundas' and baschetbalist.getPost() !='Extrema' and baschetbalist.getPost() !='Pivot':
            errors.append('Postul nu poate fi diferit de Fundas, Extrema si Pivot!')
        if baschetbalist.getInaltime() != int(baschetbalist.getInaltime()) or baschetbalist.getInaltime() < 140 :
            errors.append('Inaltimea trebuie sa fie mai mare de 140 cm si sa fie numar intreg!')

        if len(errors)>0:
            errors_string = '\n'.join(errors)
            raise ValidationException(errors_string)



def test_validare():
    validator = Validatori()
    b1 = Baschetbalist('', 'Simbotin', 190, 'Fundas')
    try:
        validator.validare(b1)
        assert False
    except ValidationException:
        assert True

    b2 = Baschetbalist('Simbotin', 'Simbotin', 180, 'Pivot')
    b3 = Baschetbalist('Mirunescu', 'Diana', 170, 'Extrema')
    b4 = Baschetbalist('Maier', 'Dana', 166, 'Pivot')