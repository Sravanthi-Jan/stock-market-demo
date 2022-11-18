package com.example.stock.market.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.stock.market.entity.StockData;
import com.example.stock.market.exception.InvalidDataException;
import com.example.stock.market.service.StockDataService;
import org.springframework.util.StringUtils;

@RestController
@RequestMapping("/demo")
public class StockDataController {
	
	@Autowired
	StockDataService service;

	@GetMapping("/getAllStocks")
	public List<StockData> getAllStocks(){
		
		return service.getAllStocks();
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/getStock/{stockId}")
	public List<StockData> getStockWithId(@PathVariable String stockId) throws InvalidDataException
	{
		System.out.println("stockId : "+stockId);
		List<StockData> data = null;
		
		if(!StringUtils.isEmpty(stockId))
			data = service.getStockById(stockId);
		else
			throw new InvalidDataException("Please try with valid data"); 
		
		return data;
	}
	
	@PostMapping("/addStocks")
	public String addStocks(@RequestBody List<StockData> stocks) throws InvalidDataException{
		
		String response = null;
		
		if(stocks.size() > 0)
		{
			response = service.addStocks(stocks);
		}
		else
			throw new InvalidDataException("Please try with valid data");
		
		return response;
	}
	
	@PostMapping("/uploadBulkData")
	public String uploadBulkData(@RequestParam("file") MultipartFile dataFile) throws IOException
	{
		return service.uploadBulkData(dataFile);
	}
}
