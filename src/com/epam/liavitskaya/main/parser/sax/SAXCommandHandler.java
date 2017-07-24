package com.epam.liavitskaya.main.parser.sax;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXCommandHandler extends DefaultHandler {

	private Map<String, String> commandMap = new HashMap<>();
	private StringBuilder text;
	private StringBuilder attribute;

	public Map<String, String> getCommandMap() {
		return commandMap;
	}

	public void setCommandMap(Map<String, String> commandMap) {
		this.commandMap = commandMap;
	}

	@Override
	public void startDocument() throws SAXException {		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		text = new StringBuilder();
		attribute = new StringBuilder();
		attribute.append(attributes.getValue("name"));
	}

	@Override
	public void endDocument() throws SAXException {		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {		

		if ("command".equals(qName)) {

			commandMap.put(attribute.toString().trim(), text.toString().trim());
		}
	}

	@Override
	public void characters(char[] buffer, int start, int length) throws SAXException {
		
		text.append(buffer, start, length);

	}
}
