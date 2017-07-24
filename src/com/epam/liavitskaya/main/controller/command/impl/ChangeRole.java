package com.epam.liavitskaya.main.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.controller.CurrentUser;
import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.main.enumeration.UserRoles;
import com.epam.liavitskaya.main.service.ClientService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;

public class ChangeRole implements Command {

	final Logger logger = Logger.getLogger(ChangeRole.class);

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
			ServiceProvider provider = ServiceProvider.getInstance();
			ClientService clientServiceImpl = provider.getClientServiceImpl();
			clientServiceImpl.editRoleService(request);
			response = "The role of the account is changed";

		} catch (ServiceException e) {
			logger.error("Error during procedure of change of the account role", e);
			response = "Error during procedure of change of the account role";
		}
		return response;
	}

}
