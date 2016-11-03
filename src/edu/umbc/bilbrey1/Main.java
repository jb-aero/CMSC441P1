package edu.umbc.bilbrey1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		String line = null;

		// BEGIN FILE READING
		try {

			FileReader reader = new FileReader(args[0]);
			BufferedReader buffer = new BufferedReader(reader);

			line = buffer.readLine();
			buffer.close();

		} catch (FileNotFoundException fnfe) {
			System.out.println("Could not find file " + args[0]);
		} catch (IOException ioe) {
			System.out.println("Error opening " + args[0]);
		}

		if (line == null) {
			System.out.println("No content to process.");
			return;
		}
		// END FILE READING

		char a, b;
		int max, upper, lower, dist = 5;

		// Determine amount of data to read
		if (args.length == 1) {
			max = line.length() - 1;
		} else {
			max = Math.min(line.length() - 1, Integer.parseInt(args[1]));
		}

		// Initialize data storage
		Substructure[][] subProblems = new Substructure[max][max];
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				subProblems[i][j] = new Substructure(i, j);
			}
		}

		// timing variables
		int test1 = 0, test2 = 0;

		// Increase the size of subproblems until we complete the full problem
		while (dist < max) {

			lower = 0;
			upper = lower + dist;


			while (upper < max) {
				a = line.charAt(lower);
				b = line.charAt(upper);

				System.out.println(lower + " x " + upper);

				// If the endpoints are a match...
				if (match(a, b)) {

					// Store the match
					subProblems[lower][upper].setPair(new Pair(lower, a, upper, b));

					// Inherit the largest contained subproblem, one unit diagonally
					subProblems[lower][upper].setParent(subProblems[lower + 1][upper - 1]);

					// Inherit the subproblem consisting of all prior characters
					subProblems[lower][upper].setSibling(subProblems[0][(lower > 0 ? lower - 1 : 0)]);

				// if the endpoints are not a match...
				} else {
					// contribute nothing, but inherit a slightly smaller contained subproblem
					subProblems[lower][upper].setParent(subProblems[lower][upper - 1]);
				}

				upper++;
				lower++;
				test2++;
			}

			dist++;
			test1++;
		}

		System.out.println("Outer loop ran " + test1 + " times, inner loop ran " + test2 + " times.");

		// Print out the character sequence in question
		System.out.print("    ");
		int c;
		for (c = 0; c < max; c++) {
			System.out.print("  " + line.charAt(c));
		}
		System.out.println();

		// Column headers
		System.out.print("    ");
		for (int j = 0; j < max; j++) {
			System.out.print((j < 10 ? "  " : " ") + j);
		}

		System.out.println();
		for (int i = 0; i < max; i++) {

			// Row headers
			System.out.print(i + (i < 10 ? "  " : " ") + "[");
			for (int j = 0; j < max; j++) {
				//c = subProblems[i][j].countSolutions();
				c = countSolutions(subProblems, i, j);
				System.out.print((c < 10 ? "  " : " ") + c);
			}
			System.out.println("]");
		}

		/*
		System.out.println(subProblems[29][49]);
		System.out.println("Parents:\n" + subProblems[30][48]);
		System.out.println(subProblems[30][47]);
		System.out.println(subProblems[30][46]);
		System.out.println(subProblems[31][45]);
		System.out.println(subProblems[0][29]);
		System.out.println("\nSiblings:\n" + subProblems[0][28]);
		System.out.println(subProblems[0][27]);
		System.out.println(subProblems[1][26]);
		*/

		/*

		while (upper - lower > 4) {
			a = line.charAt(lower);
			b = line.charAt(upper);

			if (match(a, b)) {
				pairs.add(new Pair(upper, a, lower, b));
			}

			upper--;
			lower++;
		}

		System.out.println("Total matches: " + pairs.size() + " out of " + (max + 1));
		System.out.print("{ ");
		for (Pair p : pairs) {
			System.out.print(p.toString() + ", ");
		}
		System.out.print(" }");
		*/
	}

	static boolean match(char a, char b) {

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

	static int countSolutions(Substructure[][] a, int x, int y) {

		Substructure s = a[x][y];

		if (y - x < 5) {
			return 0;
		}

		if (s.numSolutions < 0) {
			s.numSolutions = (s.getPair() == null ? countSolutions(a, x, Math.max(0, y - 1))
					: 1 + Math.max(countSolutions(a, x + 1, y - 1), countSolutions(a, 0, Math.max(0, x - 1))));
		}

		return s.numSolutions;
	}
}
