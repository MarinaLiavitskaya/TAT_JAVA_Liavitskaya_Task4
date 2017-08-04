package com.epam.liavitskaya.test.server;

import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.liavitskaya.main.controller.server.Server;

public class ServerTest {	
	
	@Test(dataProvider = "user_dataProvider", threadPoolSize = 2, invocationCount = 1, timeOut = 1000, priority = 0)
	public void test_user_Server(String request, String expected) {
		
		LinkedList<String> startServer = (LinkedList<String>) Server.startServer(request);
		Assert.assertEquals(startServer.poll(), expected);		
	}	
	
	@Test(dataProvider = "admin_dataProvider", threadPoolSize = 2, invocationCount = 1, timeOut = 1000, priority = 1)
	public void test_admin_Server(String request, String expected) {
		
		LinkedList<String> startServer = (LinkedList<String>) Server.startServer(request);
		Assert.assertEquals(startServer.poll(), expected);		
	}
	
	@Test(dataProvider = "superAdmin_dataProvider", threadPoolSize = 2, invocationCount = 1, timeOut = 1000, priority = 2)
	public void test_superAdmin_Server(String request, String expected) {
		
		LinkedList<String> startServer = (LinkedList<String>) Server.startServer(request);
		Assert.assertEquals(startServer.poll(), expected);		
	}
	
		
	@DataProvider
	public Object[][] user_dataProvider() {
		
		return new Object[][] { 
				new Object[] { "show_all_available_book",
				"Check available books : [\nBook [title = Lolita, author = Vladimir Nabokov, description = Russia, bookStatus = AVAILABLE], \nBook [title = Great Expectation, author = Charles Dickens, description = England, bookStatus = AVAILABLE], \nBook [title = Brave New Worldn, author = Aldous Huxley, description = England, bookStatus = AVAILABLE], \nBook [title = Norwegian Wood, author = Haruki Murakami, description = 1987, bookStatus = AVAILABLE], \nBook [title = The Hobbit, or There and Back Again, author = J.R.R.Tolkien, description = 1937 UK, bookStatus = AVAILABLE]]" },	
				new Object[] { "order_book 7", "Error during order book procedure" },
				new Object[] { "sign_in user_login777 encryptT@7", "Hi" },
				new Object[] { "order_book 7", "Book is ordered" },
				new Object[] { "add_book Harry_Potter_5 J.K.Rowling England", "Error during add new book procedure" },
				new Object[] { "make_admin_user USER 3", "Error during procedure of change of the status of the admin" },
				new Object[] { "sign_out user_login777", "Goodbye" },
				new Object[] { "order_book 10", "Error during order book procedure" },
		};
	}
	
	@DataProvider
	public Object[][] admin_dataProvider() {
		
		return new Object[][] { 
				new Object[] { "order_book 10", "Error during order book procedure" },
				new Object[] { "sign_in admin_login222 encryptT@2", "Hi" },			
				new Object[] { "order_book 10", "Book is ordered" },
				new Object[] { "review_profile_id 8", "\nUser [userName = Ann, userPassportNo = null, phone = phone888, email = email888, userRole = USER, login = xxx888, userStatus = INACTIVE]" }, 
				new Object[] { "write_off_book 9", "book is written off" },
				new Object[] { "add_book Harry_Potter_2 J.K.Rowling England", "New book is added" },
				new Object[] { "edit_book The_Norwegian_Wood Haruki_Murakami Japan_1987 9", "book is edited" },				
				new Object[] { "registration KIMBERIY MP1100010 PHONE110 EMAIL110 USER xxx110 encryptT10@ ACTIVE", "Welcome" },
				new Object[] { "registration KIMBERIY MP1100010 PHONE110 EMAIL110 USER xXx110 encryptT10@ ACTIVE", "Error during registration" },
				new Object[] { "make_admin_user USER 3", "Error during procedure of change of the status of the admin" },
				new Object[] { "cancel_order 3", "Order is cancelled" },
				new Object[] { "cancel_order 6", "Error during order book procedure" },
				new Object[] { "sign_out admin_login222", "Goodbye" },	
				new Object[] { "order_book 8", "Error during order book procedure" },
		};
	}
	
	@DataProvider
	public Object[][] superAdmin_dataProvider() {
		
		return new Object[][] { 
				new Object[] { "order_book 8", "Error during order book procedure" },
				new Object[] { "sign_in superadmin1991 encryptT@1991", "Hi" },
				new Object[] { "order_book 8", "Book is ordered" },
				new Object[] { "add_book Harry_Potter_3 J.K.Rowling England", "New book is added" },
				new Object[] { "make_admin_user USER 3", "the status of the admin is changed" },
				new Object[] { "sign_out superadmin1991", "Goodbye" },
				new Object[] { "order_book 5", "Error during order book procedure" },
		};
	}

	@BeforeClass
	public void beforeClass() {			
	}

	@AfterClass
	public void afterClass() {
	}

}
