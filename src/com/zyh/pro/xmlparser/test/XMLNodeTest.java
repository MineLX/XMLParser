package com.zyh.pro.xmlparser.test;

import com.zyh.pro.xmlparser.main.XMLNode;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class XMLNodeTest {

	@Test
	public void getProperties_test() {
		XMLNode node = new XMLNode("node");
		node.addProperty("id", "0");
		assertThat(node.getProperties().size(), is(1));
		node.addProperty("id", "1");
		assertThat(node.getProperties().size(), is(1));
		node.addProperty("name", "myName");
		assertThat(node.getProperties().size(), is(2));
	}

	@Test
	public void addProperty_getProperty_test() {
		XMLNode node = new XMLNode("node");
		node.addProperty("key", "value");
		assertThat(node.getProperty("key"), is("value"));
		assertNull(node.getProperty("nonExists"));
	}

	@Test
	public void addChild_getChildAt_test(){
		XMLNode XMLNode = new XMLNode("node");
		XMLNode addend = new XMLNode("inner1");
		XMLNode.addChild(addend);

		assertThat(XMLNode.getChildAt(0),
				is(addend));
	}
}
