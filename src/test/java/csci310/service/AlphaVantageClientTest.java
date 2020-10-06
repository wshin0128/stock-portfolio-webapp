package csci310.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
import org.junit.Test;

import io.cucumber.java.lu.dann;


public class AlphaVantageClientTest {
	
	private final List<String> TEST_API_KEY_POOL = AlphaVantageClient.API_KEY_POOL;
	private final String TEST_URL_FORMAT = AlphaVantageClient.URL_FORMAT;
	
	private final String TEST_DAILY_FUNCTION = AlphaVantageClient.DAILY_FUNCTION;
	private final String TEST_WEEKLY_FUNCTION = AlphaVantageClient.WEEKLY_FUNCTION;
	private final String TEST_MONTHLY_FUNCTION = AlphaVantageClient.MONTHLY_FUNCTION;
	
	private final String TEST_STOCK_SYMBOL = "IBM";
	
	@Test
	public void testGetStockPrice(){
		Map<Date, Double> resultMap1 = AlphaVantageClient.getStockPrice(TimeBasis.Daily); 
		Map<Date, Double> resultMap2 = AlphaVantageClient.getStockPrice(TimeBasis.Weekly); 
		Map<Date, Double> resultMap3 = AlphaVantageClient.getStockPrice(TimeBasis.Monthly);
		
		assertNotNull(resultMap1);
		assertNotNull(resultMap2);
		assertNotNull(resultMap3);
	}
	
	@Test
	public void testGetDailyValue() throws IOException {
		String urlString = String.format(TEST_URL_FORMAT, TEST_DAILY_FUNCTION, TEST_STOCK_SYMBOL, chooseARandomAPI());
		
		JSONObject jsonObject = JsonReader.readJsonFromUrl(urlString);
		
		JSONObject dailyValueJsonObject = jsonObject.getJSONObject("Time Series (Daily)");
	
		Map<String, Object> result = AlphaVantageClient.parseJsonObject(dailyValueJsonObject);
		
		assertEquals(result, AlphaVantageClient.getDailyValue(TEST_STOCK_SYMBOL));
		
	}

	@Test
	public void testGetWeeklyValue() throws IOException {
		assertNotNull(AlphaVantageClient.getWeeklyValue(TEST_STOCK_SYMBOL).keySet());
	}

	@Test
	public void testGetMonthlyValue() throws IOException {
		assertNotNull(AlphaVantageClient.getMonthlyValue(TEST_STOCK_SYMBOL).keySet());
	}
	
	private String chooseARandomAPI() {
		int rnd = new Random().nextInt(TEST_API_KEY_POOL.size());
        return TEST_API_KEY_POOL.get(rnd);
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
