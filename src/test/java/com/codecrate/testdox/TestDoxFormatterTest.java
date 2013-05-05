package com.codecrate.testdox;

import junit.framework.TestCase;

public class TestDoxFormatterTest extends TestCase {

	public void testPrettyClassNameRemovesTestSuffix() {
		TestDoxFormatter prettifier = new TestDoxFormatter();
		assertEquals("Blah", prettifier.prettifyTestClassName("BlahTest"));
	}

	public void testPrettyClassNameAddsSpacesForCamelCaseCharacters() {
		TestDoxFormatter prettifier = new TestDoxFormatter();
		assertEquals("Foo bar", prettifier.prettifyTestClassName("FooBarTest"));
	}

	public void testPrettyClassNameRemovesPackagePrefix() {
		TestDoxFormatter prettifier = new TestDoxFormatter();
		assertEquals("Foo", prettifier.prettifyTestClassName("com.mysite.FooTest"));
	}

	public void testPrettyMethodNameRemovesTestPrefix() {
		TestDoxFormatter prettifier = new TestDoxFormatter();
		assertEquals("Foo", prettifier.prettifyTestMethodName("testFoo"));
	}
	
	public void testPrettyMethodNameAddsSpacesForCamelCaseCharacters() {
		TestDoxFormatter prettifier = new TestDoxFormatter();
		assertEquals("Foo bar", prettifier.prettifyTestMethodName("testFooBar"));
	}
	public void testPrettyMethodNameKeepsTestPrefixWhenHandlingShouldPrefix() {
		TestDoxFormatter prettifier = new TestDoxFormatter();
		assertEquals("should foo", prettifier.prettifyTestMethodName("shouldFoo"));
	}

	public void testPrettyMethodNameAddsSpacesForCamelCaseCharactersHandlingShouldPrefix() {
		TestDoxFormatter prettifier = new TestDoxFormatter();
		assertEquals("should foo bar", prettifier.prettifyTestMethodName("shouldFooBar"));
	}
}
