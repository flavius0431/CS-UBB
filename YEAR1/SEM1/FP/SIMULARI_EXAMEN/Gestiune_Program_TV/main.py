from domain.validare import Validare
from repository.repo_tv import EmisiuneTVRepoFile
from service.srv_TV import EmisiuneTVService
from ui.consola import Consola

repo = EmisiuneTVRepoFile("data/emisiuni.txt")
val= Validare()

srv = EmisiuneTVService(repo,val)

ui = Consola(srv)

ui.show_ui()
