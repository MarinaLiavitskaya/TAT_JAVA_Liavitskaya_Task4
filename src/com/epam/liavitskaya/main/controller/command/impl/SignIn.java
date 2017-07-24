package com.epam.liavitskaya.main.controller.command.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.main.service.ClientService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;
import com.epam.liavitskaya.main.util.RequestParserUtil;

public class SignIn implements Command {

	final Logger logger = Logger.getLogger(SignIn.class);

	@Override
	public String execute(String request) {

		String login = null;
		String password = null;
		String response = null;

		try {
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			ClientService clientService = serviceProvider.getClientServiceImpl();

			String[] splitRequest = RequestParserUtil.parseRequest(request, 3);
			login = splitRequest[1];
			password = splitRequest[2];

			if (isLoginExist(login, clientService) && isPasswordExist(password, clientService)) {
				clientService.singInService(login, password);
				response = "Hi";
			} else {
				response = "Login failed, please register";
			}
		} catch (ServiceException e) {
			logger.error("Error during login procedure", e);
			response = "Error during login procedure";
		}
		return response;
	}

	public boolean isPasswordExist(String password, ClientService clientService) {

		List<String> fetchAllPasswords = null;
		
		try {
			fetchAllPasswords = clientService.fetchAllPasswordsService();
		} catch (ServiceException e) {
			logger.error("Error during is Password Exist procedure", e);
		}
		return fetchAllPasswords.contains(password);
	}

	public boolean isLoginExist(String login, ClientService clientService) {
		
		List<String> fetchAllLogins = null;
		
		try {
			fetchAllLogins = clientService.fetchAllLoginsService();
		} catch (ServiceException e) {
			logger.error("Error during is Login exist procedure", e);
		}
		return fetchAllLogins.contains(login);
	}
}
