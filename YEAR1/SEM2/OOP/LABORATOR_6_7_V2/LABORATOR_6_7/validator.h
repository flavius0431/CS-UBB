#pragma once
#include<string>
#include"Masina.h"
#include"vector.h"


using std::string;
using std::ostream;

class ValidateException {
	vector<string> msgs;
public:
	
	ValidateException(const vector<string>& errors) :msgs{ errors }{}
	//functie friend (folosim mereu membru privat msg)
	friend ostream& operator<<(ostream& out,  ValidateException ex);
};

ostream& operator<<(ostream& out,  ValidateException ex);

class MasinaValidator {
public:
	void validate(const Masina& m);
};


