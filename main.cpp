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
using std::ifstream;
using std::string;
using std::stringstream;
using std::vector;

#include "substructure.h"

int main(int argc, char** argv) {
	
	if (argc > 3) {
		cout << "Too many arguments! Usage: /fold <filename> [maxchars]" << endl;
		return 1;
	} else if (argc < 2) {
		cout << "Not enough arguments! Usage: /fold <filename> [maxchars]" << endl;
		return 1;
	}

	

	return 0;
}
