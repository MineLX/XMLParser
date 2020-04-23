package com.zyh.pro.xmlparser.main;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Consumer;

public class XMLNode implements ITree<XMLNode> {

	private final List<XMLNode> children;

	private final Map<String, String> properties;

	private String tagName;

	private XMLNode parent;

	public XMLNode(String tagName) {
		this.tagName = tagName;

		children = new ArrayList<>();
		properties = new HashMap<>();
	}

	@Override
	public String toString() {
		return "XMLNode(" + tagName + ")";
	}

	public String getTag() {
		return tagName;
	}

	@Override
	public XMLNode getChildAt(int at) {
		return children.get(at);
	}

	@Override
	public List<XMLNode> getChildren() {
		return Collections.unmodifiableList(children);
	}

	@Override
	public void addChild(XMLNode child) {
		children.add(child);
		child.parent = this;
	}

	@Override
	public XMLNode self() {
		return this;
	}

	public void addProperty(String key, String value) {
		properties.put(key, value);
	}

	public String getProperty(String key) {
		return properties.get(key);
	}

	public Map<String, String> getProperties() {
		return Collections.unmodifiableMap(properties);
	}

	public void dump(PrintStream printStream) {
		printStartTag(printStream, tagName);
		for (XMLNode child : children)
			child.dump(printStream);
		printEndTag(printStream, tagName);
	}

	// FIXME 2020/4/18  wait for me!!!  move to another Class
	private void printStartTag(PrintStream printStream, String tagName) {
		printStream.print("<");
		printStream.print(tagName);
		printProperties(printStream);
		printStream.print(">");
	}

	private void printEndTag(PrintStream printStream, String tagName) {
		printStream.print("</");
		printStream.print(tagName);
		printStream.print(">");
	}

	private void printProperties(PrintStream printStream) {
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			printStream.print(" ");
			printStream.print(entry.getKey());
			printStream.print("=");
			printStream.print("\"");
			printStream.print(entry.getValue());
			printStream.print("\"");
		}
	}

	public void rename(String tagName) {
		this.tagName = tagName;
	}

	public XMLNode getParent() {
		return parent;
	}

	public void removeFromParent() {
		parent.removeChild(this);
	}

	public void removeChild(XMLNode child) {
		children.remove(child);
	}

	public void removeProperty(String propertyName) {
		properties.remove(propertyName);
	}

	@Override
	public boolean hasChild() {
		return children.size() != 0;
	}

	public void forEach(Consumer<XMLNode> consumer) {
		consumer.accept(this);
		for (XMLNode child : children)
			child.forEach(consumer);
	}

	public List<XMLNode> toList() {
		LinkedList<XMLNode> result = new LinkedList<>();
		forEach(result::add);
		return result;
	}
}
