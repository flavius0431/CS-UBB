
class ValidationDictionar(Exception):
    pass

class ValidationException(ValidationDictionar):
    def __init__(self, mesaj):
        self.__err_mesaj = mesaj

    def getMesaj(self):
        return self.__err_mesaj

    def __str__(self):
        return 'Validation Exception: ' + str(self.__err_mesaj)

class RepositoryException(ValidationDictionar):
    def __init__(self, mesaj):
        self.__err_mesaj = mesaj

    def getMesaj(self):
        return self.__err_mesaj

    def __str__(self):
        return 'Repository Exception: ' + str(self.__err_mesaj)

class ExistException(RepositoryException):
    def __init__(self):
        RepositoryException.__init__(self,'traducerea se afla in dictionar')
