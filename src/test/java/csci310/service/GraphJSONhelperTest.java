package csci310.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphJSONhelperTest {

	@Test
	public void testStockGraphInfo() {
		GraphJSONhelper G = new GraphJSONhelper();
		assertTrue(G.StockGraphInfo("AAPL", Resolution.Weekly, 1572566400, 1601942400).equals(""));
	}

}
