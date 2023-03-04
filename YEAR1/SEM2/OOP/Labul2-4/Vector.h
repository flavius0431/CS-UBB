#pragma once
#define CAPACITY 10

typedef void* TElement;
typedef void (*DestroyElementFunctionType)(TElement);

typedef struct
{
	TElement* elems;
	int length;			// lungimea actuala a vectorului
	int capacity;		// capacitatea maxima a vectorului
	DestroyElementFunctionType destroyElemFct; // function pointer to the function responsible with deallocating the elements stored in the array
} VectorDinamic;

VectorDinamic* createVectorDinamic(int capacity, DestroyElementFunctionType destroyElemFct);

void destroyVector(VectorDinamic* vd);

void addTElem(VectorDinamic* vd, TElement t);

void deleteTElem(VectorDinamic* vd, int pos);

int getVectorLen(VectorDinamic* vd);
void swapTElemets(VectorDinamic* vd, int index1, int index2);

TElement get(VectorDinamic* vd, int pos);

void testVectorDinamic();