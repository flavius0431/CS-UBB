"""
2. Se da data nașterii (zi/luna/an), determinați vârsta persoanei in zile
"""
import datetime

data_ = input("dati o data de nastere")
zi, luna, an=[ int(x) for x in data_.split("/") if x.strip()]
data_nastere=datetime.date(an, luna, zi)
print(data_nastere)
data_curenta=datetime.date(2021,10,6)
diferenta=data_curenta-data_nastere
numarul_de_zile=diferenta.days
print(numarul_de_zile)




                    
