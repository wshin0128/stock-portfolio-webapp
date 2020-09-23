package csci310.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;

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
	protected static String DAILY_FUNCTION = "TIME_SERIES_DAILY";
	
	@VisibleForTesting
	protected static String WEEKLY_FUNCTION = "TIME_SERIES_WEEKLY_ADJUSTED";
	
	@VisibleForTesting
	protected static String MONTHLY_FUNCTION = "TIME_SERIES_MONTHLY_ADJUSTED";
	
    public static Map<String, Object> getDailyValue(String stockSymbol) throws IOException{
    	String urlString = String.format(URL_FORMAT, DAILY_FUNCTION, stockSymbol, API_KEY);
		
		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);
		
		System.out.println(jsonObject);
		
		JSONObject dailyValueJsonObject = jsonObject.getJSONObject("Time Series (Daily)");
	
		Map<String, Object> result = parseJsonObject(dailyValueJsonObject);
		
		return result;
    }
    
    public static Map<String, Object> getWeeklyValue(String stockSymbol) throws IOException{
    	String urlString = String.format(URL_FORMAT, WEEKLY_FUNCTION, stockSymbol, API_KEY);
		
		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);
        
		JSONObject weeklyValueJsonObject = jsonObject.getJSONObject("Weekly Adjusted Time Series");
	
		Map<String, Object> result = parseJsonObject(weeklyValueJsonObject);
		
		return result;
    }
    
    public static Map<String, Object> getMonthlyValue(String stockSymbol) throws IOException{
    	String urlString = String.format(URL_FORMAT, MONTHLY_FUNCTION, stockSymbol, API_KEY);
		
		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);
        
		JSONObject monthlyValueJsonObject = jsonObject.getJSONObject("Monthly Adjusted Time Series");
	
		Map<String, Object> result = parseJsonObject(monthlyValueJsonObject);
		
		return result;
    }
    
    @SuppressWarnings({ "unchecked"})
	public static Map<String, Object> parseJsonObject(JSONObject jsonObject) throws IOException{
    	return new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
    }
    
//    public static void main(String[] args) {
//    	try {
//			Map<String, Object> myMap = getDailyValue("IBM");
//			System.out.println(myMap);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
}
