package com.epam.liavitskaya.test.testdata.marshaller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.epam.liavitskaya.test.testdata.entity.BookTest;
import com.epam.liavitskaya.test.testdata.entity.BooksTest;

public class JAXBMarshallerBook {

	public static void marshallBook() {

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(BooksTest.class);

			Marshaller marshaller = context.createMarshaller();

			BookTest book1 = new BookTest();
			book1.setBookId(0);
			book1.setTitle("Breakfast_of_Champions");
			book1.setAuthor("Kurt_Vonnegut");
			book1.setDescription("US_1973");
			book1.setBookStatus("AVAILABLE");
			book1.setUserId(0);

			BookTest book2 = new BookTest();
			book2.setBookId(0);
			book2.setTitle("One_Flew_Over_the_Cuckoo's_Nest");
			book2.setAuthor("Ken_Kesey");
			book2.setDescription("US_1962");
			book2.setBookStatus("AVAILABLE");
			book2.setUserId(0);

			BookTest book3 = new BookTest();
			book3.setBookId(0);
			book3.setTitle("Flowers_for_Algernon");
			book3.setAuthor("Daniel_Keyes");
			book3.setDescription("US_1958");
			book3.setBookStatus("AVAILABLE");
			book3.setUserId(0);
						
			BookTest book4 = new BookTest();
			book4.setBookId(0);
			book4.setTitle("LOLITA");
			book4.setAuthor("VLADIMIR_NABOKOV");
			book4.setDescription("RUSSIA");
			book4.setBookStatus("AVAILABLE");
			book4.setUserId(0);
			
			BooksTest booksTest = new BooksTest(); 
			booksTest.setBooks(new ArrayList<BookTest>());
			
			booksTest.getBooks().add(book1);
			booksTest.getBooks().add(book2);
			booksTest.getBooks().add(book3);
			booksTest.getBooks().add(book4);
			
			marshaller.marshal(booksTest, new FileOutputStream("resources/test_book.xml"));						

		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
