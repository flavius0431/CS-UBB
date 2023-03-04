#include "validator.h"
#include<assert.h>
#include<sstream>
#include<string>

void MasinaValidator::validate(const Masina& m) {
	vector<string>msgs;
	if (m.getnrInmatriculare().size() != 7)msgs.push_back("Numarul de inmatriculare nevalid!");
	if (m.getproducator().size() == 0)msgs.push_back("Producator vid!");
	if (m.getmodel().size()==0)msgs.push_back("Model Vid!");;
	if (m.gettip().size() == 0)msgs.push_back("Tipul vid!");
	if (msgs.size() > 0) {
		throw ValidateException(msgs);
	}
}

ostream& operator<<(ostream& out, const ValidateException& ex) {
	for (const auto& msg : ex.msgs) {
		out << msg << " ";
	}
	return out;
}



