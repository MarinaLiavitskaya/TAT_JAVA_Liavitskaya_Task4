package com.epam.liavitskaya.test.xmldata.testentity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.epam.liavitskaya.main.enumeration.UserRoles;
import com.epam.liavitskaya.main.enumeration.UserStatus;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = { "userName", "passportNo", "phone", "email", "userRole", "login", "password", "userStatus" })
public class UserTest {

	@XmlAttribute(required = true)
	private int userId;
	
	@XmlElement(required = true)
	private String userName;
	
	@XmlElement(required = true)
	private String passportNo;
	
	@XmlElement(required = true)
	private String phone;
	
	@XmlElement(required = true)
	private String email;
	
	@XmlElement(required = true)
	private UserRoles userRole;
	
	@XmlElement(required = true)
	private String login;
	
	@XmlElement(required = true)
	private String password;
	
	@XmlElement(required = true)
	private UserStatus userStatus;

	public UserTest() {
		super();
	}

	public UserTest(String userName, String login, String password) {
		this.userName = userName;
		this.login = login;
		this.password = password;
	}

	public UserTest(String userName, UserRoles userRole, String login, String password, UserStatus userStatus) {
		this.userName = userName;
		this.userRole = userRole;
		this.login = login;
		this.password = password;
		this.userStatus = userStatus;
	}

	public UserTest(String userName, String passportNo, String phone, String email, UserRoles userRole, String login,
			String password, UserStatus userStatus) {
		this.userName = userName;
		this.passportNo = passportNo;
		this.phone = phone;
		this.email = email;
		this.userRole = userRole;
		this.login = login;
		this.password = password;
		this.userStatus = userStatus;
	}

	public UserTest(int userId, String userName, String passportNo, String phone, String email, String login,
			String password) {
		this.userId = userId;
		this.userName = userName;
		this.passportNo = passportNo;
		this.phone = phone;
		this.email = email;
		this.login = login;
		this.password = password;
	}

	public UserTest(int userId, String userName, String passportNo, String phone, String email, UserRoles userRole,
			String login, String password, UserStatus userStatus) {
		this.userId = userId;
		this.userName = userName;
		this.passportNo = passportNo;
		this.phone = phone;
		this.email = email;
		this.userRole = userRole;
		this.login = login;
		this.password = password;
		this.userStatus = userStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserRole() {
		return userRole.name();
	}

	public void setUserRole(String userRole) {
		this.userRole = UserRoles.valueOf(userRole);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserStatus() {
		return userStatus.name();
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = UserStatus.valueOf(userStatus);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((passportNo == null) ? 0 : passportNo.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		result = prime * result + ((userStatus == null) ? 0 : userStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTest other = (UserTest) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (passportNo == null) {
			if (other.passportNo != null)
				return false;
		} else if (!passportNo.equals(other.passportNo))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userRole != other.userRole)
			return false;
		if (userStatus != other.userStatus)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nUserTest [userName = " + userName + ", userPassportNo = " + passportNo + ", phone = " + phone
				+ ", email = " + email + ", userRole = " + userRole + ", login = " + login + ", userStatus = "
				+ userStatus + "]";
	}

}
