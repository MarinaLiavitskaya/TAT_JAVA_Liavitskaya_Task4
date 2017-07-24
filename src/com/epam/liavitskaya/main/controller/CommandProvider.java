package com.epam.liavitskaya.main.controller;

import java.util.Map;

import com.epam.liavitskaya.main.controller.command.Command;

public class CommandProvider {

	private final Map<String, Command> repository;

	public CommandProvider(Map<String, Command> repository) {

		this.repository = repository;
	}

	Command getCommand(String commandName) {

		Command command = null;
		try {
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get("wrong_request");
		}
		return command;
	}
}
