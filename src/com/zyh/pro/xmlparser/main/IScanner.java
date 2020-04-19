package com.zyh.pro.xmlparser.main;

import java.util.function.IntPredicate;

public interface IScanner {

	char nextChar();

	void pullBack(int count);

	boolean hasNext();

	String nextAlpha();

	String nextPage();

	boolean pass(String expected);

	boolean isEmpty();

	int hasCount();

	boolean exists(String expected);

	String nextChars(int count);

	void trim();

	default String collect(IntPredicate predicate) {
		StringBuilder result = new StringBuilder();
		char next;
		while (hasNext()) {
			if (!predicate.test(next = nextChar())) {
				pullBack(1);
				return result.toString();
			}
			result.append(next);
		}
		return result.toString();
	}

	default String til(char endChar) {
		return collect(value -> value != endChar);
	}
}
