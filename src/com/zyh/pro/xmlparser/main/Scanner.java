package com.zyh.pro.xmlparser.main;

import java.util.Arrays;

public class Scanner implements IScanner {

	private final char[] source;

	private int index;

	public Scanner(String source) {
		this.source = source.toCharArray();
	}

	@Override
	public char nextChar() {
		return source[index++];
	}

	@Override
	public void pullBack(int count) {
		index -= count;
	}

	@Override
	public boolean hasNext() {
		return index != source.length;
	}

	@Override
	public String nextAlpha() {
		return collect(Character::isAlphabetic);
	}

	@Override
	public String nextPage() {
		return collect(value -> Character.isAlphabetic(value) || Character.isDigit(value));
	}

	@Override
	public boolean pass(String expected) {
		boolean result = exists(expected);
		if (result) nextChars(expected.length());
		return result;
	}

	@Override
	public boolean isEmpty() {
		return index == source.length;
	}

	@Override
	public String toString() {
		return new String(source).substring(index);
	}

	@Override
	public int hasCount() {
		return source.length - index;
	}

	@Override
	public boolean exists(String expected) {
		if (isEmpty() || expected.length() > hasCount())
			return false;

		char[] chars = Arrays.copyOfRange(source, index,
				index + expected.length());
		return new String(chars).equals(expected);
	}

	@Override
	public String nextChars(int count) {
		char[] range = Arrays.copyOfRange(source, index, index += count);
		return new String(range);
	}

	@Override
	public void trim() {
		while (Character.isWhitespace(nextChar()));
		pullBack(1);
	}
}
