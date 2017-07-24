package com.epam.liavitskaya.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.bean.User;
import com.epam.liavitskaya.main.dao.UserDAO;
import com.epam.liavitskaya.main.dao.exception.DAOException;
import com.epam.liavitskaya.main.dao.factory.DAOFactory;
import com.epam.liavitskaya.main.dao.impl.SQLUserDao;
import com.epam.liavitskaya.main.enumeration.UserRoles;
import com.epam.liavitskaya.main.enumeration.UserStatus;
import com.epam.liavitskaya.main.service.ClientService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;
import com.epam.liavitskaya.main.util.PasswordEncryptorUtil;
import com.epam.liavitskaya.main.util.RequestParserUtil;
import com.epam.liavitskaya.main.util.ValidatorUtil;

public class ClientServiceImpl implements ClientService {

	final static String INCORRECT_SIGNIN_INPUT_MESSAGE = "incorrect sign in input";
	final static String INCORRECT_ID_MESSAGE = "incorrect id";

	final static Logger logger = Logger.getLogger(ClientServiceImpl.class);

	@Override
	public void singInService(String login, String password) throws ServiceException {

		if (login == null || login.isEmpty() || !ValidatorUtil.isLoginValid(login)) {
			throw new ServiceException(INCORRECT_SIGNIN_INPUT_MESSAGE);
		}

		if (password == null || password.isEmpty() || !ValidatorUtil.isPasswordValid(password)) {
			throw new ServiceException(INCORRECT_SIGNIN_INPUT_MESSAGE);
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			if (UserStatus.INACTIVE.equals(userDAO.checkUserStatus(login))) {
				throw new ServiceException("you are blocked contact your administrator");
			}
			userDAO.singIn(login, password);

		} catch (DAOException e) {
			throw new ServiceException(INCORRECT_SIGNIN_INPUT_MESSAGE, e);
		}
	}

	@Override
	public void singOutService(String login) throws ServiceException {

		if (login == null || login.isEmpty() || !ValidatorUtil.isLoginValid(login)) {
			throw new ServiceException("incorrect input");
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			userDAO.singOut(login);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void registrationService(String request) throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 9);
		String login = splitRequest[6];
		String password = splitRequest[7];

		if (login == null || login.isEmpty() || !ValidatorUtil.isLoginValid(login)) {
			throw new ServiceException("invalid login");
		}

		if (password == null || password.isEmpty() || !ValidatorUtil.isPasswordValid(password)) {
			throw new ServiceException("invalid password");
		}

		String encryptPassword = PasswordEncryptorUtil.encryptPassword(splitRequest[7]);

		if (isPasswordExist(encryptPassword) || isLoginExist(login)) {
			throw new ServiceException("registration data already exists");
		}
		User user = new User(splitRequest[1], splitRequest[2], splitRequest[3], splitRequest[4],
				UserRoles.valueOf(splitRequest[5]), splitRequest[6], encryptPassword,
				UserStatus.valueOf(splitRequest[8]));
		try {
			userDAO.register(user);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public User reviewProfileService(String request) throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 2);
		User userProfile = new User();

		try {
			String login = splitRequest[1];
			boolean loginExist = isLoginExist(login);
			if (!loginExist) {
				throw new ServiceException("invalid login");
			}
			userProfile = userDAO.getProfile(login);
		} catch (DAOException e) {
			throw new ServiceException();
		}
		return userProfile;
	}

