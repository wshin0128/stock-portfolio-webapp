package csci310.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import org.junit.Test;



public class FinnhubClientTest {

	@Test
	public void testGetStockPrice() {
		FinnhubClient finnhubClient = new FinnhubClient();
		Map<Date, Double> resultDailyMap = null;
		try {
			resultDailyMap = finnhubClient.getStockPrice("AAPL", Resolution.Daily, 1572566400, 1601942400);
			Map<Date, Double> resultWeeklyMap = finnhubClient.getStockPrice("AAPL", Resolution.Weekly, 1572566400, 1601942400);
			Map<Date, Double> resultMonthlyMap = finnhubClient.getStockPrice("AAPL", Resolution.Monthly, 1572566400, 1601942400);
			
			assertNotNull(resultDailyMap.keySet());
			assertNotNull(resultWeeklyMap.keySet());
			assertNotNull(resultMonthlyMap.keySet());
		} catch (Exception e) {
			assertNull(resultDailyMap);
		}
		
	}

}
