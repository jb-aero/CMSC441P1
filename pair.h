/***********************
**
**  pair.h
**	James Bilbrey
**	CMSC441 Fall 2016
**
***********************/

#ifndef CMSC441_PAIR_H
#define CMSC441_PAIR_H

#include <sstream>

class Pair
{
	char valA, valB;
	int posA, posB;

public:

	Pair(int positionA, char valueA, int positionB, char valueB)
		: valA(valueA), valB(valueB), posA(positionA), posB(positionB) {}

	void set(int positionA, char valueA, int positionB, char valueB)
	{
		valA = valueA;
		valB = valueB;
		posA = positionA;
		posB = positionB;
	}

	std::string toString()
	{
		std::stringstream ret;
		ret << "{" << posA << "-" << valA << ", " << posB << "-" << valB << "}";
		return ret.str();
	}

	int getUpper() { return posB; }

	int getLower() { return posA; }
};

#endif //CMSC441_PAIR_H
