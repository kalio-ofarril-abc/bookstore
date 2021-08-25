package com.bookstore.bookstore.util;

import com.bookstore.bookstore.entities.Book;

//Book Response class to match the desired format and variables of the get request
public class BookResponse {

	private String title;
	private String author;
	private String publisher;
	private int publicationYear;
	
	public BookResponse(String title,
			String author,
			String publisher,
			int publicationYear) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
	}
	
	public BookResponse(Book book) {
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.publisher = book.getPublisher();
		this.publicationYear = book.getYear();
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	
	@Override
	public String toString() {
		return "BookResponse [title=" + title + ", author=" + author + ", publisher=" + publisher + ", publicationYear="
				+ publicationYear + "]";
	}
}
