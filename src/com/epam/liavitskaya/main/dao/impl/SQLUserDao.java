package com.epam.liavitskaya.main.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.liavitskaya.main.bean.User;
import com.epam.liavitskaya.main.controller.CurrentUser;
import com.epam.liavitskaya.main.dao.UserDAO;
import com.epam.liavitskaya.main.dao.exception.DAOException;
import com.epam.liavitskaya.main.enumeration.UserRoles;
import com.epam.liavitskaya.main.enumeration.UserStatus;
import com.epam.liavitskaya.main.mysql.ConnectionManager;

public class SQLUserDao implements UserDAO {

	static final String ADD_USER = "INSERT INTO users(name, passport, phone, email, role, login, password, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";	
	static final String SHOW_ALL_USERS = "SELECT user_id, name, passport, phone, email, role, login, password, status FROM users";
	static final String SHOW_USER = "SELECT user_id, name, passport, phone, email, role, login, password, status FROM users WHERE login = ?";
	static final String SHOW_USER_ID = "SELECT user_id, name, passport, phone, email, role, login, password, status FROM users WHERE user_id = ?";
	static final String UPDATE_USER_PROFILE = "UPDATE users SET name = ?, passport = ?, phone = ?, email = ?, login = ?, password = ? WHERE user_id = ?";
	static final String CHECK_USER_STATUS = "SELECT status FROM users WHERE login = ?";
	static final String CHANGE_USER_ROLE = "UPDATE users SET role = ? WHERE user_id = ?";
	static final String CHANGE_USER_STATUS = "UPDATE users SET status = ? WHERE user_id = ?";
	static final String ROW_COUNT = "SELECT COUNT(*) FROM users";
	static final String DELETE_USER = "DELETE FROM users WHERE user_id = ?";

	Connection connection = null;

	public SQLUserDao() {
		connection = ConnectionManager.getManager().getConnection();
	}

	@Override
	public void singIn(String login, String password) throws DAOException {

		PreparedStatement prStmt = null;
		ResultSet rs = null;
		User currentUser = new User();

		try {
			prStmt = connection.prepareStatement(SHOW_USER);
			prStmt.setString(1, login);
			rs = prStmt.executeQuery();

			while (rs.next()) {
				
				int id = rs.getInt("user_id");
				currentUser.setUserId(id);
				String name = rs.getString("name");
				currentUser.setUserName(name);
				String passport = rs.getString("passport");
				currentUser.setPassword(passport);
				String phone = rs.getString("phone");
				currentUser.setPhone(phone);
				String email = rs.getString("email");
				currentUser.setEmail(email);
				String role = rs.getString("role");
				currentUser.setUserRole(role);
				currentUser.setLogin(login);
				currentUser.setPassword(password);
				String status = rs.getString("status");				
				currentUser.setUserStatus(status);
				CurrentUser.setCurrentUser(currentUser);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt, rs);
		}
	}

	@Override
	public void singOut(String login) {
		
		CurrentUser.getCurrentUser().setUserId(0);
		CurrentUser.getCurrentUser().setUserName("");
		CurrentUser.getCurrentUser().setPassportNo("");
		CurrentUser.getCurrentUser().setPhone("");
		CurrentUser.getCurrentUser().setUserRole("UNAUTHORIZED");
		CurrentUser.getCurrentUser().setEmail("");
		CurrentUser.getCurrentUser().setLogin("");
		CurrentUser.getCurrentUser().setPassword("");
		CurrentUser.getCurrentUser().setUserStatus("INACTIVE");
		
	}

	@Override
	public void register(User user) throws DAOException {

		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(ADD_USER);
			prStmt.setString(1, user.getUserName());
			prStmt.setString(2, user.getPassportNo());
			prStmt.setString(3, user.getPhone());
			prStmt.setString(4, user.getEmail());
			prStmt.setString(5, user.getUserRole());
			prStmt.setString(6, user.getLogin());
			prStmt.setString(7, user.getPassword());
			prStmt.setString(8, user.getUserStatus());
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}	

