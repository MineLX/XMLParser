package com.zyh.pro.xmlparser.test;

import com.zyh.pro.xmlparser.main.ITree;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public abstract class ITreeTest<T extends ITree<T>> {
	@Test
	public void children() {
		T root = toITree("root");
		T child1 = toITree("child1");
		root.addChild(child1);
		T child2 = toITree("child2");
		root.addChild(child2);

		assertThat(root.toString(), is("TreeItem(root)"));
		assertThat(root.getChildren().toString(), is("[" + child1.toString() + ", " + child2.toString() + "]"));
	}

	protected abstract T toITree(String nodeName);
}
