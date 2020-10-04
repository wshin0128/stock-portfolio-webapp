package csci310.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockTest {

	// Test Stock constructor
	@Test
	public void testStock() {
		Stock s = new Stock("Apple Inc", "AAPL", 21, "1999-12-28", "2020-12-28");
		assertTrue("Stock names do not match", s.getName() == "Apple Inc");
		assertTrue("Stock tickers do not match", s.getTicker() == "AAPL");
		assertTrue("Stock quantities do not match", s.getQuantity() == 21);
		assertTrue("Stock buy dates do not match", s.getBuyDate() == "1999-12-28");
		assertTrue("Stock sell dates do not match", s.getSellDate() == "2020-12-28");
	}
	
	// Test hexColorGenerator()
	@Test
	public void testAssignHexColor () {
		Stock s = new Stock("Apple Inc", "AAPL", 21, "1999-12-28", "2020-12-28");
		String color = s.assignHexColor();
		assertTrue("Stock colors do not match", color == s.getColor());
	}
}
