package com.epam.liavitskaya.main.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.controller.CurrentUser;
import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.main.enumeration.UserRoles;
import com.epam.liavitskaya.main.service.LibraryService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;

public class AddBook implements Command {

	final Logger logger = Logger.getLogger(AddBook.class);

	@Override
	public String execute(String request) {

		String response = null;

		try {
			if (UserRoles.UNAUTHORIZED.name().equals(CurrentUser.getCurrentUser().getUserRole())) {
				throw new ServiceException("please login");
			}
			if (UserRoles.USER.name().equals(CurrentUser.getCurrentUser().getUserRole())) {
				throw new ServiceException("you have no permission for this operation");
			}
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			LibraryService libraryService = serviceProvider.getLibraryServiceImpl();
			libraryService.addNewBookService(request);
			response = "New book is added";

		} catch (ServiceException e) {
			logger.error("Error during add new book procedure", e);
			response = "Error during add new book procedure";
		}
		return response;
	}

}
