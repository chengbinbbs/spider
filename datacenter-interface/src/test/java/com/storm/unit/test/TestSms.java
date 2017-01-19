package com.storm.unit.test;

import java.util.regex.Pattern;

public class TestSms {
	
	public static void main(String[] args) {
		System.out.println(Pattern.matches("/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/", "510723198709033758"));
	}
}
