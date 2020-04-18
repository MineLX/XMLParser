package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Files {
	public static String toString(String path) {
		return new BufferedReader(new InputStreamReader(Files.class.getResourceAsStream(path)))
				.lines().reduce("", (a, b) -> a + b + getCRLF())
				;
	}

	private static String getCRLF() {
		return "\r\n";
	}
}
