package com.zyh.pro.xmlparser.main;

public class TrimmedScanner implements IScanner {

	private final IScanner decorated;

	public TrimmedScanner(IScanner decorated) {
		this.decorated = decorated;
	}

	@Override
	public char nextChar() {
		decorated.trim();
		return decorated.nextChar();
	}

	@Override
	public String nextAlpha() {
		decorated.trim();
		return decorated.nextAlpha();
	}

	@Override
	public String nextPage() {
		decorated.trim();
		return decorated.nextPage();
	}

	@Override
	public String nextChars(int count) {
		decorated.trim();
		return decorated.nextChars(count);
	}

	@Override
	public boolean pass(String expected) {
		decorated.trim();
		return decorated.pass(expected);
	}

	@Override
	public boolean exists(String expected) {
		decorated.trim();
		return decorated.exists(expected);
	}

	@Override
	public void pullBack(int count) {
		decorated.pullBack(count);
	}

	@Override
	public boolean hasNext() {
		return decorated.hasNext();
	}

	@Override
	public boolean isEmpty() {
		return decorated.isEmpty();
	}

	@Override
	public int hasCount() {
		return decorated.hasCount();
	}

	@Override
	public void trim() {
		decorated.trim();
	}

	@Override
	public String toString() {
		return decorated.toString();
	}
}
