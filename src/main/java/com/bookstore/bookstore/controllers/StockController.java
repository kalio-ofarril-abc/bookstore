package com.bookstore.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookstore.services.StockService;

@RestController
public class StockController {
	
	
	private final StockService stockService;
	
	
	@Autowired
	public StockController(StockService stockService) {
		this.stockService  = stockService;
	}
	

}
