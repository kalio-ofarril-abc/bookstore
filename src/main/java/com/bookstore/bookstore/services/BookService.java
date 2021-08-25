package com.bookstore.bookstore.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookstore.entities.Book;
import com.bookstore.bookstore.exceptions.BookException;
import com.bookstore.bookstore.repositories.BookRepository;
import com.bookstore.bookstore.repositories.StockRepository;
import com.bookstore.bookstore.util.BookResponse;
import com.bookstore.bookstore.util.EvenBookResponse;

@Service
public class BookService{

	
	private final BookRepository bookRepository;
	private final StockRepository stockRepository;
	
	
	@Autowired
	public BookService(BookRepository bookRepository, StockRepository stockRepository) {
		this.bookRepository = bookRepository;
		this.stockRepository = stockRepository;
	}
	
	
	public List<BookResponse> getAllBooks(){	
		List<Book> allBooks = bookRepository.findAll();
		List<String> allBookIds = allBooks.stream()
				.map(book -> book.getId())
				.collect(Collectors.toList());
		Set<String> availableBooks = new HashSet<>(stockRepository.findByIdIn(allBookIds).stream()
				.map(stock -> stock.getId())
				.collect(Collectors.toList()));
		return allBooks.stream()
				.filter(book -> availableBooks.contains(book.getId()))
				.map(book -> {					
					BookResponse bookResponse = new BookResponse(book);
					return bookResponse;
					
				})
				.collect(Collectors.toList());
	}
	
	
	public String addBook(List<Book> books){
		try {
			for(Book book : books){
				if(bookRepository.findByTitle(book.getTitle()) != null) {
					throw new BookException(701,"Book Title \"" + book.getTitle() +"\" is already associated to other ID.");
				}
			}
			bookRepository.saveAll(books);
			return "Books added succesfully.";
		}catch(BookException e){
			return e.toString();
		}
	}
	
	
	public List<EvenBookResponse> getEvenBooks(){
		List<EvenBookResponse> evenBooks = bookRepository.findAll().stream()
				.map(book -> {
					EvenBookResponse map = new EvenBookResponse();
					map.setTitle(book.getTitle());
					map.setPublicationYear(book.getYear());
					map.setVowelsSum((int) book.getTitle().toLowerCase().chars().mapToObj(c -> (char) c)
									.filter(c -> {
										Set<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u'));
										return set.contains(c);
									})
									.count()
							);
					return map;
				})
				.filter(map -> map.getVowelsSum()%2==0)
				.collect(Collectors.toList());
		Collections.sort(evenBooks, (b1,b2) -> b1.getPublicationYear() - b2.getPublicationYear());	
		return evenBooks;
	}
	
}