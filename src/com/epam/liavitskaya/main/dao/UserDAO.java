package com.epam.liavitskaya.main.dao;

import java.util.List;

import com.epam.liavitskaya.main.bean.User;
import com.epam.liavitskaya.main.dao.exception.DAOException;
import com.epam.liavitskaya.main.enumeration.UserRoles;
import com.epam.liavitskaya.main.enumeration.UserStatus;

public interface UserDAO {

	void singIn(String login, String password) throws DAOException;

	void singOut(String login) throws DAOException;

	void register(User user) throws DAOException;

	User getProfile(String login) throws DAOException;
	
	User getProfileById(int id) throws DAOException;

	void updateProfile(User user, int id) throws DAOException;

	List<User> showAllUsers() throws DAOException;

	List<String> showAllLogins() throws DAOException;

	List<String> showAllPasswords() throws DAOException;
	
	UserStatus checkUserStatus(String login) throws DAOException;

	void changeUserStatus(UserStatus userStatus, int id) throws DAOException;

	void changeUserRole(UserRoles userRoles, int id) throws DAOException;

	int rowCount() throws DAOException;

	void deleteUser(int id) throws DAOException;
}
