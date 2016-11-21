/***********************
**
**	main.cpp
**	James Bilbrey
**	CMSC441 Fall 2016
**
***********************/

#include <cstdlib>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using std::cout;
using std::endl;
using std::stringstream;
using std::vector;

#include "substructure.h"

bool match(char a, char b) {

	switch (a) {
		case 'G':
			return b == 'H';
		case 'H':
			return b == 'G';
		case 'W':
			return b == 'T';
		case 'T':
			return b == 'W';
		default:
			return false;
	}
}

int countSolutions(vector< vector<Substructure*> > a, int x, int y) {

	Substructure* s = a.at(x).at(y);

	if (y - x < 5) {
		return 0;
	}

	if (s->numSolutions < 0) {
		s->numSolutions = (s->getPair() == 0
				? countSolutions(a, x, std::max(0, y - 1))
				: 1 + std::max(countSolutions(a, x + 1, y - 1), countSolutions(a, 0, std::max(0, x - 1))));
	}

	return s->numSolutions;
}

int main(int argc, char** argv)
{
	
	if (argc > 3) {
		cout << "Too many arguments! Usage: /fold <filename> [maxchars]" << endl;
		return 1;
	} else if (argc < 2) {
		cout << "Not enough arguments! Usage: /fold <filename> [maxchars]" << endl;
		return 1;
	}

	vector<char> line;
	std::ifstream infile(argv[1], std::ios_base::in);

	if (infile) {
		char ch;
		string input;
		getline(infile, input);
		stringstream reader(input);

		while (reader >> ch) {
			line.push_back(ch);
		}
	}

	infile.close();

	if (line.size() == 0) {
		cout << "No content was read from file " << argv[1] << endl;
		return 1;
	}

	char a, b;
	int max, upper, lower, dist = 5;

	if (argc == 2) {
		max = line.size() - 1;
	} else {
		max = std::min((int) line.size() - 1, atoi(argv[2]));
	}

	// Initialize data storage
	vector< vector<Substructure*> > subProblems(max, vector<Substructure*>(max));
	for (int i = 0; i < max; i++) {
		for (int j = 0; j < max; j++) {
			subProblems.at(i).at(j) = new Substructure(i, j);
		}
	}

	// timing variables
	int test1 = 0, test2 = 0;

	// Increase the size of subproblems until we complete the full problem
	while (dist < max) {

		lower = 0;
		upper = lower + dist;


		while (upper < max) {
			a = line.at(lower);
			b = line.at(upper);

			cout << lower << " x " << upper << endl;

			// If the endpoints are a match...
			if (match(a, b)) {

				// Store the match
				subProblems.at(lower).at(upper)->setPair(new Pair(lower, a, upper, b));

				// Inherit the largest contained subproblem, one unit diagonally
				subProblems.at(lower).at(upper)->setParent(subProblems[lower + 1][upper - 1]);

				// Inherit the subproblem consisting of all prior characters
				subProblems.at(lower).at(upper)->setSibling(subProblems[0][(lower > 0 ? lower - 1 : 0)]);

				// if the endpoints are not a match...
			} else {
				// contribute nothing, but inherit a slightly smaller contained subproblem
				subProblems.at(lower).at(upper)->setParent(subProblems[lower][upper - 1]);
			}

			upper++;
			lower++;
			test2++;
		}

		dist++;
		test1++;
	}

	cout << "Outer loop ran " << test1 << " times, inner loop ran " << test2 << " times." << endl;

	// Print out the character sequence in question
	cout << "    ";
	int c;
	for (c = 0; c < max; c++) {
		cout << "  " << line.at(c);
	}
	cout << endl;

	// Column headers
	cout << "    ";
	for (int j = 0; j < max; j++) {
		cout << (j < 10 ? "  " : " ") << j;
	}
	cout << endl;

	for (int i = 0; i < max; i++) {
		// Row headers
		cout << i << (i < 10 ? "  " : " ") << "[";
		for (int j = 0; j < max; j++) {
			//c = subProblems[i][j].countSolutions();
			c = countSolutions(subProblems, i, j);
			cout << (c < 10 ? "  " : " ") << c;
		}
		cout << "]" << endl;
	}

	return 0;
}
