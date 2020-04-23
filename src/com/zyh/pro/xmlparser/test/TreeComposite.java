package com.zyh.pro.xmlparser.test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class TreeComposite extends TreeLeaf {

	private final List<TreeLeaf> children;

	public TreeComposite(String tag) {
		super(tag);
		children = new ArrayList<>();
	}

	public TreeLeaf getChildAt(int at) {
		return children.get(at);
	}

	public List<TreeLeaf> getChildren() {
		return children;
	}

	public void addChild(TreeLeaf child) {
		children.add(child);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("TreeComposite(" + tag + ")");
		for (TreeLeaf child : children)
			result.append(child.toString());
		return result.toString();
	}
}
