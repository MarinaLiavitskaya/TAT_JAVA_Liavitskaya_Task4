package com.epam.liavitskaya.main.parser.reflection;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.controller.command.Command;

public class ReflectionClassLoader {

	static final Logger logger = Logger.getLogger(ReflectionClassLoader.class);

	private static Map<String, Command> repository = new HashMap<>();

	public static Map<String, Command> initRepository(Map<String, String> map) {

		for (Map.Entry<String, String> entry : map.entrySet()) {

			Command command;
			Class<?> clas;

			try {
				clas = Class.forName(entry.getValue());
				command = (Command) clas.newInstance();

				repository.put(entry.getKey(), command);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				logger.error("error during comand class loading", e);
			}
		}

		return repository;
	}

}
