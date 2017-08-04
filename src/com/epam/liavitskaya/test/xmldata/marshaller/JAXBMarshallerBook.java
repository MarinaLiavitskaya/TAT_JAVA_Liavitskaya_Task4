package com.epam.liavitskaya.test.xmldata.marshaller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.bean.Book;
import com.epam.liavitskaya.main.bean.BooksTest;

public class JAXBMarshallerBook {

	static final Logger logger = Logger.getLogger(JAXBMarshallerBook.class);

	public static void marshallBook() {

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(BooksTest.class);

			Marshaller marshaller = context.createMarshaller();

			Book book1 = new Book();
			book1.setBookId(0);
			book1.setTitle("Breakfast_of_Champions");
			book1.setAuthor("Kurt_Vonnegut");
			book1.setDescription("US_1973");
			book1.setBookStatus("AVAILABLE");
			book1.setUserId(0);

			Book book2 = new Book();
			book2.setBookId(0);
			book2.setTitle("One_Flew_Over_the_Cuckoo's_Nest");
			book2.setAuthor("Ken_Kesey");
			book2.setDescription("US_1962");
			book2.setBookStatus("AVAILABLE");
			book2.setUserId(0);

			Book book3 = new Book();
			book3.setBookId(0);
			book3.setTitle("Flowers_for_Algernon");
			book3.setAuthor("Daniel_Keyes");
			book3.setDescription("US_1958");
			book3.setBookStatus("AVAILABLE");
			book3.setUserId(0);

			Book book4 = new Book();
			book4.setBookId(0);
			book4.setTitle("LOLITA");
			book4.setAuthor("VLADIMIR_NABOKOV");
			book4.setDescription("RUSSIA");
			book4.setBookStatus("AVAILABLE");
			book4.setUserId(0);

			Book book5 = new Book();
			book5.setBookId(0);
			book5.setTitle("Anna Karenina");
			book5.setAuthor("Leo_Tolstoy");
			book5.setDescription("Russia_1877");
			book5.setBookStatus("AVAILABLE");
			book5.setUserId(0);

			BooksTest booksTest = new BooksTest();
			booksTest.setBooks(new ArrayList<Book>());

			booksTest.getBooks().add(book1);
			booksTest.getBooks().add(book2);
			booksTest.getBooks().add(book3);
			booksTest.getBooks().add(book4);
			booksTest.getBooks().add(book5);

			marshaller.marshal(booksTest, new FileOutputStream("resources/test_book.xml"));

		} catch (JAXBException | FileNotFoundException e) {
			logger.error("error during marshalling books", e);
		}
	}

}
