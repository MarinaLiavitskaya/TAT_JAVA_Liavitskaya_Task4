package com.epam.liavitskaya.main.util;

import com.epam.liavitskaya.main.service.exception.ServiceException;

public class RequestParserUtil {

	public static String[] parseRequest(String request, int necessarydParts) throws ServiceException {

		if (request.isEmpty() || request == null) {
			throw new ServiceException("incorrect request");
		}
		String[] requestParts = request.split(" ");
		
		if (requestParts.length != necessarydParts) {
			throw new ServiceException("incorrect request");
		}
		return requestParts;
	}
}
