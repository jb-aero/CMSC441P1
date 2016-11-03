package edu.umbc.bilbrey1;

import java.util.List;

/**
 * Represents a solved substructure.
 */
class Substructure {

	public int numSolutions = -1; // public because java is misbehaving
	private int x, y;
	private Substructure parent = null,
			sibling = null;
	private Pair pair = null;

	Substructure(int x, int y) {
		this.x = x;
		this.y = y;
	}

	int countSolutions() {
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
		return (pair == null
				? (parent == null ? 0 : parent.countSolutions())
				: Math.max((parent == null ? 0 : parent.countSolutions()), (sibling == null ? 0 : sibling.countSolutions())));
	}

	Substructure getParent() {
		return parent;
	}

	void setParent(Substructure p) {
		parent = p;
	}

	Pair getPair() {
		return pair;
	}

	void setPair(Pair p) {
		pair = p;
	}

	Substructure getSibling() {
		return sibling;
	}

	void setSibling(Substructure s) {
		sibling = s;
	}

	@Override
	public String toString() {
		return "Subproblem " + x + " x " + y + ": " + countSolutions()
				+ "\nPair: " + getPair()
				+ "\nParent: " + (parent == null ? null : getParent().getPair())
				+ "\nSibling: " + (getSibling() == null ? null : getSibling().getPair());
	}
}
