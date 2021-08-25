package com.bookstore.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookstore.repositories.StockRepository;

@Service
public class StockService {
	
	private final StockRepository stockRepository;
	
	@Autowired
	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
	
}
