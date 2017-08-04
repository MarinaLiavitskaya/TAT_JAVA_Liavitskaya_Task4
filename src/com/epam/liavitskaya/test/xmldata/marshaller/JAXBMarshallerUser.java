package com.epam.liavitskaya.test.xmldata.marshaller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.bean.User;
import com.epam.liavitskaya.main.bean.UsersTest;

public class JAXBMarshallerUser {

	static final Logger logger = Logger.getLogger(JAXBMarshallerUser.class);

	public static void marshallUser() {

		try {
			JAXBContext context = JAXBContext.newInstance(UsersTest.class);
			Marshaller marshaller = context.createMarshaller();

			UsersTest usersTest = new UsersTest();
			usersTest.setUsers(new ArrayList<User>());

			User userTest1 = new User();
			userTest1.setUserName("Marina");
			userTest1.setPassportNo("MP3798891");
			userTest1.setPhone("297781991");
			userTest1.setEmail("email@marina");
			userTest1.setUserRole("USER");
			userTest1.setLogin("marina_test00");
			userTest1.setPassword("encryptTest@00");
			userTest1.setUserStatus("ACTIVE");

			User userTest2 = new User();
			userTest2.setUserName("Juras");
			userTest2.setPassportNo("MP2306723");
			userTest2.setPhone("297702722");
			userTest2.setEmail("email@juras");
			userTest2.setUserRole("USER");
			userTest2.setLogin("juras_test00");
			userTest2.setPassword("encryptTest@22");
			userTest2.setUserStatus("ACTIVE");

			User userTest3 = new User();
			userTest3.setUserName("Natalie");
			userTest3.setPassportNo("MP7777777");
			userTest3.setPhone("297777777");
			userTest3.setEmail("email@77777");
			userTest3.setUserRole("USER");
			userTest3.setLogin("user_test777");
			userTest3.setPassword("encryptT@777");
			userTest3.setUserStatus("ACTIVE");

			usersTest.getUsers().add(userTest1);
			usersTest.getUsers().add(userTest2);
			usersTest.getUsers().add(userTest3);

			marshaller.marshal(usersTest, new FileOutputStream("resources/test_user.xml"));

		} catch (JAXBException | FileNotFoundException e) {
			logger.error("error during marshalling users", e);
		}

	}

}
