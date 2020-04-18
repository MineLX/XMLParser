package main;

public class TagScanner {

	private final IScanner trimmedScanner;

	private final Scanner scanner;

	public TagScanner(String source) {
		scanner = new Scanner(source);
		trimmedScanner = new TrimmedScanner(scanner);
	}

	public XMLNode parseAnNode() {
		XMLNode result = scanAnStartTag();
		parseRecursively(result);
		scanAnEndTag();
		return result;
	}

	private void scanAnEndTag() {
		trimmedScanner.nextChars(2);
		trimmedScanner.nextPage();
		trimmedScanner.nextChar();
	}

	private XMLNode scanAnStartTag() {
		trimmedScanner.nextChar();
		XMLNode result = new XMLNode(trimmedScanner.nextPage());
		scanPropertiesTo(result);
		trimmedScanner.nextChar();
		return result;
	}

	private void scanPropertiesTo(XMLNode node) {
		while (!trimmedScanner.exists(">")) {
			String propertyName = trimmedScanner.nextPage();
			trimmedScanner.nextChar(); // =
			String propertyValue = scanner.til(trimmedScanner.nextChar());
			trimmedScanner.nextChar(); // "
			node.addProperty(propertyName, propertyValue);
		}
	}

	private boolean goesAnStartTag() {
		trimmedScanner.trim();
		if (!trimmedScanner.exists("<"))
			return false;
		return !trimmedScanner.exists("</");
	}

	private void parseRecursively(XMLNode parent) {
		while (goesAnStartTag()) {
			XMLNode child = scanAnStartTag();
			parseRecursively(child);
			scanAnEndTag();
			parent.addChild(child);
		}
	}
}
