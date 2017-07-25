package com.epam.liavitskaya.test.xmldata.converter;

import java.util.ArrayList;
import java.util.List;

import com.epam.liavitskaya.test.xmldata.beantest.BookTest;
import com.epam.liavitskaya.test.xmldata.beantest.BooksTest;
import com.epam.liavitskaya.test.xmldata.beantest.UserTest;
import com.epam.liavitskaya.test.xmldata.beantest.UsersTest;
import com.epam.liavitskaya.test.xmldata.marshaller.JAXBMarshallerBook;
import com.epam.liavitskaya.test.xmldata.marshaller.JAXBMarshallerUser;
import com.epam.liavitskaya.test.xmldata.unmarshaller.JAXBUnMarshallerBook;
import com.epam.liavitskaya.test.xmldata.unmarshaller.JAXBUnMarshallerUser;

public class Converter {

	public static List<String> convertXMLToRequestArgs(String xmlPath) {

		String xmlType = xmlPath.substring(xmlPath.indexOf("_") + 1, xmlPath.indexOf("."));
		List<String> requestArgs = new ArrayList<>();

		if (xmlType.equals("book")) {

			JAXBMarshallerBook.marshallBook();
			BooksTest books = JAXBUnMarshallerBook.unMarsall(xmlPath);

			for (BookTest book : books.getBooks()) {

				String arg;
				arg = book.getTitle() + " " + book.getAuthor() + " " + book.getDescription();
				requestArgs.add(arg);
			}

		} else if (xmlType.equals("user")) {

			JAXBMarshallerUser.marshallUser();
			UsersTest users = JAXBUnMarshallerUser.unMarshall(xmlPath);

			for (UserTest user : users.getUsers()) {

				String arg;
				arg = user.getUserName() + " " + user.getPassportNo() + " " + user.getPhone() + " " + user.getEmail()
						+ " " + user.getUserRole() + " " + user.getLogin() + " " + user.getPassword() + " "
						+ user.getUserStatus();
				requestArgs.add(arg);
			}
		}

		return requestArgs;

	}

}