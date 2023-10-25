from domain.validare import Validator
from repository.dictionar_repo import DictionarRepoFile
from service.dictionar_service import DictionarService
from ui.consola import Consola

repo = DictionarRepoFile("data/dictionar.txt")
val =Validator()

srv=DictionarService(repo,val)

ui = Consola(srv)
ui.show_ui()
