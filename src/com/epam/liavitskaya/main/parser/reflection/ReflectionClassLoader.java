package com.epam.liavitskaya.main.parser.reflection;

import java.util.HashMap;
import java.util.Map;

import com.epam.liavitskaya.main.controller.command.Command;

public class ReflectionClassLoader {

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
				e.printStackTrace();
			}
		}

		return repository;
	}

}
