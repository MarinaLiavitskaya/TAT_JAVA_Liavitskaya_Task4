package com.epam.liavitskaya.main.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.main.service.ClientService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;

public class Registration implements Command {

	final Logger logger = Logger.getLogger(Registration.class);

	@Override
	public String execute(String request) {

		String response = null;
		
		try {
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			ClientService clientService = serviceProvider.getClientServiceImpl();
			clientService.registrationService(request);
			response = "Welcome";
			
		} catch (ServiceException e) {
			logger.debug("Error during registration", e);
			response = "Error during registration";
		}
		return response;
	}

}
