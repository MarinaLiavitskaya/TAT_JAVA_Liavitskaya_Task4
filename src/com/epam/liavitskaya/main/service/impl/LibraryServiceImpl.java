package com.epam.liavitskaya.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.liavitskaya.main.bean.Book;
import com.epam.liavitskaya.main.controller.CurrentUser;
import com.epam.liavitskaya.main.dao.BookDAO;
import com.epam.liavitskaya.main.dao.exception.DAOException;
import com.epam.liavitskaya.main.dao.impl.SQLBookDao;
import com.epam.liavitskaya.main.enumeration.BookStatus;
import com.epam.liavitskaya.main.service.LibraryService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.util.RequestParserUtil;

public class LibraryServiceImpl implements LibraryService {

	@Override
	public void addNewBookService(String request) throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 4);
		Book book = new Book(splitRequest[1], splitRequest[2], splitRequest[3], BookStatus.AVAILABLE);

		try {
			bookDAO.addBook(book);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void editBookService(String request) throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 5);
		int id = Integer.parseInt(splitRequest[4]);
		Book book = new Book(id, splitRequest[1], splitRequest[2], splitRequest[3]);

		try {
			int bookCount = bookDAO.rowCount();
			if (id < 1 || bookCount < id) {
				throw new ServiceException("incorrect id");
			}
			bookDAO.editBook(book);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void editBookDescriptionService(String request) throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 3);
		String description = splitRequest[1];

		try {
			int id = Integer.parseInt(splitRequest[2]);
			int bookCount = bookDAO.rowCount();
			if (id < 1 || bookCount < id) {
				throw new ServiceException("incorrect id");
			}
			bookDAO.editBookDescription(description, id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Book> bookFondReviewService() throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		List<Book> bookFondReview = new ArrayList<>();

		try {
			bookFondReview = bookDAO.bookFondReview();
		} catch (DAOException e) {
			throw new ServiceException();
		}
		return bookFondReview;
	}
	
	@Override
	public List<Book> availableBookReviewService() throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		List<Book> bookFondReview = new ArrayList<>();

		try {
			bookFondReview = bookDAO.availableBookReview();
		} catch (DAOException e) {
			throw new ServiceException();
		}
		return bookFondReview;
	}

	@Override
	public void orderBookService(String request) throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 2);

		try {
			int bookId = Integer.parseInt(splitRequest[1]);
			int bookCount = bookDAO.rowCount();
			if (bookId < 1 || bookCount < bookId) {
				throw new ServiceException("incorrect id");
			}
			bookDAO.changeBookStatus(BookStatus.ORDERED, bookId);
			bookDAO.appointBookTo(CurrentUser.getCurrentUser().getUserId(), bookId);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void cancelOrderService(String request) throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 2);

		try {
			int bookId = Integer.parseInt(splitRequest[1]);
			int bookCount = bookDAO.rowCount();
			if (bookId < 1 || bookCount < bookId) {
				throw new ServiceException("incorrect id");
			}
			String checkBookStatus = bookDAO.checkBookStatus(bookId);
			if (checkBookStatus.equals("ON_HAND " + CurrentUser.getCurrentUser().getUserId())) {
				throw new ServiceException("the book is yours");
			}
			bookDAO.changeBookStatus(BookStatus.AVAILABLE, bookId);
			bookDAO.removeAppoint(bookId);
		} catch (DAOException e) {			
			throw new ServiceException("cancel fail");
		}

	}

	@Override
	public void writeOffBookService(String request) throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 2);

		try {
			int id = Integer.parseInt(splitRequest[1]);
			int bookCount = bookDAO.rowCount();
			if (id < 1 || bookCount < id) {
				throw new ServiceException("incorrect id");
			}
			bookDAO.changeBookStatus(BookStatus.WRITTEN_OFF, id);
		} catch (DAOException e) {			
			throw new ServiceException();
		}
	}

	@Override
	public void changeBookStatusService(String request) throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 3);

		try {
			int id = Integer.parseInt(splitRequest[1]);
			BookStatus bookStatus = BookStatus.valueOf(splitRequest[2]);
			int bookCount = bookDAO.rowCount();
			if (id < 1 || bookCount < id) {
				throw new ServiceException("incorrect id");
			}
			bookDAO.changeBookStatus(bookStatus, id);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public void deleteBookService(String request) throws ServiceException {

		BookDAO bookDAO = new SQLBookDao();
		String[] splitRequest = RequestParserUtil.parseRequest(request, 2);
		int id = Integer.parseInt(splitRequest[1]);

		try {
			int bookCount = bookDAO.rowCount();
			if (id < 1 || bookCount < id) {
				throw new ServiceException("incorrect id");
			}
			bookDAO.deleteBook(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

}
