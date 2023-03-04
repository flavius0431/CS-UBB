#pragma once

typedef struct
{
	char* nume;
	char* prenume;
	int scor;

}Participant;

Participant* createParticipant(char* nume, char* prenume, int scor);
void destroyParticipant(Participant* p); //eliberam memoria

char* getNume(Participant* p);
char* getPrenume(Participant* p);
int getScor(Participant* p);
int setScor(Participant* p, int scor_nou);

/// <summary>
/// Functie care copiaza un participant
/// </summary>
/// <param name="p"></param>
/// <returns></returns>
Participant* copyParticipant(Participant* p);

void toString(Participant* p, char str[]);

void testP();

