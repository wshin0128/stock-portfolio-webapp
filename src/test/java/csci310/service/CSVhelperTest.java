package csci310.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import csci310.model.Stock;

public class CSVhelperTest extends Mockito {

	private static CSVhelper csv;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		csv = new CSVhelper();
	}

	@Test
	public void testGetIOException() {
		assertFalse(csv.getIOException());
	}

	@Test
	public void testSetIOException() {
		CSVhelper csvException = new CSVhelper();
		assertFalse(csvException.getIOException());
		csvException.setIOException();
		assertTrue(csvException.getIOException());
	}

	@Test
	public void testValidateStock() {
		// correctly formated file
		String msg = csv.validateStock("stockTests/stock1.csv");
		assertEquals(msg, "No Error Found.");

		// wrong formated file
		msg = csv.validateStock("stockTests/stock2.csv");
		String expected = 
				"Row 1: requires minimum of 5 parameters.\n" + 
						"Row 2, Col A: stock name is a required field.\n" + 
						"Row 2, Col B: stock ticker is a required field.\n" + 
						"Row 2, Col C: stock quantity must be a Integer.\n" + 
						"Row 2, Col D: stock buy date must be a Long.\n" + 
						"Row 3, Col A: stock name is a required field.\n" + 
						"Row 3, Col B: stock ticker is a required field.\n" + 
						"Row 4, Col C: stock quantity must be a Integer.\n" + 
						"Row 4, Col D: stock buy date must be a Long.\n" + 
						"Row 4, Col E: stock sell date must be a Long.\n" + 
						"Row 5, Col C: stock quantity must be a Integer.\n" + 
						"Row 6, Col D: stock buy date must be a Long.\n" + 
						"Row 6, Col E: stock sell date must be a Long.\n";
		assertEquals(msg, expected);

		// not found exception
		msg = csv.validateStock("NotFound.csv");
		assertEquals(msg, "FileNotFoundException");

		// IO exception
		CSVhelper csvException = new CSVhelper();
		csvException.setIOException();
		msg = csvException.validateStock("stockTests/stock1.csv");
		assertEquals(msg, "IOException");
	}

	@Test
	public void testParseCSV() {		
		// feed valid file
		ArrayList<Stock> stocks = csv.parseCSV("stockTests/stock1.csv");
		// match result from CSV
		for(int i = 0; i < 3; i++) {
			Stock stock = stocks.get(i);
			if(i == 0) {
				assertEquals(stock.getName(), "Microsoft");
				assertEquals(stock.getTicker(), "MSFT");
				assertEquals(stock.getQuantity(), 123);
				assertEquals(stock.getBuyDate(), 1599027025);
				assertEquals(stock.getSellDate(), 1601619025);
			}
			else if(i == 1) {
				assertEquals(stock.getName(), "Tesla");
				assertEquals(stock.getTicker(), "TSLA");
				assertEquals(stock.getQuantity(), 456);
				assertEquals(stock.getBuyDate(), 1599027026);
				assertEquals(stock.getSellDate(), 1601619026);
			}
			else {
				assertEquals(stock.getName(), "Apple");
				assertEquals(stock.getTicker(), "AAPL");
				assertEquals(stock.getQuantity(), 789);
				assertEquals(stock.getBuyDate(), 1599027027);
				assertEquals(stock.getSellDate(), 1601619027);
			}
		}
		
		// not found exception
		stocks = csv.parseCSV("NotFound.csv");
		assertEquals(stocks, null);

		// IO exception
		CSVhelper csvException = new CSVhelper();
		csvException.setIOException();
		stocks = csvException.parseCSV("stockTests/stock1.csv");
		assertEquals(stocks, null);
	}

}
