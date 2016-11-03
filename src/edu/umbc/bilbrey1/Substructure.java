package edu.umbc.bilbrey1;

import java.util.List;

/**
 * Represents a solved substructure.
 */
class Substructure {

	private Substructure parent = null,
			sibling = null;
	private Pair pair = null;

	public int countSolutions() {
		return (pair == null ? 0 : 1)
				+ (parent == null ? 0 : parent.countSolutions())
				+ (sibling == null ? 0 : sibling.countSolutions());
	}

	public Substructure getParent() {
		return parent;
	}

	public void setParent(Substructure p) {
		parent = p;
	}

	public Pair getPair() {
		return pair;
	}

	public void setPair(Pair p) {
		pair = p;
	}

	public Substructure getSibling() {
		return sibling;
	}

	public void setSibling(Substructure s) {
		sibling = s;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
