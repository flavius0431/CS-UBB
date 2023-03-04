#include "teste.h"

void test_operator() {
	Masina m1{ "aaaaaaa","aaa","aa","a" };
	Masina m2{ "","","","" };
	m2 = m1;
	m1 = m1;
	assert(m1.getnrInmatriculare() == m2.getnrInmatriculare());
}

void test_creeaza_masina() {
	Masina m{ "VS23MDJ","AUDI","A4","SPORT" };
	assert(m.getnrInmatriculare() == "VS23MDJ");
	assert(m.getproducator() == "AUDI");
	assert(m.getmodel() == "A4");
	assert(m.gettip() == "SPORT");
	m.setnrInmatriculare("CJ73BOK");
	m.setproducator("TESLA");
	m.setmodel("3");
	m.settip("AWD");
	assert(m.getnrInmatriculare() == "CJ73BOK");
	assert(m.getproducator() == "TESLA");
	assert(m.getmodel() == "3");
	assert(m.gettip() == "AWD");
}

void test_repo_adauga_masina() {
	repo rep;
	Masina m{ "BV12ABC","Tesla","3","AWD" };
	rep.inchiriere(m);
	const auto& masini = rep.getAll();
	assert(masini.size() == 1);
	try {
		rep.inchiriere(m);
		assert(false);
	}
	catch (const repoException& re) {
		assert(  re.getMesaj() == "Masina exista deja!");
	}
}
void test_repo_sterge_masina() {
	repo rep;
	Masina m{ "BV12ABC","Tesla","3","AWD" };
	Masina m1{ "BV13ABC","Tesla","3","AWD" };
	rep.inchiriere(m);
	rep.inchiriere(m1);
	const auto& masini = rep.getAll();
	assert(masini.size() == 2);
	rep.sterge(m);
	const auto& masini1 = rep.getAll();
	assert(masini1.size() == 1);
	try {
		rep.sterge(m);
		assert(false);
	}
	catch (std::string error) {
		assert(error== "Nu este nicio masina cu acest numar de inmatriculare");
	}
}

void test_repo_modifica_masina() {
	repo rep;
	Masina m1{ "BV12ABC","Tesla","3","AWD" };
	Masina m2{ "BV13ABC","Dacia","1998","SPORT" };
	rep.inchiriere(m1);
	assert(rep.cauta(m1) == true);
	rep.modifica(m1, m2);
	assert(rep.cauta(m1) == false);
	try {
		rep.modifica(m1, m2);
		assert(false);
	}
	catch (std::string error) {
		assert(error == "Nu este nicio masina cu acest numar de inmatriculare");
	}
	assert(rep.cauta(m2) == true);
}

void test_repo_find()
{
	repo rep;
	Masina m1{ "AAAJMKA","DFD","SF","DF" };
	rep.inchiriere(m1);
	try {
		rep.find("ABCLKLL");
		assert(false);
	}
	catch (std::string error) {
		assert(error == "Nu este nicio masina cu acest numar de inmatriculare");
	}
	
}

void test_validare_masina() {

	MasinaValidator valid;
	Masina m1{ "","TESLA","3","AWD" };
	try {
		valid.validate(m1);
		assert(false);
	}
	catch (const ValidateException& ve) {
		std::stringstream s;
		s << ve;
		assert(s.str() == "Numarul de inmatriculare nevalid! ");
	}
	Masina m2{ "aaaaaaa","","3","AWD" };
	try {
		valid.validate(m2);
		assert(false);
	}
	catch (const ValidateException& ve) {
		std::stringstream s;
		s << ve;
		assert(s.str() == "Producator vid! ");
	}
	Masina m3{ "aaaaaaa","TESLA","","AWD" };
	try {
		valid.validate(m3);
		assert(false);
	}
	catch (const ValidateException& ve) {
		std::stringstream s;
		s << ve;
		assert(s.str() == "Model Vid! ");
	}
	Masina m4{ "aaaaaaa","TESLA","3","" };
	try {
		valid.validate(m4);
		assert(false);
	}
	catch (const ValidateException& ve) {
		std::stringstream s;
		s << ve;
		assert(s.str() == "Tipul vid! ");
	}
}

