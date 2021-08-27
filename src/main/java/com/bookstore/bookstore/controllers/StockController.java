package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.entities.Stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookstore.services.StockService;

@RestController
public class StockController {
	
	
	private final StockService stockService;
	
	
	@Autowired
	public StockController(StockService stockService) {
		this.stockService  = stockService;
	}


	@PostMapping( path = "addStock")
	public String addStock(@RequestBody List<Stock> stockList){
		return stockService.addStock(stockList);
	}

}
