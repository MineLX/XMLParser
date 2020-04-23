package com.zyh.pro.xmlparser.test;

public class TreeLeaf {

	final String tag;

	public TreeLeaf(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "TreeLeaf(" + tag + ")";
	}
}
