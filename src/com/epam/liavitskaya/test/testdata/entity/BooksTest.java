package com.epam.liavitskaya.test.testdata.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Books")
@XmlAccessorType (XmlAccessType.FIELD)
public class BooksTest {	
	
	    @XmlElement(name = "books")
	    private List<BookTest> books = null;

		public List<BookTest> getBooks() {
			return books;
		}

		public void setBooks(List<BookTest> books) {
			this.books = books;
		}	 
	   
	}