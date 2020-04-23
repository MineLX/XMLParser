package com.zyh.pro.xmlparser.test;

public class TreeItemTest extends ITreeTest<TreeItem> {
	@Override
	protected TreeItem toITree(String nodeName) {
		return new TreeItem(nodeName);
	}
}
