package com.epam.liavitskaya.test.xmldata.unmarshaller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.bean.UsersTest;

public class JAXBUnMarshallerUser {

	static final Logger logger = Logger.getLogger(JAXBUnMarshallerUser.class);

	public static UsersTest unMarshall(String path) {

		UsersTest usersTest = null;

		try {
			JAXBContext context = JAXBContext.newInstance(UsersTest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			usersTest = (UsersTest) unmarshaller.unmarshal(new FileInputStream(path));

		} catch (FileNotFoundException | JAXBException e) {
			logger.error("error during unmarshalling users", e);
		}

		return usersTest;

	}

}
