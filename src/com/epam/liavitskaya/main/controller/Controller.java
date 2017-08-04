package com.epam.liavitskaya.main.controller;

import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.test.parser.Parser;
import com.epam.liavitskaya.test.parser.provider.ParserProvider;
import com.epam.liavitskaya.test.parser.reflection.ReflectionClassLoader;

public class Controller {

	private final static CommandProvider provider;
	private final char paramDelimeter = ' ';
	private static Controller instance;

	private Controller() {
		super();
	}

	static {
		// selection of a suitable parser
		Parser parser = ParserProvider.getInstance().getSaxcommandparser();
		// Parser parser = ParserProvider.getInstance().getStaxcommandparser();
		// Parser parser = ParserProvider.getInstance().getDomcommandparser();
		provider = new CommandProvider(ReflectionClassLoader.initRepository(parser.parseXML()));
	}

	public static Controller getInstance() {

		if (null == instance) {
			instance = new Controller();
		}
		return instance;
	}

	public String executeTask(String request) {

		String commandName = "";

		if (request.isEmpty() || request != null) {
			if (request.contains(" ")) {
				commandName = request.substring(0, request.indexOf(paramDelimeter));
			} else {
				commandName = request.substring(0, request.length());
			}
		}

		Command executionCommand;
		executionCommand = provider.getCommand(commandName);

		String response = "";
		response = executionCommand.execute(request);
		return response;
	}

}
