package csci310.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import csci310.model.Portfolio;
import csci310.model.Stock;
import csci310.model.User;

import org.mockito.Mockito;



public class HomePageModuleTest extends Mockito{
	

	private static final Stock TEST_STOCK_1 = new Stock("Apple Inc", "AAPL", 21, 946368000, 1609142400);
	private static final Stock TEST_STOCK_2 = new Stock("Some dummy value", "some dummy value", 0, 100, 100);
	
	private static Portfolio portfolio;
	
	private static User user;
	
	private static HomePageModule homePageModule;
	private static DatabaseClient databaseClient;
	
	@BeforeClass 
	public static void setUp() {
		portfolio = new Portfolio();
		portfolio.addStock(TEST_STOCK_1);
		
		user = new User("jdoe",1);
		user.setPortfolio(portfolio);
		homePageModule = new HomePageModule(user);
		
		try {
			databaseClient = new DatabaseClient();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testHomePageModule() {
		@SuppressWarnings("unused")
		HomePageModule homePageModuleTemp = new HomePageModule(user);
	}

	@Test
	public void testGetChangePercentDouble() {
		FinnhubClient finnhubClient = new FinnhubClient();
		Double todayTotalDouble = 0.0;
		Double yesterdayTotalDouble = 0.0;
		for (Stock stock : portfolio.getPortfolio()) {
			Calendar cal = Calendar.getInstance();
			long currentTime = cal.getTimeInMillis() / 1000;
			cal.add(Calendar.DAY_OF_YEAR, -7);
			long yesterdayTime = cal.getTimeInMillis() / 1000;
			try {
				// Get the whole week's data 
				Map<Date, Double> priceMap = finnhubClient.getStockPrice(stock.getTicker(),Resolution.Daily,yesterdayTime, currentTime);
				// System.out.println(priceMap);
				
				// Fetch the most recent two dates, and their prices
				Set<Date> dates = priceMap.keySet();
				int n = dates.size(); 
			    List<Date> aList = new ArrayList<Date>(n); 
			    for (Date x : dates) 
			        aList.add(x); 
				Collections.sort(aList, Collections.reverseOrder());
				
				Date todayDate = aList.get(0);
				Date yesterdayDate = aList.get(1);
				Double todayPrice = priceMap.get(todayDate);
				Double yesterdayPrice = priceMap.get(yesterdayDate);
				
				// add to total
				todayTotalDouble += todayPrice;
				yesterdayTotalDouble += yesterdayPrice;
			} catch (Exception e) {			
			}
		}
		// Calculate change percentage
		Double diffDouble = todayTotalDouble - yesterdayTotalDouble;
		Double changePercentageDouble = diffDouble / yesterdayTotalDouble;
		assertEquals(changePercentageDouble, homePageModule.getChangePercentDouble());
	}
	
	@Test
	public void testAddStock() {
		// assertFalse(portfolio.contains(TEST_STOCK_2));
		homePageModule.addStock(TEST_STOCK_2);
		// assertTrue(portfolio.contains(TEST_STOCK_2));
		
		// test updates to database
	}
	
	@Test
	public void testRemoveStock() {
		// assertTrue(portfolio.contains(TEST_STOCK_1));
		homePageModule.removeStock("AAPL");
		// assertFalse(portfolio.contains(TEST_STOCK_1));
		
		// test updates to database
	}

}
