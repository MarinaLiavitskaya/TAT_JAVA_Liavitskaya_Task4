package com.epam.liavitskaya.main.service;

import java.util.List;

import com.epam.liavitskaya.main.bean.Book;
import com.epam.liavitskaya.main.service.exception.ServiceException;

public interface LibraryService {

	void addNewBookService(String request) throws ServiceException;	

	List<Book> bookFondReviewService() throws ServiceException;
	
	List<Book> availableBookReviewService() throws ServiceException;
	
	void editBookService(String request) throws ServiceException;
	
	void editBookDescriptionService(String request) throws ServiceException;	

	void orderBookService(String request) throws ServiceException;
	
	void cancelOrderService(String request) throws ServiceException;
	
	void writeOffBookService(String request) throws ServiceException;
	
	void changeBookStatusService(String request) throws ServiceException;	

	void deleteBookService(String request) throws ServiceException;

}
