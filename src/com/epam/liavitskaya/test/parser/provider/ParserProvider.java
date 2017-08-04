package com.epam.liavitskaya.test.parser.provider;

import com.epam.liavitskaya.test.parser.dom.DOMCommandParser;
import com.epam.liavitskaya.test.parser.sax.SAXCommandParser;
import com.epam.liavitskaya.test.parser.stax.StAXCommandParser;

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
