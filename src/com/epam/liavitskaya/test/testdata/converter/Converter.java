package com.epam.liavitskaya.test.testdata.converter;

import java.util.ArrayList;
import java.util.List;

import com.epam.liavitskaya.test.testdata.entity.BookTest;
import com.epam.liavitskaya.test.testdata.entity.BooksTest;
import com.epam.liavitskaya.test.testdata.entity.UserTest;
import com.epam.liavitskaya.test.testdata.entity.UsersTest;
import com.epam.liavitskaya.test.testdata.marshaller.JAXBMarshallerBook;
import com.epam.liavitskaya.test.testdata.marshaller.JAXBMarshallerUser;
import com.epam.liavitskaya.test.testdata.unmarshaller.JAXBUnMarshallerBook;
import com.epam.liavitskaya.test.testdata.unmarshaller.JAXBUnMarshallerUser;

public class Converter {

	public static List<String> convertToRequestArgs(String xmlPath) {

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
