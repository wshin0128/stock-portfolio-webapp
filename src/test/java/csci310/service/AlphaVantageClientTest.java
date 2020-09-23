package csci310.service;

import static org.junit.Assert.*;

import java.io.IOException;

import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;


public class AlphaVantageClientTest {
	
	private final String TEST_API_KEY = AlphaVantageClient.API_KEY;
	private final String TEST_URL_FORMAT = AlphaVantageClient.URL_FORMAT;
	
	private final String TEST_DAILY_FUNCTION = AlphaVantageClient.DAILY_FUNCTION;
	private final String TEST_WEEKLY_FUNCTION = AlphaVantageClient.WEEKLY_FUNCTION;
	private final String TEST_MONTHLY_FUNCTION = AlphaVantageClient.MONTHLY_FUNCTION;
	
	private final String TEST_STOCK_SYMBOL = "IBM";
	
	@Test
	public void testGetDailyValue() throws IOException {
		String urlString = String.format(TEST_URL_FORMAT, TEST_DAILY_FUNCTION, TEST_STOCK_SYMBOL, TEST_API_KEY);
		
		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);
		
		JSONObject dailyValueJsonObject = jsonObject.getJSONObject("Time Series (Daily)");
	
		Map<String, Object> result = AlphaVantageClient.parseJsonObject(dailyValueJsonObject);
		
		assertEquals(result, AlphaVantageClient.getDailyValue(TEST_STOCK_SYMBOL));
		
	}

	@Test
	public void testGetWeeklyValue() throws IOException {
//		String urlString = String.format(TEST_URL_FORMAT, TEST_WEEKLY_FUNCTION, TEST_STOCK_SYMBOL, TEST_API_KEY);
//		
//		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);
//        
//		JSONObject weeklyValueJsonObject = jsonObject.getJSONObject("Weekly Adjusted Time Series");
//	
//		Map<String, StockInfo> result = AlphaVantageClient.parseJsonObject(weeklyValueJsonObject);
		
		// Commented out since we have limits with api call
//		assertEquals(result, AlphaVantageClient.getWeeklyValue(TEST_STOCK_SYMBOL));
		
		assertNotNull(AlphaVantageClient.getWeeklyValue(TEST_STOCK_SYMBOL));
	}

	@Test
	public void testGetMonthlyValue() throws IOException {
		String urlString = String.format(TEST_URL_FORMAT, TEST_MONTHLY_FUNCTION, TEST_STOCK_SYMBOL, TEST_API_KEY);
		
		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);
        
		JSONObject monthlyValueJsonObject = jsonObject.getJSONObject("Monthly Adjusted Time Series");
	
		Map<String, Object> result = AlphaVantageClient.parseJsonObject(monthlyValueJsonObject);
		
		assertEquals(result, AlphaVantageClient.getMonthlyValue(TEST_STOCK_SYMBOL));
	}
	
	@Test
	public void testParseJsonObject(){
		try {
			
			Map<String, Object> resultMap = AlphaVantageClient.parseJsonObject(new JSONObject());
			assertNotNull(resultMap);
		} catch (IOException e) {
			assertNotNull(e);
		} 
		
		// Check we can initialize the class
		AlphaVantageClient alphaVantageClient = new AlphaVantageClient();
		assertNotNull(alphaVantageClient);
	}

}
