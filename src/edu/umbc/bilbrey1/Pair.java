package edu.umbc.bilbrey1;

/**
 * Represents a matched pair.
 */
public class Pair {

	private char valA, valB;
	private int posA, posB;

	public Pair(int positionA, char valueA, int positionB, char valueB) {
		valA = valueA;
		valB = valueB;
		posA = positionA;
		posB = positionB;
	}

	public void set(int positionA, char valueA, int positionB, char valueB) {
		valA = valueA;
		valB = valueB;
		posA = positionA;
		posB = positionB;
	}

	@Override
	public String toString() {
		return "{" + posA + "-" + valA + ", " + posB + "-" + valB + "}";
	}

	public int getUpper() {
		return posB;
	}

	public int getLower() {
		return posA;
	}
}
