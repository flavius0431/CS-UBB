#pragma once
#include"Masina.h"
#include"repo.h"

class ActiuneUndo {
public:
	virtual void doUndo()=0;
	virtual ~ActiuneUndo(){};
};

class UndoAdauga :public ActiuneUndo {
	Masina mAdaugat;
	repo& rep;
public:
	UndoAdauga(repo& rep, const Masina& m): rep{ rep }, mAdaugat{ m }{}
	void doUndo()override {
		rep.sterge(mAdaugat);
	}
};

class UndoSterge : public ActiuneUndo {
	Masina mSters;
	repo& rep;
public:
	UndoSterge(repo& rep, Masina& mSters ): rep{rep},mSters{mSters}{}
	void doUndo()override {
		rep.inchiriere(mSters);
	}
};


class UndoModify :public ActiuneUndo {
	Masina oldM, newM;
	repo& rep;
public:
	UndoModify(repo& rep, Masina& oldM,Masina& newM): rep{rep},oldM{oldM},newM{newM}{}
	void doUndo()override {
		rep.sterge(newM);
		rep.inchiriere(oldM);
	}
};