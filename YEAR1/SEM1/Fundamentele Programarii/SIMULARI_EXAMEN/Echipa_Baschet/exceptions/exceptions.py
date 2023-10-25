class GestionareEchipaBachetException(Exception):
    pass

class ValidationException(GestionareEchipaBachetException):
    def __init__(self,mesaj):
        self.__err_mesaj=mesaj

    def getMesaj(self):
        return self.__err_mesaj

    def __str__(self):
        return 'Validation Exception: '+ str(self.__err_mesaj)

class RepositoryException(GestionareEchipaBachetException):
    def __init__(self,mesaj):
        self.__err_mesaj = mesaj

    def getMesaj(self):
        return self.__err_mesaj

    def __str__(self):
        return 'Repository Exception: '+str(self.__err_mesaj)

class NotFoundException(RepositoryException):
    def __init__(self):
        RepositoryException.__init__(self,'Jucatorul nu este in lista!')
