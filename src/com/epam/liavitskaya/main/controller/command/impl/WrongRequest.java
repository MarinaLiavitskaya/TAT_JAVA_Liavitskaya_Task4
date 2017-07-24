package com.epam.liavitskaya.main.controller.command.impl;

import com.epam.liavitskaya.main.controller.command.Command;

public class WrongRequest implements Command {

	@Override
	public String execute(String request) {

		String response = "Wrong Request Format";
		return response;

	}
}
