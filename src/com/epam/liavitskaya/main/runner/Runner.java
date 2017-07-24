package com.epam.liavitskaya.main.runner;

import java.util.List;

import com.epam.liavitskaya.main.controller.Controller;
import com.epam.liavitskaya.main.server.Server;

public class Runner {

	public static void main(String[] args) {

		Controller controller = Controller.getInstance();
		controller.executeTask("sign_in superadmin1991 encryptT@1991");		
		
		Server.startServer("order_book 8");
		Server.startServer("review_profile_id 8");
		Server.startServer("edit_profile 3 molly mp2302323 25+###59 email333 logixqrrn63 paes&wtswW43");	
		Server.startServer("edit_book the_norwegian_wood haruki_murakami japan_1987 9");
		// Server.startServer("show_all_available_books");
		Server.startServer("add_book harry_potter j.k.rowling england");
		Server.startServer("write_off_book 9");
		Server.startServer("make_admin_user user 1");
		List<String> startserver2 = Server.startServer("write_off_book 8");
		System.out.println("###			startserver2  " + startserver2);
		// Server.startServer("show_all_available_books");
		Server.startServer("add_book harry_potter_2 j.k.rowling england");
		// Server.startServer("show_all_available_books");
		Server.startServer("add_book harry_potter_3 j.k.rowling england");		
		List<String> startserver3 = Server.startServer("add_book harry_potter_5 j.k.rowling england");
		System.out.println("######  	startserver3  " + startserver3);

	}
}
