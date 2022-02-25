from datetime import date

from domain.validatori import Validator
from repository.melodii_repo import MusicRepoFile
from service.melodii_service import MusicService
from ui.consola import Consola

repo = MusicRepoFile("data/melodii.txt")
val = Validator()

srv = MusicService(repo,val)

ui=Consola(srv)
ui.show_ui()