void test_srv_adauga_masina() {
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service srv{ rep,val,s };
	srv.addMasina("aaaaaaa", "aaa", "aaa", "aaa");
	const auto& masini = srv.getAll();
	assert(masini.size() == 1);
}

void test_srv_sterge_masina() {
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service srv{ rep,val,s };
	srv.addMasina("aaaaaaa", "aaa", "aaa", "aaa");
	const auto& masini = srv.getAll();
	assert(masini.size() == 1);
	srv.deleteMasina("aaaaaaa");
	const auto& masini1 = srv.getAll();
	assert(masini1.size() == 0);
}

void test_srv_modifica_masina() {
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service srv{ rep,val ,s};
	srv.addMasina("aaaaaaa", "a", "a", "a");
	assert(srv.cautaMasina("aaaaaaa") == true);
	srv.modifyMasina("aaaaaaa", "bbbbbbb", "aa", "a", "aa");
	assert(srv.cautaMasina("aaaaaaa") == false);
}

void test_srv_filtrare() {
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service srv{ rep,val,s};
	srv.addMasina("aaaaaaa", "a", "a", "a");
	srv.addMasina("baaaaaa", "b", "c", "a");
	srv.filtrareDupaProducator("a");
	srv.filtrareDupaTip("a");

}

void test_srv_sortare() {
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service srv{ rep,val,s };
	srv.addMasina("aaaaaaa", "a", "b", "a");
	srv.addMasina("baaaaaa", "b", "c", "a");
	srv.addMasina("caaaaaa", "z", "c", "b");
	srv.addMasina("daaaaaa", "r", "g", "d");
	srv.addMasina("xaaaaaa", "a", "c", "a");
	srv.addMasina("xaagaaa", "a", "a", "a");
	srv.addMasina("ccaaaaa", "b", "c", "a");
	srv.addMasina("deaaaaa", "z", "c", "b");
	srv.addMasina("dadaaaa", "r", "g", "d");
	srv.SortByNrInmatriculare(1);
	srv.SortByTip(0);
	srv.SortByProducatorModel(1);
	srv.SortByNrInmatriculare(0);
	srv.SortByTip(1);
	srv.SortByProducatorModel(0);

}

void test_adauga_spalatorie()
{
	Masina m1{ "aaaaaaa","aaa","aa","a" };
	Masina m2{ "baaaaaa","aaa","aa","a" };
	Spalatorie s;
	assert(s.getAllMasiniS().size() == 0);
	s.addMasinaS(m1);
	assert(s.getAllMasiniS().size() == 1);
	s.addMasinaS(m2);
	assert(s.getAllMasiniS().size() == 2);
}

void test_goleste_spalatorie()
{
	Masina m1{ "aaaaaaa","aaa","aa","a" };
	Masina m2{ "baaaaaa","aaa","aa","a" };
	Spalatorie s;
	assert(s.getAllMasiniS().size() == 0);
	s.addMasinaS(m1);
	s.addMasinaS(m2);
	assert(s.getAllMasiniS().size() == 2);
	s.golesteS();
	assert(s.getAllMasiniS().size() == 0);

}

void test_addRandomMasini()
{
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service crt{ rep, val , s };
	crt.addMasina("bh13dls", "ford", "kuga", "suv");
	crt.addMasina("cs34rpx", "volvo", "s60", "sedan");
	crt.addMasina("bh25sis", "volkswagen", "passat", "combi");
	crt.addMasina("bh42cox", "bmw", "f45", "sedan");

	crt.addtoSpalatorie("bh13dls");
	assert(crt.getAllOferteSpalatorie().size() == 1);

	crt.addRandomToSpalatorie(2);
	assert(crt.getAllOferteSpalatorie().size() == 2);

	crt.emptySpalatorie();
	assert(crt.getAllOferteSpalatorie().size() == 0);
}

