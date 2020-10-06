package csci310.service;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;

enum TimeBasis { Daily, Weekly, Monthly}; 

/**
 * The service client wrapper of Alpha Advantage API for fetching stock info
 * https://app.asana.com/0/1194247499119601/1194513700590696
 * 
 * Use a
 * @author Chunyang Mou
 */
public class AlphaVantageClient {

	@VisibleForTesting
	protected static final List<String> API_KEY_POOL = ImmutableList.of("RR16KZC1DD427U5S", "VO06N33HTL63LQAM", "OQO8N892D2N38P6E", "WJMTZJMKGGZ9MBJG");
	
	private static int indexOfPool = 0;

	@VisibleForTesting
	protected static String URL_FORMAT = "https://www.alphavantage.co/query?function=%s&symbol=%s&apikey=%s";

	@VisibleForTesting
	protected static String DAILY_FUNCTION = "TIME_SERIES_DAILY";

	@VisibleForTesting
	protected static String WEEKLY_FUNCTION = "TIME_SERIES_WEEKLY_ADJUSTED";

	@VisibleForTesting
	protected static String MONTHLY_FUNCTION = "TIME_SERIES_MONTHLY_ADJUSTED";
	
	
	
	public static Map<Date, Double> getStockPrice(String stockSymbol, TimeBasis timeBasis){
//		Map<String, Object> resultMap;
//		switch (timeBasis) {
//		case TimeBasis.Daily:
//			resultMap = getDailyValue(stockSymbol)
//			break;
//
//		default:
//			break;
//		}
		return null;
	}
	

	public static Map<String, Object> getDailyValue(String stockSymbol) throws IOException{
		String urlString = String.format(URL_FORMAT, DAILY_FUNCTION, stockSymbol, chooseAnAPIKey());

		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);

		JSONObject dailyValueJsonObject = jsonObject.getJSONObject("Time Series (Daily)");

		Map<String, Object> result = parseJsonObject(dailyValueJsonObject);

		return result;
	}

	public static Map<String, Object> getWeeklyValue(String stockSymbol) throws IOException{
		String urlString = String.format(URL_FORMAT, WEEKLY_FUNCTION, stockSymbol, chooseAnAPIKey());

		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);

		JSONObject weeklyValueJsonObject = jsonObject.getJSONObject("Weekly Adjusted Time Series");

		Map<String, Object> result = parseJsonObject(weeklyValueJsonObject);

		return result;
	}

	public static Map<String, Object> getMonthlyValue(String stockSymbol) throws IOException{
		String urlString = String.format(URL_FORMAT, MONTHLY_FUNCTION, stockSymbol, chooseAnAPIKey());

		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);

		JSONObject monthlyValueJsonObject = jsonObject.getJSONObject("Monthly Adjusted Time Series");

		Map<String, Object> result = parseJsonObject(monthlyValueJsonObject);

		return result;
	}
	
	private static final String chooseAnAPIKey() {
        indexOfPool = (indexOfPool + 1) % API_KEY_POOL.size();
        return API_KEY_POOL.get(indexOfPool);
	}

	@SuppressWarnings({ "unchecked"})
	public static Map<String, Object> parseJsonObject(JSONObject jsonObject) throws IOException{
		return new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
	}
}


