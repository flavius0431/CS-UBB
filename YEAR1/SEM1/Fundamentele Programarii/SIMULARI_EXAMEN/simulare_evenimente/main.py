
from ui.consola import Consola
from domain.validari import Validator
from repository.repo_even import RepoEvenimentFile
from service.srv_enen import ServiceEvenimet

repo= RepoEvenimentFile('data/evenimente.txt')
val=Validator()

srv=ServiceEvenimet(repo,val)

ui=Consola(srv)
ui.show_ui()