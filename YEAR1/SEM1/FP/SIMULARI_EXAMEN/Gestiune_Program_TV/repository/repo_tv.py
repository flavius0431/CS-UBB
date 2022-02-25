from domain.entitate import Emisiune_TV


class EmisiuneTVRepoFile:
    def __init__(self,filename):
        self.__filename=filename
        self.__load_from_file()

    def __load_from_file(self):
        with open(self.__filename,"r") as f:

            emisiuniTV=[]
            lines=f.readlines()
            for line in lines:
                nume,tip,durata,descriere=[token.strip() for token in line.split(';')]
                emisiune = Emisiune_TV(nume,tip,durata,descriere)
                emisiuniTV.append(emisiune)
        return emisiuniTV

    def __save_to_file(self,emisiuni):
        with open(self.__filename,"w")as p:
            for emisiune in emisiuni:
                emisiune_string=str(emisiune.getNume())+';'+str(emisiune.getTip())+';'+str(emisiune.getDurata())+';'+str(emisiune.getDescriere())+'\n'
                p.write(emisiune_string)

    def delet(self,nume, tip):
        emisiuni = self.__load_from_file()
        ok=-1

        for emisiune in emisiuni:
            ok= ok+1
            if emisiune.getNume() == nume and emisiune.getTip() == tip:
                break
        if ok == -1:
            print('Nu exista aceasta emisiune')
        else:
            emisiuni.pop(ok)
            self.__save_to_file(emisiuni)

    def modifica(self,nume,tip,durata,descriere):
        emisiuni = self.__load_from_file()
        ok = 0
        for emisiune in emisiuni:
            if emisiune.getNume() == nume and emisiune.getTip() == tip:
                emisiune.setDurata(durata)
                emisiune.setDescriere(descriere)
                ok =1
        if ok == 0:
            print('Nu exista aceasta emisiune')
        else:
            self.__save_to_file(emisiuni)


    def get_all(self):
        return self.__load_from_file()