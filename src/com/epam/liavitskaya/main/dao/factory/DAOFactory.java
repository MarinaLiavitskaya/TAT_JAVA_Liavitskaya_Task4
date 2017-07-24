package com.epam.liavitskaya.main.dao.factory;

import com.epam.liavitskaya.main.dao.BookDAO;
import com.epam.liavitskaya.main.dao.UserDAO;
import com.epam.liavitskaya.main.dao.impl.SQLBookDao;
import com.epam.liavitskaya.main.dao.impl.SQLUserDao;

public final class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private final BookDAO sqlBookImpl = new SQLBookDao();
	private final UserDAO sqlUserImpl = new SQLUserDao();

	private DAOFactory() {
		super();
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public BookDAO getBookDAO() {
		return sqlBookImpl;
	}

	public UserDAO getUserDAO() {
		return sqlUserImpl;
	}

}