void test_undo()
{
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service crt{ rep,val,s };
	try {
		crt.undo();
	}
	catch (repoException ex) {
		assert(ex.getMesaj() == "Nu mai exista operatii\n");
	}
	assert(crt.getAll().size() == 0);
	crt.addMasina("ABVDFRT", "SDFS", "SGFF", "SDF");
	crt.addMasina("ABDFFRT", "SDD", "SK", "SDF");
	crt.addMasina("AASDFRT", "SDFS", "SGDF", "SDF");
	crt.addMasina("ABVNVJT", "SDD", "SGDF", "SDF");
	assert(crt.getAll().size() == 4);
	crt.undo();
	assert(crt.getAll().size() == 3);
	crt.undo();
	crt.undo();
	crt.undo();
	try {
		crt.undo();
	}
	catch (repoException ex) {
		assert(ex.getMesaj() == "Nu mai exista operatii\n");
	}
	crt.addMasina("ABVDFRT", "SDFS", "SGFF", "SDF");
	crt.addMasina("ABDFLLK", "SDD", "SK", "SDF");
	assert(crt.getAll().size() == 2);
	crt.modifyMasina("ABVDFRT", "BBMNMKD", "SF", "YUY", "OPO");
	assert(crt.getAll().at(0).getnrInmatriculare() == "BBMNMKD");

	
}

void test_service_exportFile()
{ 
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service crt{rep,val,s};
	crt.exportFile("faokdf", "crt");
}

void test_repo_exportFile()
{
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service crt{ rep,val,s };
	assert(crt.exportFile("date", "csv")==true);
	//verifica in fisier
}

void test_count_masini()
{
	repo rep;
	MasinaValidator val;
	Spalatorie s;
	service crt{ rep,val,s };
	crt.addMasina("ABVDFRT", "SDD", "SGFF", "SDF");
	crt.addMasina("ABDFLLK", "SDD", "SK", "SDF");
	crt.addMasina("ABHJFSA", "SAA", "SK", "SDF");
	assert(crt.count_masini("SDD") == 2);
}

void test_repo_prob()
{
	PRepo repoT;
	repoT.setProb(0);
	try {
		repoT.inchiriere(Masina("MMMMMMM", "DD", "DS", "DF"));
	}
	catch (repoException ex) {
		assert(true);
	}
	repoT.setProb(1);

	repoT.inchiriere(Masina("AAAAAAN", "F", "B", "D"));
	assert(repoT.cauta(Masina("AAAAAAN", "F", "B", "D")) == true);
	Masina m_1{ "AAAAAAN", "S", "D", "L" };
	Masina m_2{ "AMMMLKD", "GF", "G", "F" };
	repoT.modifica(m_1,m_2);
	try {
		repoT.inchiriere(m_2);
		assert(false);
	}
	catch (repoException r)
	{
		assert(true);
	}
	repoT.sterge(m_2);
	Masina m1{ "AAAAAAN", "S", "D", "L" };
	Masina m2{ "AMMMLKD", "GF", "G", "F" };
	Masina m3{ "DDDDDDD","FG","DF","DF" };
	repoT.inchiriere(m1);
	repoT.inchiriere(m2);
	repoT.inchiriere(m3);
	repoT.sterge(m2);
	assert(repoT.find("AAAAAAN").getnrInmatriculare() == m1.getnrInmatriculare());
	repoT.sterge(m1);
	repoT.sterge(m3);
	repoT.inchiriere(m1);
	try {
		repoT.inchiriere(m1);
		assert(false);
	}
	catch (repoException ex) {
		assert(true);
	}
	try {
		repoT.find("w");
		assert(false);
	}
	catch (repoException&) {};
	vector<Masina> lista = repoT.getAll();
}



void run_all_tests() {
	test_operator();
	test_creeaza_masina();
	test_repo_adauga_masina();
	test_repo_sterge_masina();
	test_repo_find();
	test_repo_modifica_masina();
	test_validare_masina();
	test_srv_adauga_masina();
	test_srv_sterge_masina();
	test_srv_modifica_masina();
	test_srv_sortare();
	test_srv_filtrare();
	test_adauga_spalatorie();
	test_goleste_spalatorie();
	test_addRandomMasini();
	test_undo();
	test_service_exportFile();
	test_count_masini();
	test_repo_exportFile();
	test_repo_prob();
}