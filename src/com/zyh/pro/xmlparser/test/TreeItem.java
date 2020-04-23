package com.zyh.pro.xmlparser.test;

import com.zyh.pro.xmlparser.main.ITree;

import java.util.ArrayList;
import java.util.List;

public class TreeItem implements ITree<TreeItem> {

	private final String nodeName;

	private final List<TreeItem> children;

	TreeItem(String nodeName) {
		this.nodeName = nodeName;

		children = new ArrayList<>();
	}

	@Override
	public List<TreeItem> getChildren() {
		return children;
	}

	@Override
	public TreeItem getChildAt(int at) {
		return children.get(at);
	}

	@Override
	public void addChild(TreeItem child) {
		children.add(child);
	}

	@Override
	public TreeItem self() {
		return this;
	}

	@Override
	public boolean hasChild() {
		return children.size() != 0;
	}

	@Override
	public String toString() {
		return "TreeItem(" + nodeName + ")";
	}
}
