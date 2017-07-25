package com.epam.liavitskaya.test.xmldata.unmarshaller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.epam.liavitskaya.test.xmldata.beantest.BooksTest;

public class JAXBUnMarshallerBook {

	public static BooksTest unMarsall(String path) {
		
		BooksTest booksTest = null;

		try {
			JAXBContext context = JAXBContext.newInstance(BooksTest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			booksTest = (BooksTest) unmarshaller.unmarshal(new FileInputStream("resources/test_book.xml"));
	
		} catch (FileNotFoundException | JAXBException e) {
			e.printStackTrace();
		}
		return booksTest;
	}

}
