package csci310.service;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;

import csci310.model.stock.StockInfo;

/**
 * The service client wrapper of Alpha Advantage API for fetching stock info
 * https://app.asana.com/0/1194247499119601/1194513700590696
 * 
 * Use a
 * @author Chunyang Mou
 */
public class AlphaVantageClient {
	
	@VisibleForTesting
	protected static String API_KEY = "RR16KZC1DD427U5S";
	
	@VisibleForTesting
	protected static String URL_FORMAT = "https://www.alphavantage.co/query?function=%s&symbol=%s&apikey=%s";
	
	@VisibleForTesting
	protected static String DAILY_FUNCTION = "TIME_SERIES_DAILY_ADJUSTED";
	
	@VisibleForTesting
	protected static String WEEKLY_FUNCTION = "TIME_SERIES_WEEKLY_ADJUSTED";
	
	@VisibleForTesting
	protected static String MONTHLY_FUNCTION = "TIME_SERIES_MONTHLY_ADJUSTED";
	
    public static Map<Date, StockInfo> getDailyValue(String stockSymbol){
		return null;
    }
    
    public static Map<Date, StockInfo> getWeeklyValue(String stockSymbol){
		return null;
    }
    
    public static Map<Date, StockInfo> getMonthlyValue(String stockSymbol){
		return null;
    }
    
    //@SuppressWarnings({ "unchecked", "unused" })
	public static Map<String, StockInfo> parseJsonObject(JSONObject jsonObject) throws IOException{
//    	return new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
    	return null;
    }
}