	@Override
	public User getProfile(String login) throws DAOException {

		PreparedStatement prStmt = null;
		ResultSet rs = null;
		User user = new User();

		try {
			prStmt = connection.prepareStatement(SHOW_USER);
			prStmt.setString(1, login);
			rs = prStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("user_id");
				user.setUserId(id);
				String name = rs.getString("name");
				user.setUserName(name);
				String passport = rs.getString("passport");
				user.setPassword(passport);
				String phone = rs.getString("phone");
				user.setPhone(phone);
				String email = rs.getString("email");
				user.setEmail(email);
				String role = rs.getString("role");
				user.setUserRole(role);				
				user.setLogin(login);
				String password = rs.getString("password");
				user.setPassword(password);
				String status = rs.getString("status");
				user.setUserStatus(status);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt, rs);
		}
		return user;
	}
	
	@Override
	public User getProfileById(int id) throws DAOException {

		PreparedStatement prStmt = null;
		ResultSet rs = null;
		User user = new User();

		try {
			prStmt = connection.prepareStatement(SHOW_USER_ID);
			prStmt.setInt(1, id);
			rs = prStmt.executeQuery();

			while (rs.next()) {				
				user.setUserId(id);
				String name = rs.getString("name");
				user.setUserName(name);
				String passport = rs.getString("passport");
				user.setPassword(passport);
				String phone = rs.getString("phone");
				user.setPhone(phone);
				String email = rs.getString("email");
				user.setEmail(email);
				String role = rs.getString("role");
				user.setUserRole(role);	
				String login = rs.getString("login");
				user.setLogin(login);
				String password = rs.getString("password");
				user.setPassword(password);
				String status = rs.getString("status");
				user.setUserStatus(status);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt, rs);
		}
		return user;
	}

	@Override
	public void updateProfile(User user, int id) throws DAOException {

		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(UPDATE_USER_PROFILE);
			prStmt.setString(1, user.getUserName());
			prStmt.setString(2, user.getPassportNo());
			prStmt.setString(3, user.getPhone());
			prStmt.setString(4, user.getEmail());
			prStmt.setString(5, user.getLogin());
			prStmt.setString(6, user.getPassword());
			prStmt.setInt(7, id);
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public List<User> showAllUsers() throws DAOException {

		PreparedStatement prStmt = null;
		ResultSet rs = null;
		User user;
		List<User> userList = new ArrayList<>();

		try {
			prStmt = connection.prepareStatement(SHOW_ALL_USERS);
			rs = prStmt.executeQuery();

			while (rs.next()) {

				user = new User();
				int id = rs.getInt("user_id");
				user.setUserId(id);
				String name = rs.getString("name");
				user.setUserName(name);
				String passport = rs.getString("passport");
				user.setPassword(passport);
				String phone = rs.getString("phone");
				user.setPhone(phone);
				String email = rs.getString("email");
				user.setEmail(email);
				String role = rs.getString("role");
				user.setUserRole(role);
				String login = rs.getString("login");
				user.setLogin(login);
				String password = rs.getString("password");
				user.setPassword(password);
				String status = rs.getString("status");
				user.setUserStatus(status);
				userList.add(user);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt, rs);
		}
		return userList;
	}
	
	@Override
	public UserStatus checkUserStatus(String login) throws DAOException {
		
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		UserStatus userStatus;

		try {
			prStmt = connection.prepareStatement(CHECK_USER_STATUS);
			prStmt.setString(1, login);
			rs = prStmt.executeQuery();
			rs.next();
			userStatus = UserStatus.valueOf(rs.getString(1));
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt, rs);
		}
		return userStatus;
	}

	@Override
	public void changeUserStatus(UserStatus userStatus, int id) throws DAOException {

		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(CHANGE_USER_STATUS);
			prStmt.setString(1, userStatus.name());
			prStmt.setInt(2, id);
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public void changeUserRole(UserRoles userRoles, int id) throws DAOException {

		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(CHANGE_USER_ROLE);
			prStmt.setString(1, userRoles.name());
			prStmt.setInt(2, id);
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public void deleteUser(int userId) throws DAOException {

		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(DELETE_USER);
			prStmt.setInt(1, userId);
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public List<String> showAllLogins() throws DAOException {

		PreparedStatement prStmt = null;
		ResultSet rs = null;
		List<String> loginList = new ArrayList<>();

		try {
			prStmt = connection.prepareStatement(SHOW_ALL_USERS);
			rs = prStmt.executeQuery();

			while (rs.next()) {
				String login = rs.getString("login");
				loginList.add(login);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
		return loginList;
	}

	@Override
	public List<String> showAllPasswords() throws DAOException {

		PreparedStatement prStmt = null;
		ResultSet rs = null;
		List<String> passwordList = new ArrayList<>();

		try {
			prStmt = connection.prepareStatement(SHOW_ALL_USERS);
			rs = prStmt.executeQuery();

			while (rs.next()) {
				String password = rs.getString("password");
				passwordList.add(password);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
		return passwordList;
	}

	@Override
	public int rowCount() throws DAOException {

		PreparedStatement prStmt = null;
		ResultSet rs = null;
		int count;

		try {
			prStmt = connection.prepareStatement(ROW_COUNT);
			rs = prStmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt, rs);
		}
		return count;
	}
	
}
