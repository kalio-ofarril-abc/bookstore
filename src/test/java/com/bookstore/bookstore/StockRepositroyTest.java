package com.bookstore.bookstore;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.bookstore.bookstore.entities.Stock;
import com.bookstore.bookstore.repositories.StockRepository;

@DataJpaTest
class StockRepositroyTest {
	
	/***************************************************
	 * This are the test cases for the Stock Class.
	 * 
	 * Here we only test methods which have a
	 * personalized query.
	 * 
	 * The initial data for the tests is inserted
	 * into the H2 DB at the build of the application
	 * and can be found in the data.sql file in the
	 * resources.
	 * 
	 ***************************************************/
	
	
	@Autowired
	private StockRepository underTest; 
	
	
	@Test
	void testFindByIdIn() {
		
		//Given
		List<String> allBookIds = Arrays.asList(new String[]{"TestId1","TestId3","TestId3","TestId4"});
		
		//When
		List<Stock> availableIds = underTest.findByIdIn(allBookIds);
		
		//Then
		List<Stock> booksToBeExpected = Arrays.asList(new Stock[] {new Stock("TestId1","TestTitle1"), new Stock("TestId3","TestTitle3")});
		assertThat(availableIds.toString()).isEqualTo(booksToBeExpected.toString());
	}
	
}
