package com.epam.liavitskaya.main.controller.command.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.bean.Book;
import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.main.service.LibraryService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;

public class ShowAllAvailableBook implements Command {

	final Logger logger = Logger.getLogger(ShowAllAvailableBook.class);

	@Override
	public String execute(String request) {

		String response = null;

		try {
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			LibraryService libraryService = serviceProvider.getLibraryServiceImpl();
			List<Book> availableBookReview = libraryService.availableBookReviewService();
			response = "Check available books : " + availableBookReview;

		} catch (ServiceException e) {
			logger.error("Error during procedure to show all books", e);
			response = "Error during procedure to show all books";
		}
		return response;
	}

}
