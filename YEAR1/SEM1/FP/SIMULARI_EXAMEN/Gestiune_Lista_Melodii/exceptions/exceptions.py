class ValidationMusicList(Exception):
    pass

class ValidationException(ValidationMusicList):

    def __init__(self,mesaj):
        self.__err_mesaj = mesaj

    def getMesaj(self):
        return self.__err_mesaj

    def __str__(self):
        return 'ValidationException: '+str(self.__err_mesaj)
