package main;

public class XMLParser {
	public XMLNode parse(String source) {
		return new TagScanner(source).parseAnNode();
	}
}
