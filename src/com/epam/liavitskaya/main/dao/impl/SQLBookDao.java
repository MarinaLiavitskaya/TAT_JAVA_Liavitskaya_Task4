package com.epam.liavitskaya.main.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.liavitskaya.main.bean.Book;
import com.epam.liavitskaya.main.bean.enumeration.BookStatus;
import com.epam.liavitskaya.main.dao.BookDAO;
import com.epam.liavitskaya.main.dao.exception.DAOException;
import com.epam.liavitskaya.main.dao.mysql.ConnectionManager;

public class SQLBookDao implements BookDAO {

	static final String ADD_BOOK = "INSERT INTO books(title, author, description, status) VALUES(?, ?, ?, ?)";
	static final String SHOW_ALL_BOOKS = "SELECT book_id, title, author, description, status, user_id FROM books";
	static final String SHOW_ALL_AVAILABLE_BOOKS = "SELECT book_id, title, author, description, status, user_id FROM books WHERE status = 'available'";
	static final String EDIT_BOOK = "UPDATE books SET title = ?, author = ?, description = ? WHERE book_id = ?";
	static final String EDIT_BOOK_DESCRIPTION = "UPDATE books SET description = ? WHERE book_id = ?";
	static final String CHANGE_BOOK_STATUS = "UPDATE books SET status = ? WHERE book_id = ?";
	static final String CHANGE_BOOK_APPOINTMENT = "UPDATE books SET user_id = ? WHERE book_id = ?";
	static final String SHOW_BOOK_STATUS = "SELECT status, user_id FROM books WHERE book_id = ?";	
	static final String ROW_COUNT = "SELECT COUNT(*) FROM books";
	static final String DELETE_BOOK = "DELETE FROM books WHERE book_id = ?";	
	static final String BOOK_ID = "book_id";
	static final String BOOK_TITLE =  "title";
	static final String BOOK_AUTHOR = "author";
	static final String BOOK_DESCRIPTION = "description";	
	static final String USER_ID = "user_id";
	static final String STATUS = "status";	

//	public SQLBookDao() {
//		connection = ConnectionManager.getManager().getConnection();
//	}

	@Override
	public void addBook(Book book) throws DAOException {

		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(ADD_BOOK);
			prStmt.setString(1, book.getTitle());
			prStmt.setString(2, book.getAuthor());
			prStmt.setString(3, book.getDescription());
			prStmt.setString(4, book.getBookStatus());
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public void editBook(Book book) throws DAOException {

		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(EDIT_BOOK);
			prStmt.setString(1, book.getTitle());
			prStmt.setString(2, book.getAuthor());
			prStmt.setString(3, book.getDescription());
			prStmt.setInt(4, book.getBookId());
			prStmt.executeUpdate();
			prStmt.close();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public void editBookDescription(String description, int id) throws DAOException {

		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(EDIT_BOOK_DESCRIPTION);
			prStmt.setString(1, description);
			prStmt.setInt(2, id);
			prStmt.executeUpdate();
			prStmt.close();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public List<Book> bookFondReview() throws DAOException {

		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		Book book = null;

		List<Book> bookList = new ArrayList<>();

		try {
			prStmt = connection.prepareStatement(SHOW_ALL_BOOKS);
			rs = prStmt.executeQuery();

			while (rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt(BOOK_ID));
				book.setTitle(rs.getString(BOOK_TITLE));
				book.setAuthor(rs.getString(BOOK_AUTHOR));
				book.setDescription(rs.getString(BOOK_DESCRIPTION));
				book.setBookStatus(rs.getString(STATUS));
				bookList.add(book);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt, rs);
		}
		return bookList;
	}
	
	@Override
	public List<Book> availableBookReview() throws DAOException {
		
		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		Book book = null;

		List<Book> availableBookList = new ArrayList<>();

		try {
			prStmt = connection.prepareStatement(SHOW_ALL_AVAILABLE_BOOKS);
			rs = prStmt.executeQuery();

			while (rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt(BOOK_ID));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setDescription(rs.getString("description"));
				book.setBookStatus(rs.getString(STATUS));
				availableBookList.add(book);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt, rs);
		}
		return availableBookList;
	}

	@Override
	public void changeBookStatus(BookStatus bookStatus, int id) throws DAOException {

		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(CHANGE_BOOK_STATUS);
			prStmt.setString(1, bookStatus.name());
			prStmt.setInt(2, id);
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public void appointBookTo(int userId, int bookId) throws DAOException {

		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(CHANGE_BOOK_APPOINTMENT);
			prStmt.setInt(1, userId);
			prStmt.setInt(2, bookId);
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}
	
	@Override
	public void removeAppoint(int bookId) throws DAOException {
		
		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(CHANGE_BOOK_APPOINTMENT);
			prStmt.setInt(1, 0);
			prStmt.setInt(2, bookId);
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public void deleteBook(int id) throws DAOException {

		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;

		try {
			prStmt = connection.prepareStatement(DELETE_BOOK);
			prStmt.setInt(1, id);
			prStmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt);
		}
	}

	@Override
	public int rowCount() throws DAOException {

		Connection connection = ConnectionManager.getManager().getConnection();
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

	@Override
	public String checkBookStatus(int id) throws DAOException {

		Connection connection = ConnectionManager.getManager().getConnection();
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		BookStatus status;
		int userId;

		try {
			prStmt = connection.prepareStatement(SHOW_BOOK_STATUS);
			prStmt.setInt(1, id);
			rs = prStmt.executeQuery();
			rs.next();
			status = BookStatus.valueOf(rs.getString(STATUS));
			userId = rs.getInt(USER_ID);			
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionManager.getManager().closeDbResources(prStmt, rs);
		}
		return status.name() + " " + userId;
	}	

}
