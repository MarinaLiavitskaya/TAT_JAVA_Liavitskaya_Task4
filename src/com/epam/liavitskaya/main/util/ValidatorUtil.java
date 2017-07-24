package com.epam.liavitskaya.main.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

	private static final String LOGIN_PATTERN = "^(?=\\S+$)([a-z0-9_-]){5,17}$";	
	// ^ # Start of the line
	// (?=\S+$) # No whitespace allowed in the entire string
	// [a-z0-9_-] # Match characters and symbols in the list, a-z, 0-9,
	// underscore, hyphen
	// {5,17} # Length at least 5 characters and maximum length of 17
	// $ # End of the line
	
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";	
	// ^ # start-of-string
	// (?=.*[0-9]) # a digit must occur at least once
	// (?=.*[a-z]) # a lower case letter must occur at least once
	// (?=.*[A-Z]) # an upper case letter must occur at least once
	// (?=.*[@#$%^&+=]) # a special character must occur at least once
	// (?=\S+$) # no whitespace allowed in the entire string
	// .{8,} # anything, at least eight places though
	// $ # end-of-string

	public static boolean isPasswordValid(String password) {

		Pattern r = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = r.matcher(password);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static boolean isLoginValid(String login) {

		Pattern r = Pattern.compile(LOGIN_PATTERN);
		Matcher matcher = r.matcher(login);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
}
