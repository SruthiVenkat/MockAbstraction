package br.com.caelum.vraptor.util.test;

public class Driver {
	public void runall() {
		br.com.caelum.vraptor.util.test.MockValidatorTest class1 = new br.com.caelum.vraptor.util.test.MockValidatorTest();
		class1.setUp();
		class1.containsMessageShouldReturnFalseWhenExpectedMessageDontExists();
		class1.containsMessageShouldReturnTrueWhenExpectedMessageExists();
		class1.containsMessageShouldReturnTrueWhenExpectedMessageWithParamsExists();
		br.com.caelum.vraptor.util.test.MockSerializationResultTest class2 = new br.com.caelum.vraptor.util.test.MockSerializationResultTest();
		try {
			class2.setUp();
		}
		catch (java.lang.Exception err1) {
			err1.printStackTrace();
		}
		try {
			class2.shouldReturnStringWithObjectSerialized();
		}
		catch (java.lang.Exception err2) {
			err2.printStackTrace();
		}
		br.com.caelum.vraptor.util.test.MockHttpServletResponseTest class3 = new br.com.caelum.vraptor.util.test.MockHttpServletResponseTest();
		try {
			class3.setUp();
		}
		catch (java.lang.Exception err3) {
			err3.printStackTrace();
		}
		try {
			class3.shouldBeAbleToReturnContentIntoWriteAsString();
		}
		catch (java.io.IOException err4) {
			err4.printStackTrace();
		}
		class3.shouldBeAbleToReturnResponseStatusCode();
		br.com.caelum.vraptor.util.test.MockResultTest class4 = new br.com.caelum.vraptor.util.test.MockResultTest();
		try {
			class4.setUp();
		}
		catch (java.lang.Exception err5) {
			err5.printStackTrace();
		}
		try {
			class4.shouldNotThrowNullPointersOnLogicResult();
		}
		catch (java.lang.Exception err6) {
			err6.printStackTrace();
		}
		try {
			class4.shouldNotThrowNullPointersOnPageResult();
		}
		catch (java.lang.Exception err7) {
			err7.printStackTrace();
		}
		try {
			class4.shouldNotThrowNullPointersOnHttpResult();
		}
		catch (java.lang.Exception err8) {
			err8.printStackTrace();
		}
		try {
			class4.shouldNotThrowNullPointersOnJsonResult();
		}
		catch (java.lang.Exception err9) {
			err9.printStackTrace();
		}
		try {
			class4.shouldNotThrowNullPointersOnXmlResult();
		}
		catch (java.lang.Exception err10) {
			err10.printStackTrace();
		}
		try {
			class4.shouldNotThrowNullPointersOnRepresentationResult();
		}
		catch (java.lang.Exception err11) {
			err11.printStackTrace();
		}
		try {
			class4.shouldNotThrowNullPointersOnStatusResult();
		}
		catch (java.lang.Exception err12) {
			err12.printStackTrace();
		}
		br.com.caelum.vraptor.util.test.MockHttpResultTest class5 = new br.com.caelum.vraptor.util.test.MockHttpResultTest();
		try {
			class5.setUp();
		}
		catch (java.lang.Exception err13) {
			err13.printStackTrace();
		}
		try {
			class5.test();
		}
		catch (java.lang.Exception err14) {
			err14.printStackTrace();
		}
	}
}