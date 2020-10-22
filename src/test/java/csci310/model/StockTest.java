package csci310.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockTest {

	// Test Stock constructor
	@Test
	public void testStock() {
		Stock s = new Stock("Apple Inc", "AAPL", null, 21, 946368000, 1609142400);
		assertTrue("Stock names do not match", s.getName() == "Apple Inc");
		assertTrue("Stock tickers do not match", s.getTicker() == "AAPL");
		assertTrue("Stock quantities do not match", s.getQuantity() == 21);
		assertTrue("Stock buy dates do not match", s.getBuyDate() == 946368000);
		assertTrue("Stock sell dates do not match", s.getSellDate() == 1609142400);
	}
	
	// Test modifying stocks
	@Test
	public void testSetters() {
		Stock s = new Stock("Apple Inc", "AAPL", null, 21, 946368000, 1609142400);
		s.setName("Microsoft");
		s.setTicker("MSFT");
		s.setColor("#000000");
		s.setQuantity(22);
		s.setBuyDate(946368001);
		s.setSellDate(1609142401);
		
		assertTrue("Stock names do not match", s.getName() == "Microsoft");
		assertTrue("Stock tickers do not match", s.getTicker() == "MSFT");
		assertTrue("Stock quantities do not match", s.getQuantity() == 22);
		assertTrue("Stock buy dates do not match", s.getBuyDate() == 946368001);
		assertTrue("Stock sell dates do not match", s.getSellDate() == 1609142401);
	}
	
	// Test hexColorGenerator()
	@Test
	public void testAssignHexColor () {
		Stock s = new Stock("Apple Inc", "AAPL", null, 21, 946368000, 1609142400);
		String color = s.assignHexColor();
		assertTrue("Stock colors do not match", color == s.getColor());
	}
	
	// Test equals
	@Test
	public void testEquals() {
		Stock s1 = new Stock("Apple Inc", "AAPL", "#000000", 14, 1, 2);
		Stock s2 = new Stock("Apple Inc", "AAPL", "#000000", 14, 1, 2);
		assertTrue(s1.equals(s2));
		
	}
}
