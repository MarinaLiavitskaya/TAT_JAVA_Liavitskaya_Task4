package com.epam.liavitskaya.test.server;

import org.testng.annotations.Test;

import com.epam.liavitskaya.main.controller.Controller;
import com.epam.liavitskaya.test.testdata.converter.Converter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class XMLDataProviderTest {

	static final String XML_PATH_BOOK = "resources/test_book.xml";
	static final String XML_PATH_USER = "resources/test_user.xml";

	Controller controller;

	List<String> requestArgsBook;
	List<String> requestArgsUser;
	
	@Test(dataProvider = "dp_add_book")
	public void test_ADD_BOOK(Integer n, String expected) {
		
		requestArgsBook = Converter.convertToRequestArgs(XML_PATH_BOOK);
		String request = "add_book " + requestArgsBook.get(n);		
		Assert.assertEquals(controller.executeTask(request), expected);
	}
	
	@Test(dataProvider = "dp_registration")
	public void test_Registration(Integer n, String expected) {
		
		requestArgsUser = Converter.convertToRequestArgs(XML_PATH_USER);
		String request = "registration " + requestArgsUser.get(n);		
		Assert.assertEquals(controller.executeTask(request), expected);
	}

	@BeforeMethod
	public void beforeMethod() {			
	}

	@AfterMethod
	public void afterMethod() {
	}

	@DataProvider
	public Object[][] dp_add_book() {
		return new Object[][] { 			
			
			new Object[] { 0, "New book is added" }, 
			new Object[] { 1, "New book is added" },
			new Object[] { 2, "New book is added" }, 
			};
	}	
	
	@DataProvider
	public Object[][] dp_registration() {
		return new Object[][] { 			
			
			new Object[] { 0, "Welcome" }, 
			new Object[] { 1, "Welcome" },
			new Object[] { 2, "Welcome" }, 
			};
	}

	@BeforeClass
	public void beforeClass() {

		controller = Controller.getInstance();			
		controller.executeTask("sign_in superadmin1991 encryptT@1991");	

	}

	@AfterClass
	public void afterClass() {
	}

}
