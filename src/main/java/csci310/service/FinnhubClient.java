package csci310.service;

import java.sql.Time;
import java.util.Map;


enum Resolution { Daily, Weekly, Monthly}; 

public class FinnhubClient {
	
	protected static String URL_FORMAT = "https://finnhub.io/api/v1/stock/candle?symbol=%s&resolution=%s&from=%d&to=%d&token=%s";
	
	private static String API_KEY = "btug69748v6vqmm3nn10";
	
	public static Map<Time, Double> getStockPrice(String symbol, Resolution resolution, long startTime, long endTime) {
		return null;
	}
}
