package test;

import main.XMLNode;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XmlDumpTest {
	@Test
	public void dump_with_properties() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		XMLNode node = new XMLNode("root");
		node.addProperty("id", "0");
		XMLNode child = new XMLNode("child");
		child.addProperty("id", "1");
		child.addProperty("name", "gentle");
		node.addChild(child);
		node.dump(new PrintStream(outputStream));
		assertThat(new String(outputStream.toByteArray()), is("<root id=\"0\"><child name=\"gentle\" id=\"1\"></child></root>"));
	}

	@Test
	public void simple_dump() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		XMLNode node = new XMLNode("root");
		node.addChild(new XMLNode("child"));
		node.addChild(new XMLNode("child"));

		node.dump(new PrintStream(outputStream));
		String actual = new String(outputStream.toByteArray());
		assertThat(actual, is("<root><child></child><child></child></root>"));
	}
}
