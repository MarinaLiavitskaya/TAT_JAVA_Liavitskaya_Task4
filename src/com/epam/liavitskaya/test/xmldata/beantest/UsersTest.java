package com.epam.liavitskaya.test.xmldata.beantest;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersTest {

	@XmlElement(name = "users")
	private List<UserTest> users = null;

	public List<UserTest> getUsers() {
		return users;
	}

	public void setUsers(List<UserTest> users) {
		this.users = users;
	}	
	
}
