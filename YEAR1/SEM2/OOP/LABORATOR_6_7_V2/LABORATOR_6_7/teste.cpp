#include "teste.h"

void test_operator() {
	Masina m1{ "aaaaaaa","aaa","aa","a" };
	Masina m2{ "","","","" };
	m2 = m1;
	//m1 = m1;
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
	catch (const repoException& re) {
		assert(re.getMesaj() == "Masina nu exista\n");
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
	catch (const repoException& re) {
		assert(re.getMesaj() == "Masina nu exista!\n");
	}
	assert(rep.cauta(m2) == true);

}

void test_validare_masina() {

	MasinaValidator valid;
	Masina m1{ "","TESLA","3","AWD" };
	try {
		valid.validate(m1);
		assert(false);
	}
	catch ( ValidateException ve) {
		std::stringstream s;
		s << ve;
		assert(s.str() == "Numarul de inmatriculare nevalid! ");
	}
	Masina m2{ "aaaaaaa","","3","AWD" };
	try {
		valid.validate(m2);
		assert(false);
	}
	catch ( ValidateException ve) {
		std::stringstream s;
		s << ve;
		assert(s.str() == "Producator vid! ");
	}
	Masina m3{ "aaaaaaa","TESLA","","AWD" };
	try {
		valid.validate(m3);
		assert(false);
	}
	catch ( ValidateException ve) {
		std::stringstream s;
		s << ve;
		assert(s.str() == "Model Vid! ");
	}
	Masina m4{ "aaaaaaa","TESLA","3","" };
	try {
		valid.validate(m4);
		assert(false);
	}
	catch ( ValidateException ve) {
		std::stringstream s;
		s << ve;
		assert(s.str() == "Tipul vid! ");
	}
}

void test_srv_adauga_masina() {
	repo rep;
	MasinaValidator val;
	service srv{ rep,val };
	srv.addMasina("aaaaaaa", "aaa", "aaa", "aaa");
	const auto& masini = srv.getAll();
	assert(masini.size() == 1);
}

void test_srv_sterge_masina() {
	repo rep;
	MasinaValidator val;
	service srv{ rep,val };
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
	service srv{ rep,val };
	srv.addMasina("aaaaaaa", "a", "a", "a");
	assert(srv.cautaMasina("aaaaaaa") == true);
	srv.modifyMasina("aaaaaaa", "bbbbbbb", "aa", "a", "aa");
	assert(srv.cautaMasina("aaaaaaa") == false);
}

void run_all_tests() {
	test_operator();
	test_creeaza_masina();
	test_repo_adauga_masina();
	test_repo_sterge_masina();
	test_repo_modifica_masina();
	test_validare_masina();
	test_srv_adauga_masina();
	test_srv_sterge_masina();
	test_srv_modifica_masina();
}