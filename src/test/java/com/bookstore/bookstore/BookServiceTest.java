package com.bookstore.bookstore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.bookstore.bookstore.entities.Book;
import com.bookstore.bookstore.exceptions.BookException;
import com.bookstore.bookstore.repositories.BookRepository;
import com.bookstore.bookstore.repositories.StockRepository;
import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.util.BookResponse;
import com.bookstore.bookstore.util.EvenBookResponse;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class BookServiceTest {
	
	/***************************************************
	 * This are the test cases for the BookService Class.
	 * 
	 * The initial data for the tests is inserted
	 * into the H2 DB at the build of the application
	 * and can be found in the data.sql file in the
	 * resources.
	 * 
	 ***************************************************/
	
	
	@Mock private BookRepository bookRepositoryMock;
	
	
	@Captor private ArgumentCaptor<List<Book>> bookArgumentCaptor;
	
	
	@Autowired private BookRepository bookRepository;
	@Autowired private StockRepository stockRepository;
	private BookService underTest;
	
	
	@BeforeEach
	void setUp() {
		underTest = new BookService(bookRepository,stockRepository);
	}
	
	
	@Test
	void testGetAllBooks() {
		
		List<BookResponse> expetedBooks = Arrays.asList(new BookResponse[] {
				new BookResponse("TestTitle1","TestAuthor1","TestPublisher1",2001),
				new BookResponse("TestTitle2","TestAuthor2","TestPublisher2",2002),
				new BookResponse("TestTitle3","TestAuthor3","TestPublisher3",2003)
		});
		
		//When
		List<BookResponse> booksShown = underTest.getAllBooks();
		
		//Then		
		assertThat(booksShown.toString()).isEqualTo(expetedBooks.toString());
	}

	
	@Test
	void testAddBook() {
		
		BookService underMockTest = new BookService(bookRepositoryMock,stockRepository);
		
		//Given
		List<Book> bookListTest = Arrays.asList(new Book[] {new Book("AddTestId","AddTestTitle","AddTestAuthor","AddTestPublisher",1969)});
				
		//When
		underMockTest.addBook(bookListTest);
		
		//Then		
		verify(bookRepositoryMock).saveAll((bookArgumentCaptor.capture()));
		
		List<Book> capturedBookList = bookArgumentCaptor.getValue();
		
		assertThat(capturedBookList).isEqualTo(bookListTest);
	}

	
	@Test
	void testGetEvenBooks() {
    		
		List<EvenBookResponse> expectedBooks = Arrays.asList(new EvenBookResponse[] {
				new EvenBookResponse("TestAnEvenTitleMoreVowels", 2001,10),
				new EvenBookResponse("TestAnEvenTitle", 2003, 6),
				new EvenBookResponse("TestAnEvenTitle2", 2005, 6)
		});
		
		//When
		List<EvenBookResponse> booksShown = underTest.getEvenBooks();
		
		//Then
		assertThat(booksShown.toString()).isEqualTo(expectedBooks.toString());
	}
	
	
	@Test
	void testExistentTitleExceptionMessage() {
		
		//Given
		List<Book> bookListTest = Arrays.asList(new Book[] {new Book("TestId10","TestTitle1","TestAuthor1","TestPublisher1",2001)});
		String expectedExceptionMessage = "BookException [errorCode 701, Book Title \"TestTitle1\" is already associated to other ID.]";
		
		//When
		String actualExceptionMessage =  underTest.addBook(bookListTest);
		
		//Then
		assertTrue(actualExceptionMessage.contains(expectedExceptionMessage));
	}

}
