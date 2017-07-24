package com.epam.liavitskaya.main.parser.provider;

import com.epam.liavitskaya.main.parser.dom.DOMCommandParser;
import com.epam.liavitskaya.main.parser.sax.SAXCommandParser;
import com.epam.liavitskaya.main.parser.stax.StAXCommandParser;

public class ParserProvider {

	private static final ParserProvider instance = new ParserProvider();

	private final SAXCommandParser saxCommandParser = new SAXCommandParser();
	private final StAXCommandParser stAXCommandParser = new StAXCommandParser();
	private final DOMCommandParser domCommandParser = new DOMCommandParser();

	public static ParserProvider getInstance() {
		return instance;
	}

	public SAXCommandParser getSaxcommandparser() {
		return saxCommandParser;
	}

	public StAXCommandParser getStaxcommandparser() {
		return stAXCommandParser;
	}

	public DOMCommandParser getDomcommandparser() {
		return domCommandParser;
	}	
	
}
