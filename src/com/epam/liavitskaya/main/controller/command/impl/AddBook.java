package com.epam.liavitskaya.main.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.bean.enumeration.UserRoles;
import com.epam.liavitskaya.main.controller.CurrentUser;
import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.main.service.LibraryService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;

public class AddBook implements Command {
	
	private static final String ERROR_MESSAGE_UNAUTHORIZED = "please login";
	private static final String ERROR_MESSAGE_NO_PERMISSION = "you have no permission for this operation";	
	private static final String SUCCESSFUL_RESPONSE = "New book is added";
	private static final String FAIL_RESPONSE = "Error during add new book procedure";

	final Logger logger = Logger.getLogger(AddBook.class);

	@Override
	public String execute(String request) {

		String response = null;

		try {
			if (UserRoles.UNAUTHORIZED.name().equals(CurrentUser.getCurrentUser().getUserRole())) {
				throw new ServiceException(ERROR_MESSAGE_UNAUTHORIZED);
			}
			if (UserRoles.USER.name().equals(CurrentUser.getCurrentUser().getUserRole())) {
				throw new ServiceException(ERROR_MESSAGE_NO_PERMISSION);
			}
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			LibraryService libraryService = serviceProvider.getLibraryServiceImpl();
			libraryService.addNewBookService(request);
			response = SUCCESSFUL_RESPONSE;

		} catch (ServiceException e) {
			logger.error(FAIL_RESPONSE, e);
			response = FAIL_RESPONSE;
		}
		return response;
	}

}
