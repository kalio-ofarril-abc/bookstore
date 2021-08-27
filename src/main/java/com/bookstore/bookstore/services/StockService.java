package com.bookstore.bookstore.services;

import com.bookstore.bookstore.entities.Stock;

import java.util.List;

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


	public String addStock(List<Stock> stockList){
		stockRepository.saveAll(stockList);
		return "Stock added";
	}
	
}
