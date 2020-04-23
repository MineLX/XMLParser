package com.zyh.pro.xmlparser.test;

import com.zyh.pro.xmlparser.main.XMLNode;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class XMLNodeTest {
	@Test
	public void toList() {
		XMLNode root = getSimpleRootChild();
		assertThat(root.toList().toString(), is("[XMLNode(root), XMLNode(child)]"));
	}

	@Test
	public void iterate() {
		XMLNode root = getSimpleRootChild();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outputStream);
		root.forEach(stream::print);
		assertThat(new String(outputStream.toByteArray()), is("XMLNode(root)XMLNode(child)"));
	}

	private XMLNode getSimpleRootChild() {
		XMLNode root = new XMLNode("root");
		XMLNode child = new XMLNode("child");
		root.addChild(child);
		return root;
	}

	@Test
	public void dump_with_properties() {
		XMLNode node = new XMLNode("root");
		node.addProperty("id", "0");
		XMLNode child = new XMLNode("child");
		child.addProperty("id", "1");
		child.addProperty("name", "gentle");
		node.addChild(child);

		assertDump(node, "<root id=\"0\"><child name=\"gentle\" id=\"1\"></child></root>");
	}

	@Test
	public void simple_dump() {
		XMLNode node = new XMLNode("root");
		node.addChild(new XMLNode("child"));
		node.addChild(new XMLNode("child"));

		assertDump(node, "<root><child></child><child></child></root>");
	}

	@Test
	public void convertWithLeaf() {
		XMLNode root = getSimpleRootChild();

		TreeLeaf treeLeaf = root.convertWithLeaf(
				groupNode -> new TreeComposite(groupNode.getTag()),
				childNode -> new TreeLeaf(childNode.getTag()),
				TreeComposite::addChild);
		assertThat(treeLeaf.toString(), is("TreeComposite(root)TreeLeaf(child)"));
	}

	@Test
	public void hasChild() {
		XMLNode root = new XMLNode("root");
		assertThat(root.hasChild(), is(false));
		root.addChild(new XMLNode("child"));
		assertThat(root.hasChild(), is(true));
	}

	@Test
	public void convertTree() {
		XMLNode root = getSimpleRootChild();
		TreeItem converted = root.convert(xmlNode -> new TreeItem(xmlNode.getTag()));

		assertThat(converted.toString(), is("TreeItem(root)"));
		assertThat(converted.getChildren().toString(), is("[TreeItem(child)]"));
	}

	@Test
	public void removeProperty() {
		XMLNode root = new XMLNode("root");
		root.addProperty("hello", "world");
		assertThat(root.getProperties().size(), is(1));
		root.removeProperty("hello");
		assertThat(root.getProperties().size(), is(0));
	}

	@Test
	public void removeFromParent() {
		XMLNode root = new XMLNode("root");
		XMLNode child = new XMLNode("child");
		root.addChild(child);
		assertThat(root.getChildren().size(), is(1));
		child.removeFromParent();
		assertThat(root.getChildren().size(), is(0));
	}


	@Test
	public void removeChild() {
		XMLNode root = new XMLNode("root");
		XMLNode child = new XMLNode("child");
		root.addChild(child);
		assertThat(root.getChildren().size(), is(1));
		root.removeChild(child);
		assertThat(root.getChildren().size(), is(0));
	}

	@Test
	public void getParent() {
		XMLNode root = new XMLNode("root");
		XMLNode child = new XMLNode("child");
		root.addChild(child);
		assertThat(child.getParent(), is(root));
	}

	@Test
	public void rename() {
		XMLNode simpleName = new XMLNode("simpleName");
		assertThat(simpleName.getTag(), is("simpleName"));
		simpleName.rename("renamed");
		assertThat(simpleName.getTag(), is("renamed"));
	}

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
	public void addChild_getChildAt_test() {
		XMLNode XMLNode = new XMLNode("node");
		XMLNode addend = new XMLNode("inner1");
		XMLNode.addChild(addend);

		assertThat(XMLNode.getChildAt(0),
				is(addend));
	}

	private void assertDump(XMLNode node, String expected) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		node.dump(new PrintStream(outputStream));
		assertThat(new String(outputStream.toByteArray()), is(expected));
	}
}
