package edu.umbc.bilbrey1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		String line = null;

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

		char a, b;
		int max, upper, lower, dist = 5;

		if (args.length == 1) {
			max = line.length() - 1;
		} else {
			max = Math.min(line.length() - 1, Integer.parseInt(args[1]));
		}

		Substructure[][] subProblems = new Substructure[max][max];
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				subProblems[i][j] = new Substructure();
			}
		}

		while (dist < max) {

			lower = 0;
			upper = lower + dist;

			while (upper < max) {
				a = line.charAt(lower);
				b = line.charAt(upper);

				if (match(a, b)) {
					subProblems[lower][upper].setPair(new Pair(lower, a, upper, b));
					subProblems[lower][upper].setParent(subProblems[lower + 1][upper - 1]);
					subProblems[lower][upper].setSibling(subProblems[0][(lower > 0 ? lower - 1 : 0)]);
				} else {
					subProblems[lower][upper].setParent(subProblems[lower][upper - 1]);
				}

				upper++;
				lower++;
			}

			dist++;
		}

		System.out.print("    ");
		for (int j = 0; j < max; j++) {
			System.out.print((j < 10 ? "  " : " ") + j);
		}
		System.out.println();
		for (int i = 0; i < max; i++) {
			System.out.print(i + (i < 10 ? "  " : " ") + "[");
			for (int j = 0; j < max; j++) {
				//System.out.print(j < 10 ? " " : "  ");
				System.out.print("  " + subProblems[i][j].countSolutions());
			}
			System.out.println("]");
		}

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

	static char invert(char a) {
		switch (a) {
			case 'G':
				return 'H';
			case 'H':
				return 'G';
			case 'W':
				return 'T';
			default:
				return 'W';
		}
	}

	static String invert(String seq) {
		StringBuilder builder = new StringBuilder();

		for (char a : seq.toCharArray()) {
			builder.append(invert(a));
		}

		return builder.toString();
	}
}
