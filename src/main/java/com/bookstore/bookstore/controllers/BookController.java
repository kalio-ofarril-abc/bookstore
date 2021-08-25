package com.bookstore.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookstore.entities.Book;
import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.util.BookResponse;
import com.bookstore.bookstore.util.EvenBookResponse;

@RestController
@RequestMapping 
public class BookController {
	
	
	private final BookService bookService;
	
	
	@Autowired
	public BookController(BookService studentService) {
		this.bookService = studentService;
	}
	
	
	@GetMapping( 
			path = "getBooks",
			produces = "application/json"
	)
	public List<BookResponse> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	
	@GetMapping(
			path = "getEvenBooks",
			produces = "application/json"
	)
	public List<EvenBookResponse> getEvenBooks(){
		return bookService.getEvenBooks();
	}

	
	@PostMapping( path = "addBooks")
	public String addBook(@RequestBody List<Book> books) {
		return bookService.addBook(books);
	}
}
