class ValidationExceptionEmisiuni(Exception):
    pass

class RepositoryExceptions(ValidationExceptionEmisiuni):
    def __init__(self,mesaj):
        self.__err_mesaj=mesaj

    def getMesaj(self):
        return self.__err_mesaj

    def __str__(self):
        return 'repository exception '+str(self.__err_mesaj)

