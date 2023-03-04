#include "teste.h"
#include<vector>
#include"Masina.h"

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
		assert(error == "Nu este nicio masina cu acest numar de inmatriculare");
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

void test_validare_masina() {

	MasinaValidator valid;
	Masina m1{ "","TESLA","3","AWD" };
	try {
		valid.validate(m1);
		assert(false);
	}
	catch ( ValidateException& ve) {
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

void test_srv_filtrare() {
	repo rep;
	MasinaValidator val;
	service srv{ rep,val };
	srv.addMasina("aaaaaaa", "a", "a", "a");
	srv.addMasina("baaaaaa", "b", "c", "a");
	srv.filtrareDupaProducator("a");
	srv.filtrareDupaTip("a");

}

void test_srv_sortare() {
	repo rep;
	MasinaValidator val;
	service srv{ rep,val };
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
template <typename MyVector>
MyVector testCopyIterate(MyVector v) {
	
	Masina p{ "aaaaaaa","a","a","a"};
	v.push_back(p);
	return v;
}

template <typename MyVector>
void addPets(MyVector& v, int cate) {
	for (int i = 0; i < cate; i++) {
		Masina p{ "aaaaaaa","a","a","a" };
		v.push_back(p);
	}
}

/*
Testare constructori / assignment
E template pentru a refolosi la diferite variante de VectorDinamic din proiect
*/
template <typename MyVector>
void testCreateCopyAssign() {
	MyVector v;//default constructor
	addPets(v, 100);
	assert(v.size() == 100);
	assert(v[50].getnrInmatriculare() == "aaaaaaa");

	MyVector v2{ v };//constructor de copiere
	assert(v2.size() == 100);
	assert(v2[50].getproducator() == "a");

	MyVector v3;//default constructor
	v3 = v;//operator=   assignment
	assert(v3.size() == 100);
	assert(v3[50].gettip() == "a");

	auto v4 = testCopyIterate(v3);
	assert(v4.size() == 101);
	assert(v4[50].getmodel() == "a");
}

/*
  Test pentru move constructor si move assgnment
*/
template <typename MyVector>
void testMoveConstrAssgnment() {
	std::vector<MyVector> v;
	//adaugam un vector care este o variabila temporara
	//se v-a apela move constructor
	v.push_back(MyVector{});

	//inseram, la fel se apeleaza move costructor de la vectorul nostru
	v.insert(v.begin(), MyVector{});

	assert(v.size() == 2);

	MyVector v2;
	addPets(v2, 50);

	v2 = MyVector{};//move assignment

	assert(v2.size() == 0);

}

template <typename MyVector>
void testAll() {
	testCreateCopyAssign<MyVector>();
	testMoveConstrAssgnment<MyVector>();
}



void run_all_tests() {
	//testAll<vector<Masina>>();
	test_operator();
	test_creeaza_masina();
	test_repo_adauga_masina();
	test_repo_sterge_masina();
	test_repo_modifica_masina();
	test_validare_masina();
	test_srv_adauga_masina();
	test_srv_sterge_masina();
	test_srv_modifica_masina();
	test_srv_filtrare();
	test_srv_sortare();
}