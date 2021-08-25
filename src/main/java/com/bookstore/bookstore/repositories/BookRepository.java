package com.bookstore.bookstore.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstore.entities.Book;

public interface BookRepository extends JpaRepository<Book,String>{
	
	public Book findByTitle(String string);

}