	@Override
	public User reviewProfileByIdService(String request) throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 2);
		User userProfile = new User();

		try {
			int id = Integer.parseInt(splitRequest[1]);

			int userCount = userDAO.rowCount();
			if (id < 1 || userCount < id) {
				throw new ServiceException(INCORRECT_ID_MESSAGE);
			}

			userProfile = userDAO.getProfileById(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
		return userProfile;
	}

	@Override
	public void editProfileService(String request) throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		try {
			User user = buildEditedUser(request, userDAO);
			userDAO.updateProfile(user, user.getUserId());
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void editStatusService(String request) throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 3);
		UserStatus userStatus = UserStatus.valueOf(splitRequest[1]);

		try {
			int id = Integer.parseInt(splitRequest[2]);
			int userCount = userDAO.rowCount();
			if (id < 1 || userCount < id) {
				throw new ServiceException(INCORRECT_ID_MESSAGE);
			}
			userDAO.changeUserStatus(userStatus, id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void editRoleService(String request) throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 3);
		String role = splitRequest[1];
		UserRoles userRole = UserRoles.valueOf(role);

		try {
			int id = Integer.parseInt(splitRequest[2]);
			int userCount = userDAO.rowCount();
			if (id < 1 || userCount < id) {
				throw new ServiceException(INCORRECT_ID_MESSAGE);
			}
			userDAO.changeUserRole(userRole, id);

		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<User> showAllUsersService() throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		List<User> allUsers;

		try {
			allUsers = userDAO.showAllUsers();
		} catch (DAOException e) {
			throw new ServiceException();
		}
		return allUsers;
	}

	@Override
	public void deleteUserService(String request) throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 2);

		try {
			int id = Integer.parseInt(splitRequest[1]);
			int userCount = userDAO.rowCount();
			if (id < 1 || userCount < id) {
				throw new ServiceException(INCORRECT_ID_MESSAGE);
			}
			userDAO.deleteUser(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	public List<String> fetchAllLoginsService() throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		List<String> fetchAllLogins = new ArrayList<>();

		try {
			fetchAllLogins = userDAO.showAllLogins();
		} catch (DAOException e) {
			throw new ServiceException();
		}
		return fetchAllLogins;
	}

	public List<String> fetchAllPasswordsService() throws ServiceException {

		UserDAO userDAO = new SQLUserDao();
		List<String> fetchAllPasswords = new ArrayList<>();

		try {
			fetchAllPasswords = userDAO.showAllPasswords();
		} catch (DAOException e) {
			throw new ServiceException();
		}
		return fetchAllPasswords;
	}

	private User buildEditedUser(String request, UserDAO userDAO) throws ServiceException, DAOException {

		String[] splitRequest = RequestParserUtil.parseRequest(request, 8);

		int id = Integer.parseInt(splitRequest[1]);
		int userCount = userDAO.rowCount();
		String login = splitRequest[6];
		String password = splitRequest[7];

		if (id < 1 || userCount < id) {
			throw new ServiceException(INCORRECT_ID_MESSAGE);
		}
		if (login == null || login.isEmpty() || !ValidatorUtil.isLoginValid(login)) {
			throw new ServiceException("invalid login");
		}

		if (password == null || password.isEmpty() || !ValidatorUtil.isPasswordValid(password)) {
			throw new ServiceException("invalid password");
		}

		String encryptPassword = PasswordEncryptorUtil.encryptPassword(splitRequest[7]);

		User user = new User(id, splitRequest[2], splitRequest[3], splitRequest[4], splitRequest[5], login,
				encryptPassword);
		return user;
	}

	private static boolean isPasswordExist(String password) {

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		ClientService clientService = serviceProvider.getClientServiceImpl();
		List<String> fetchAllPasswords = new ArrayList<>();

		try {
			fetchAllPasswords = clientService.fetchAllPasswordsService();
		} catch (ServiceException e) {
			logger.debug("Error during is Password Original check", e);
			e.printStackTrace();
		}
		return fetchAllPasswords.contains(password);
	}

	private static boolean isLoginExist(String login) {

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		ClientService clientService = serviceProvider.getClientServiceImpl();
		List<String> fetchAllLogins = new ArrayList<>();

		try {
			fetchAllLogins = clientService.fetchAllLoginsService();
		} catch (ServiceException e) {
			logger.debug("Error during is Login Original check", e);
			e.printStackTrace();
		}
		return fetchAllLogins.contains(login);
	}

}
