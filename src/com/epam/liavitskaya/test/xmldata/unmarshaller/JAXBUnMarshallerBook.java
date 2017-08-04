package com.epam.liavitskaya.test.xmldata.unmarshaller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.bean.BooksTest;

public class JAXBUnMarshallerBook {

	static final Logger logger = Logger.getLogger(JAXBUnMarshallerBook.class);

	public static BooksTest unMarsall(String path) {

		BooksTest booksTest = null;

		try {
			JAXBContext context = JAXBContext.newInstance(BooksTest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			booksTest = (BooksTest) unmarshaller.unmarshal(new FileInputStream(path));

		} catch (FileNotFoundException | JAXBException e) {
			logger.error("error during unmarshalling books", e);
		}
		return booksTest;
	}

}
