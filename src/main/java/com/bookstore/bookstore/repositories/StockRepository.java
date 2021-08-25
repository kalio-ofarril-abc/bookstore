package com.bookstore.bookstore.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.bookstore.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, String>{

	@Query("SELECT b FROM Stock b WHERE ID IN :ids")
	List<Stock> findByIdIn(@Param("ids") List<String> allBookIds);

}
