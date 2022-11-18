package com.example.stock.market.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.stock.market.entity.StockData;
import com.example.stock.market.repository.StockDataRepository;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Component
public class StockDataService {

	@Autowired
	StockDataRepository repo;
	
	public List<StockData> getAllStocks()
	{
		return repo.findAll();
	}
	
	public List<StockData> getStockById(String stockId)
	{
		return repo.findByStock(stockId);
	}
	
	public String addStocks(List<StockData> stocks)
	{
		String response = null;
		try {
			repo.saveAll(stocks);
			response = "Stocks have been added successfully !!";
		}
		catch (Exception e) {
			response = "Exception encountered while adding stocks";
			System.out.println("Exception is : "+e.getMessage());
		}
		
		return response;
		
	}
	
	public String uploadBulkData(MultipartFile dataFile) throws IOException
	{
		List<StockData> dataList = new ArrayList<>();
		
		InputStream inputData = dataFile.getInputStream();
		
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		List<String[]> parseAll = parser.parseAll(inputData);
		
		parseAll.forEach(str -> {
			StockData data = new StockData();
			data.setQuarter(str[0]);
			data.setStock(str[1]);
			data.setDate(str[2]);
			data.setOpen(str[3]);
			data.setHigh(str[4]);
			data.setLow(str[5]);
			data.setClose(str[6]);
			data.setVolume(str[7]);
			data.setPercent_change_price(str[8]);
			data.setPercent_change_volume_over_last_wk(str[9]);
			data.setPrevious_weeks_volume(str[10]);
			data.setNext_weeks_open(str[11]);
			data.setNext_weeks_close(str[12]);
			data.setPercent_change_next_weeks_price(str[13]);
			data.setDays_to_next_dividend(str[14]);
			data.setPercent_return_next_dividend(str[15]);
			
			dataList.add(data);
		});
		
		repo.saveAll(dataList);
		
		return "Bulk Data Upload Operation Successful !!";
	}
}
