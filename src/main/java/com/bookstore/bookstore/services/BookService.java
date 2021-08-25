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
		//Retrieve all the Book data from the Book table
		List<Book> allBooks = bookRepository.findAll();
		
		//Create a list of the Book Ids
		List<String> allBookIds = allBooks.stream()
				.map(book -> book.getId())
				.collect(Collectors.toList());
		
		//Filter for the Ids that are present in the Stock table 
		Set<String> availableBooks = new HashSet<>(stockRepository.findByIdIn(allBookIds).stream()
				.map(stock -> stock.getId())
				.collect(Collectors.toList()));
		
		//Return books whose Id passed the filtering
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
			
			//Check whether the title of any of the posted books is already stored in the Book table
			for(Book book : books){
				if(bookRepository.findByTitle(book.getTitle()) != null) {
					throw new BookException(701,"Book Title \"" + book.getTitle() +"\" is already associated to other ID.");
				}
			}
			
			//If no BookExcpetion is thrown, save all the books
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
					
					//Create a char stream of the characters in the Book title
					map.setVowelsSum((int) book.getTitle().toLowerCase().chars().mapToObj(c -> (char) c)
							
									//Filter for vowels
									.filter(c -> {
										Set<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u'));
										return set.contains(c);
									})
									
									//Count number of elements remaining in the stream and set the number to the EvenBookMap
									.count()
							);
					return map;
				})
				
				//Keep books whose title vowels sum is a pair number
				.filter(map -> map.getVowelsSum()%2==0)
				.collect(Collectors.toList());
		Collections.sort(evenBooks, (b1,b2) -> b1.getPublicationYear() - b2.getPublicationYear());	
		return evenBooks;
	}
	
}