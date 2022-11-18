package com.example.stock.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.example.stock.market.entity.StockData;

@Repository
public interface StockDataRepository extends JpaRepository<StockData,Integer>{

	List<StockData> findByStock(String stockId);
}
