#include <iostream>
#include<crtdbg.h>
#include "coada.h"
#include "TestExtins.h"
#include "TestScurt.h"
using namespace std;


int main() {

	testAll();
	testAllExtins();

	cout << "sfarsit teste" << endl;

	_CrtDumpMemoryLeaks();
	return 0;
}
