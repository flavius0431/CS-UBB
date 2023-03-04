#include"consola.h"
#include"service.h"
#include"repo.h"
#include"validator.h"
#include"teste.h"

void adaugaMasini(service& crt) {
	crt.addMasina("VS23FOX", "NUBIRA", "sws", "SPORT");
}


int main() {
	/*run_all_tests();
	repo rep;
	MasinaValidator val;
	service crt{ rep,val };
	adaugaMasini(crt);
	consola ui{ crt };
	ui.start();*/

	
	return 0;
}