package com.zyh.pro.xmlparser.test;

import com.zyh.pro.xmlparser.main.Files;
import com.zyh.pro.xmlparser.main.XMLNode;
import com.zyh.pro.xmlparser.main.XMLParser;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class XMLParserTest {

	@Test
	public void combination_1_test() {
		XMLNode classNode = parseToNode(Files.toString("/com/zyh/pro/xmlparser/test/combination_1_test.test"));
		assertThat(classNode.getTag(), is("class"));
		assertThat(classNode.getProperty("id"), is("5"));
		assertThat(classNode.getChildren().size(), is(2));

		XMLNode zyhNode = classNode.getChildAt(0);
		assertThat(zyhNode.getTag(), is("student"));
		assertThat(zyhNode.getProperty("id"), is("0"));
		assertThat(zyhNode.getProperty("name"), is("zyh"));
		assertThat(zyhNode.getChildren().size(), is(1));

		XMLNode zyhHabit1 = zyhNode.getChildAt(0);
		assertThat(zyhHabit1.getTag(), is("habit"));
		assertThat(zyhHabit1.getProperty("name"), is("singleton addiction"));

		XMLNode lhrNode = classNode.getChildAt(1);
		assertThat(lhrNode.getTag(), is("student"));
		assertThat(lhrNode.getProperty("id"), is("1"));
		assertThat(lhrNode.getProperty("name"), is("lhr"));
		assertThat(lhrNode.getChildren().size(), is(2));

		XMLNode lhrHabit1 = lhrNode.getChildAt(0);
		assertThat(lhrHabit1.getTag(), is("habit"));
		assertThat(lhrHabit1.getProperty("name"), is("template"));

		XMLNode lhrHabit2 = lhrNode.getChildAt(1);
		assertThat(lhrHabit2.getTag(), is("habit"));
		assertThat(lhrHabit2.getProperty("name"), is("command"));
	}

	@Test
	public void sibling_nodes_test() {
		XMLNode node = parseToNode("<a> <a1></a1> <a2></a2> </a>");
		assertThat(node.getTag(), is("a"));
		assertThat(node.getChildren().size(), is(2));

		assertThat(node.getChildAt(0).getTag(), is("a1"));
		assertThat(node.getChildAt(1).getTag(), is("a2"));
	}

	@Test
	public void node_with_properties_test() {
		XMLNode node = parseToNode("<student id = \"0\"></student>");
		assertThat(node.getProperty("id"), is("0"));
	}

	@Test
	public void spaces_and_tabs_test() {
		XMLNode node = parseToNode("\n < a  > \n <b\n> \t  </b\t>  \t\n  </a>   \n\t");
		assertThat(node.getTag(), is("a"));
		assertThat(node.getChildAt(0).getTag(), is("b"));
	}

	@Test
	public void multi_nodes_test() {
		XMLNode node = parseToNode("<back><inner><inner1><inner2></inner2></inner1></inner></back>");
		assertThat(node.getTag(), is("back"));
		assertThat(node.getChildAt(0).getTag(), is("inner"));
		assertThat(node.getChildAt(0).getChildAt(0).getTag(), is("inner1"));
		assertThat(node.getChildAt(0).getChildAt(0).getChildAt(0).getTag(), is("inner2"));
	}

	private XMLNode parseToNode(String source) {
		return new XMLParser().parse(source);
	}

}
