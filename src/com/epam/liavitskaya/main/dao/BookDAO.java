package com.epam.liavitskaya.main.dao;

import java.util.List;

import com.epam.liavitskaya.main.bean.Book;
import com.epam.liavitskaya.main.bean.enumeration.BookStatus;
import com.epam.liavitskaya.main.dao.exception.DAOException;

public interface BookDAO {

	void addBook(Book book) throws DAOException;

	void editBook(Book book) throws DAOException;

	void editBookDescription(String description, int id) throws DAOException;

	List<Book> bookFondReview() throws DAOException;
	
	List<Book> availableBookReview() throws DAOException;

	void changeBookStatus(BookStatus bookStatus, int bookId) throws DAOException;

	void appointBookTo(int userId, int bookId) throws DAOException;
	
	void removeAppoint(int bookId) throws DAOException;

	String checkBookStatus(int id) throws DAOException;

	int rowCount() throws DAOException;

	void deleteBook(int bookId) throws DAOException;
}
