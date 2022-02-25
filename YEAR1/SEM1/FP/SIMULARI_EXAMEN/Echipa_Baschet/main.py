from domain.validatori import Validatori
from repository.repo_baschet import BaschetbalitRepoFile
from service.srv_baschet import Service_Bachetbalist
from ui.consola import Consola

val=Validatori()
repo = BaschetbalitRepoFile("data/echipa_baschet.txt")
srv=Service_Bachetbalist(repo,val)

ui= Consola(srv)
ui.show_ui()