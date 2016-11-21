/***********************
**
**  substructure.h
**	James Bilbrey
**	CMSC441 Fall 2016
**
***********************/

#ifndef CMSC441_SUBSTRUCTURE_H
#define CMSC441_SUBSTRUCTURE_H

#include <cmath>

#include "pair.h"
using std::string;

class Substructure {

	int x, y, numSolutions;
	Substructure *parent, *sibling;
	Pair *pair;

public:
	Substructure(int ix, int iy)
			: x(ix), y(iy), numSolutions(-1), parent(0), sibling(0), pair(0) {}

	int countSolutions()
	{
		/* if (x == 29 && y == 49) {
			System.out.println("Pair: " + (pair == null ? 0 : 1));
			System.out.println("Parent: " + parent);
			System.out.println("Parent: " + (parent == null ? 0 : parent.countSolutions()));
			System.out.println("Parent: " + parent);
			System.out.println("Sibling: " + (sibling == null ? 0 : sibling.countSolutions()));
			System.out.println("Total: " + (pair == null ? 0 : 1)
					+ (parent == null ? 0 : parent.countSolutions())
					+ (sibling == null ? 0 : sibling.countSolutions()));
		} */

		/*return (pair == null ? 0 : 1)
				+ (parent == null ? 0 : parent.countSolutions())
				+ (sibling == null ? 0 : sibling.countSolutions());*/
		return (pair == 0
				? (parent == 0 ? 0 : parent->countSolutions())
				: std::max((parent == 0 ? 0 : parent->countSolutions()), (sibling == 0 ? 0 : sibling->countSolutions())));
	}

	Substructure* getParent() { return parent; }

	void setParent(Substructure* p) { parent = p; }

	Pair* getPair() { return pair; }

	void setPair(Pair* p) { pair = p; }
	
	string printPair()
	{
		if (pair == 0) {
			return "null";
		} else {
			return pair->toString();
		}
	}

	Substructure* getSibling() { return sibling; }

	void setSibling(Substructure* s) { sibling = s; }

	string toString()
	{
		std::stringstream ret;
		ret << "Subproblem " << x << " x " << y << ": " << countSolutions()
			   << "\nPair: " << getPair()
			   << "\nParent: " << (parent == 0 ? "null" : getParent()->printPair())
			   << "\nSibling: " << (getSibling() == 0 ? "null" : getSibling()->printPair());
		return ret.str();
	}
};

#endif //CMSC441_SUBSTRUCTURE_H
