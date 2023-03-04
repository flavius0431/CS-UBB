#include"consola.h"
#include"service.h"
#include"repo.h"
#include"validator.h"
#include"teste.h"
#include<crtdbg.h>
#define _CRTDBG_MAP_ALLOC

void adaugaMasini(service& crt) {
	crt.addMasina("VS23FOX", "aaa", "b", "dfsd");
	crt.addMasina("CJ23MAN", "aaa", "aaa", "aaaa");
	crt.addMasina("AB23KHS", "bbb", "abb", "aaaa");
	crt.addMasina("VS12KSL", "ccc", "abb", "asaaa");
}

void startmain()
{
	run_all_tests();
	repo rep;
	MasinaValidator val;
	service crt{ rep,val };
	adaugaMasini(crt);
	consola ui{ crt };
	ui.start();
}

int main() {
	
	startmain();
	_CrtDumpMemoryLeaks();
	
	return 0;
}