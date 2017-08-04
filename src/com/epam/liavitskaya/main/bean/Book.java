package com.epam.liavitskaya.main.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.epam.liavitskaya.main.bean.enumeration.BookStatus;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book", propOrder = { "title", "author", "description", "bookStatus", "userId" })
public class Book implements Serializable {

	private static final long serialVersionUID = 6358225220968981681L;

	@XmlAttribute(required = true)
	private int bookId;

	@XmlElement(required = true)
	private String title;

	@XmlElement(required = true)
	private String author;

	@XmlElement(required = true)
	private String description;

	@XmlElement(required = true)
	private BookStatus bookStatus;

	@XmlElement(required = true)
	private int userId;

	public Book() {
		super();
	}

	public Book(String title, String author, String description, BookStatus bookStatus) {
		this.title = title;
		this.author = author;
		this.description = description;
		this.bookStatus = bookStatus;
	}

	public Book(int bookId, String title, String author, String description) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
	}

	public Book(int bookId, String title, String author, String description, BookStatus bookStatus, int userId) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.bookStatus = bookStatus;
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBookStatus() {
		return bookStatus.name();
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = BookStatus.valueOf(bookStatus);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + bookId;
		result = prime * result + ((bookStatus == null) ? 0 : bookStatus.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookId != other.bookId)
			return false;
		if (bookStatus != other.bookStatus)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nBook [title = " + title + ", author = " + author + ", description = " + description
				+ ", bookStatus = " + bookStatus + "]";
	}
}
