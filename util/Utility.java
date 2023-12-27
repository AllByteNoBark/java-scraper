package main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utility {
	private static InputStreamReader input = new InputStreamReader(System.in);
	private static BufferedReader buffer = new BufferedReader(input);
	
	public static Object input(String msg, Class<?> x) {
		System.out.print(msg);
		
		String str = null;
		try {
			str = Utility.buffer.readLine();
			return parseType(x, str);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static Object parseType(Class<?> x, String str) {
		if(x == String.class) {
			return str;
		} else if(x == Integer.class) {
			return Integer.parseInt(str);
		} else if(x == Double.class) {
			return Double.parseDouble(str);
		} else {
			throw new IllegalArgumentException("Input error!");
		}
	}
	
	public static void print() {
		System.out.println();
	}
	
	public static void print(boolean out) {
		System.out.println(out);
	}
	
	public static void print(char out) {
		System.out.println(out);
	}
	
	public static void print(int out) {
		System.out.println(out);
	}
	
	public static void print(long out) {
		System.out.println(out);
	}
	
	public static void print(float out) {
		System.out.println(out);
	}
	
	public static void print(double out) {
		System.out.println(out);
	}
	
	public static void print(char[] out) {
		System.out.println(out);
	}
	
	public static void print(String out) {
		System.out.println(out);
	}
	
	public static void print(Object out) {
		System.out.println(out);
	}
}