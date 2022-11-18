package com.example.stock.market.loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.stock.market.entity.StockData;
import com.example.stock.market.repository.StockDataRepository;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Component
public class StockDataLoader implements CommandLineRunner {
	
	@Autowired
	StockDataRepository repo;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Data Loading to H2 Database");
		
		List<StockData> dataList = new ArrayList<>();
		Resource resource = new ClassPathResource("StockData.csv");
		
		InputStream inputData = resource.getInputStream();
		
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
		
		System.out.println("Data successfully uploaded to H2 DB");
		
	}
	
}
