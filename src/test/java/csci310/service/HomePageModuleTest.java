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
import io.cucumber.java.en.Then;

import org.mockito.Mockito;



public class HomePageModuleTest extends Mockito{
	

	private static final Stock TEST_STOCK_1 = new Stock("Apple Inc", "AAPL", null, 21, 946368000, 1609142400);
	private static final Stock TEST_STOCK_2 = new Stock("Some dummy value", "some dummy value", null, 0, 100, 100);
	private static final Stock TEST_STOCK_3 = new Stock("Some dummy value", "some dummy value", null, 1, 100, 100);
	
	private static Portfolio portfolio;
	
	private static User user;
	
	private static HomePageModule homePageModule;
	private static DatabaseClient databaseClient;
	
	@BeforeClass 
	public static void setUp() throws SQLException {
		portfolio = new Portfolio();
		portfolio.addStock(TEST_STOCK_1);
		portfolio.addStock(TEST_STOCK_2);
		
		user = new User("jdoe",1);
		user.setPortfolio(portfolio);
		homePageModule = new HomePageModule(user, new FinnhubClient(), new DatabaseClient());
		
		try {
			databaseClient = new DatabaseClient();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testHomePageModule() throws SQLException {
		@SuppressWarnings("unused")
		HomePageModule homePageModuleTemp = new HomePageModule(user, new FinnhubClient(), new DatabaseClient());
	}

	@Test
	public void testGetChangePercentDouble() throws Exception {
		FinnhubClient finnhubClient = new FinnhubClient();
		Double todayTotalDouble = 0.0;
		Double yesterdayTotalDouble = 0.0;
		for (Stock stock : portfolio.getPortfolio()) {
			Calendar cal = Calendar.getInstance();
			long currentTime = cal.getTimeInMillis() / 1000;
			cal.add(Calendar.DAY_OF_YEAR, -7);
			long lastWeekTime = cal.getTimeInMillis() / 1000;
			try {
				// Get the whole week's data 
				Map<Date, Double> priceMap = finnhubClient.getStockPrice(stock.getTicker(),Resolution.Daily,lastWeekTime, currentTime);
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
				todayTotalDouble += todayPrice * stock.getQuantity();
				yesterdayTotalDouble += yesterdayPrice * stock.getQuantity();
			} catch (Exception e) {	
				
			}
		}
		// Calculate change percentage
		Double diffDouble = todayTotalDouble - yesterdayTotalDouble;
		Double changePercentageDouble = diffDouble / yesterdayTotalDouble;
		if (homePageModule.getChangePercentDouble() != null) assertEquals(changePercentageDouble, homePageModule.getChangePercentDouble());
		
		// mock exception 
		FinnhubClient mockFinnhubClient = mock(FinnhubClient.class);
		when(mockFinnhubClient.getStockPrice(anyString(), any(Resolution.class), anyLong(), anyLong()))
			.thenThrow(new Exception ());
		HomePageModule homePageModule2 = new HomePageModule(user, mockFinnhubClient, new DatabaseClient());
		
		assertNotNull(homePageModule2.getChangePercentDouble());
	}
	
	@Test
	public void testAddStock() {
		homePageModule.addStock(TEST_STOCK_2);
		homePageModule.removeStock(TEST_STOCK_2.getTicker());
	}

	@Test
	public void testRemoveStock() {
		homePageModule.addStock(TEST_STOCK_1);
		assertTrue(portfolio.contains(TEST_STOCK_1));
		homePageModule.removeStock("AAPL");
	}
	
	@Test
	public void testRemoveViewedStock() {
		homePageModule.removeViewedStock("AAPL");
		//homePageModule.addViewedStock(stock);
	}
	
	@Test
	public void testGetStockList() {
		assertEquals(portfolio.getSize(), homePageModule.getStockList().size());
	}
	
	@Test
	public void testGetPortfolioValue() {
		assertNotNull(homePageModule.getPortfolioValue());
	}
	
	@Test
	public void testGetViewedStockList() {
		assertNotNull(homePageModule.getViewedStockList());
	}
	
	@Test
	public void testAddViewedStockList() {
		homePageModule.addViewedStock(TEST_STOCK_1);
		homePageModule.removeViewedStock(TEST_STOCK_1.getTicker());
	}
	
	@Test
	public void testSetPortfolio() {
		homePageModule.setPortfolio(portfolio);
	}

}
