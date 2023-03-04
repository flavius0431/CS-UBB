#include"consola.h"
#include"service.h"
#include"repo.h"
#include"validator.h"
#include"teste.h"


void adaugaMasini(service& crt) {
	crt.addMasina("VS23FOX", "NUBIRA", "sws", "SPORT");
	crt.addMasina("AAAAAAA", "AFADN", "SMDF", "KSFBHD");
	crt.addMasina("BBBBBBB", "NVXNV", "IIUD", "NNHK");
	crt.addMasina("CCCCCCC", "LSKS", "KLDJ", "JSHHDJ");
}


int main() {
	run_all_tests();
	repo rep;
	//PRepo  rep;
	//rep.setProb(0.5);
	MasinaValidator val;
	Spalatorie s;
	service crt{ rep,val ,s};
	//adaugaMasini(crt);
	consola ui{ crt };
	ui.start();

	

}