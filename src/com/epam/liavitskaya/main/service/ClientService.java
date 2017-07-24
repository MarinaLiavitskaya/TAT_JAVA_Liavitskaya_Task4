package com.epam.liavitskaya.main.service;

import java.util.List;

import com.epam.liavitskaya.main.bean.User;
import com.epam.liavitskaya.main.service.exception.ServiceException;

public interface ClientService {

	void singInService(String login, String password) throws ServiceException;

	void singOutService(String login) throws ServiceException;

	void registrationService(String request) throws ServiceException;

	User reviewProfileService(String requestd) throws ServiceException;
	
	User reviewProfileByIdService(String request) throws ServiceException;

	void editProfileService(String request) throws ServiceException;
	
	void editStatusService(String request) throws ServiceException;
	
	void editRoleService(String request) throws ServiceException;

	List<User> showAllUsersService() throws ServiceException;

	List<String> fetchAllLoginsService() throws ServiceException;

	List<String> fetchAllPasswordsService() throws ServiceException;

	void deleteUserService(String request) throws ServiceException;

}
