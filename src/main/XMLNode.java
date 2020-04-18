package main;

import java.io.PrintStream;
import java.util.*;

public class XMLNode {

	private final List<XMLNode> children;

	private final Map<String, String> properties;

	private final String tagName;

	public XMLNode(String tagName) {
		this.tagName = tagName;

		children = new ArrayList<>();
		properties = new HashMap<>();
	}

	@Override
	public String toString() {
		return "XMLNode <" + tagName + ">";
	}

	public String getTag() {
		return tagName;
	}

	public XMLNode getChildAt(int at) {
		return children.get(at);
	}

	public List<XMLNode> getChildren() {
		return Collections.unmodifiableList(children);
	}

	public void addChild(XMLNode child) {
		children.add(child);
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
}
