package com.bookstore.bookstore.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;

@Entity( name = "Book")
@Table(name = "Book")
public class Book {

	@Id
	@Column(
			name = "id",
			updatable = false
	)
	private String id;
	
	@Column(
			name = "title",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String title;
	
	@Column(
			name = "author",
			columnDefinition = "TEXT"
	)
	private String author;
	
	@Column(
			name = "publisher",
			columnDefinition = "TEXT"
	)
	private String publisher;
	
	@Column(
			name = "PUBLICATION_YEAR",
			nullable = false,
			columnDefinition = "integer"
	)
	private Integer year;
	
	public Book() {}
	
	public Book(String id,
			String title,
			String author,
			String publisher,
			Integer year) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publisher=" + publisher + ", year="
				+ year + "]";
	}
	
	
	
}
