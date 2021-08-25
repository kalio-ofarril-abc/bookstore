package com.bookstore.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Stock")
@Table( name = "current_stock")
public class Stock {

	@Id
	@Column(
			name = "id",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String id;
	
	@Column(
			name = "title",
			nullable = false,
			columnDefinition = "TEXT")
	private String title;
	
	public Stock() {
		
	}
	
	public Stock(String id, String title){
		this.id = id;
		this.title = title;
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

	@Override
	public String toString() {
		return "CurrentStock [id=" + id + ", title=" + title + "]";
	}
	
	
	
}
