package csci310.service;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.Map;

import org.junit.Test;

public class FinnhubClientTest {

	@Test
	public void testGetStockPrice() {
		Map<Time, Double> resultMap = FinnhubClient.getStockPrice("AAPL", Resolution.Daily, 1572566400, 1572566400);
		assertNotNull(resultMap.keySet());
	}

}
