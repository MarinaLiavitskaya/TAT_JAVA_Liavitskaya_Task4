package com.epam.liavitskaya.test.parser.sax;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.liavitskaya.test.parser.Parser;

public class SAXCommandParser implements Parser {

	static final Logger logger = Logger.getLogger(SAXCommandParser.class);

	static final String COMMAND_XML_PATH = "resources/command.xml";

	private Map<String, String> commandMap = new HashMap<>();

	@Override
	public Map<String, String> parseXML() {

		SAXCommandHandler handler = getHandler();

		commandMap = handler.getCommandMap();

		return commandMap;
	}

	private SAXCommandHandler getHandler() {

		SAXCommandHandler handler = null;
		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			handler = new SAXCommandHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(COMMAND_XML_PATH));

		} catch (IOException | SAXException e) {
			logger.error("error during SAX parsing", e);
		}
		return handler;
	}

}
