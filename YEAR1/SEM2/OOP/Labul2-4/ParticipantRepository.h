#pragma once
#include "Participant.h"
#include "Vector.h"

typedef struct
{
	VectorDinamic* participanti;
}ParticipantRepo;

ParticipantRepo* createRepo();
void destroyRepo(ParticipantRepo* v);

int getLen(ParticipantRepo* v);

int add(ParticipantRepo* v, Participant* p);

Participant* getParticipantOnPos(ParticipantRepo* v, int pos);

int del(ParticipantRepo* v, int nr);

int update(ParticipantRepo* v, int nr, int scor_nou);

int crescator(Participant* p1, Participant* p2);
int descrescator(Participant* p1, Participant* p2);
ParticipantRepo* sortarePartRepo(ParticipantRepo* v, int (*cmp)(Participant* p1, Participant* p2));

ParticipantRepo* filterbyscorRepo(ParticipantRepo* v, int valoare);
ParticipantRepo* filterbyliteraRepo(ParticipantRepo* v, char litera);


void initParticipantRepoForTests(ParticipantRepo* v);
void testsRepo();
