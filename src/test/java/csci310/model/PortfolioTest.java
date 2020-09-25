package csci310.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PortfolioTest {
	
	private Portfolio p;

	@Before
	public void portfolioSetup() {
		p = new Portfolio();
	}
	
	@Test
	public void testPortfolio() {
		Portfolio p = new Portfolio();
		ArrayList<String> testArrayList = p.getArrayList();
		assertTrue("Portfolio ArrayList was not initialized",testArrayList != null);
	}
	
	@Test
	public void testGetArrayList() {
		String stock = "stock";
		ArrayList<String> testArrayList = new ArrayList<String>();
		testArrayList.add(stock);
		p.addStock(stock);
		ArrayList<String> comparisonArrayList = p.getArrayList();
		assertTrue("Incorrect ArrayList was returned",comparisonArrayList.equals(testArrayList));
	}
	
	@Test
	public void testGetSize() {
		assertTrue("Portfolio size incorrect", p.getSize() == 0);
		p.addStock("Test");
		assertTrue("Portfolio size does not increase correctly", p.getSize() == 1);
	}
	
	@Test
	public void testAddStock() {
		String stock = "stock";
		p.addStock(stock);
		assertTrue("Stock was not added to portfolio", p.getSize() == 1);
	}
	
	@Test
	public void testRemoveStock() {
		String stock = "stock";
		p.addStock(stock);
		assertTrue("Stock was not added to portfolio", p.getSize() == 1);
		p.removeStock(stock);
		assertTrue("Stock was not removed to portfolio", p.getSize() == 0);
	}
	
	@Test
	public void testResetPortfolio() {
		p.addStock("Stock1");
		p.addStock("Stock2");
		p.addStock("Stock3");
		p.resetPortfolio();
		assertTrue("Portfolio was not reset correctly", p.getSize() == 0);
	}
	
	@Test
	public void testContains() {
		String stock = "stock";
		assertFalse("Contains is triggering a false positive", p.contains(stock));
		p.addStock(stock);
		assertTrue("Contains is triggering a false negative", p.contains(stock));
	}

}
