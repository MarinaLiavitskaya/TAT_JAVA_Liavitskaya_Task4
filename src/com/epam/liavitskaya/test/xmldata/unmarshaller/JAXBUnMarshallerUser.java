package com.epam.liavitskaya.test.xmldata.unmarshaller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.epam.liavitskaya.test.xmldata.beantest.UsersTest;

public class JAXBUnMarshallerUser {

	public static UsersTest unMarshall(String path) {

		UsersTest usersTest = null;

		try {
			JAXBContext context = JAXBContext.newInstance(UsersTest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			usersTest = (UsersTest) unmarshaller.unmarshal(new FileInputStream("resources/test_user.xml"));

		} catch (FileNotFoundException | JAXBException e) {
			e.printStackTrace();
		}

		return usersTest;

	}

}
