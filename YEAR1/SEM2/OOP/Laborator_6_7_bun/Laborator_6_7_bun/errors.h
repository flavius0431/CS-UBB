#pragma once

#include<iostream>

class Errors {
public:

	std::string MasinaNotFound = "Nu este nicio masina cu acest numar de inmatriculare";
	std::string NrDuplicat = "Este deja o masina cu acest numar de inmatriculare";
	std::string IndexOut = "Indexul specificat este mai mic decat 0 sau mare decat marimea vectorului";

};