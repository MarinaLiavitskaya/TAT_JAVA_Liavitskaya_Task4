package com.epam.liavitskaya.main.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigUtil {
	
	private static ConfigUtil instance = null;
	private Properties properties;
	final Logger logger = Logger.getLogger(ConfigUtil.class);

	private ConfigUtil() throws IOException {
		
		properties = new Properties();		
		InputStream input = getClass().getClassLoader()
				.getResourceAsStream("library_db.properties");		
		try {
			properties.load(input);
		} catch (IOException e) {
			logger.error("Problems occured during loading DB Properties", e);
		}
	}

	public static ConfigUtil getInstance() {
		if (instance == null) {
			try {
				instance = new ConfigUtil();
			} catch (IOException e) {
				throw new RuntimeException("Problems occured during loading DB Properties!");
			}
		}
		return instance;
	}

	public Properties getPropertiesValues() {
		return properties;
	}
}