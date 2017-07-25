package com.epam.liavitskaya.test.xmldata;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.liavitskaya.main.controller.Controller;
import com.epam.liavitskaya.main.server.Server;
import com.epam.liavitskaya.test.xmldata.converter.Converter;

public class XMLDataTest {

	static final String XML_PATH_BOOK = "resources/test_book.xml";
	static final String XML_PATH_USER = "resources/test_user.xml";

	Controller controller;

	List<String> requestArgsBook;
	List<String> requestArgsUser;
	
	@Test(dataProvider = "add_book_dataProvider")
	public void test_ADD_BOOK(Integer index, String expected) {
		
		requestArgsBook = Converter.convertXMLToRequestArgs(XML_PATH_BOOK);
		String request = "add_book " + requestArgsBook.get(index);
		Assert.assertEquals(controller.executeTask(request), expected);
	}
	
	@Test(dataProvider = "registration_dataProvider")
	public void test_Registration(Integer index, String expected) {
		
		requestArgsUser = Converter.convertXMLToRequestArgs(XML_PATH_USER);
		String request = "registration " + requestArgsUser.get(index);		
		Assert.assertEquals(controller.executeTask(request), expected);
	}	

	@DataProvider
	public Object[][] add_book_dataProvider() {
		return new Object[][] { 			
			
			new Object[] { 0, "New book is added" }, 
			new Object[] { 1, "New book is added" },
			new Object[] { 2, "New book is added" }, 
			new Object[] { 3, "New book is added" },
			new Object[] { 4, "Error during add new book procedure" },
			};
	}	
	
	@DataProvider
	public Object[][] registration_dataProvider() {
		return new Object[][] { 			
			
			new Object[] { 0, "Welcome" }, 
			new Object[] { 1, "Welcome" },
			new Object[] { 2, "Welcome" }, 
			new Object[] { 2, "Error during registration" }, 
			};
	}

	@BeforeClass
	public void beforeClass() {

		controller = Controller.getInstance();			
		Server.startServer("sign_in superadmin1991 encryptT@1991");	

	}

	@AfterClass
	public void afterClass() {
	}

}
