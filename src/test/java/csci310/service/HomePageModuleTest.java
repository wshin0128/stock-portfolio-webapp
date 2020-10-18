package csci310.service;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import csci310.model.Portfolio;
import csci310.model.Stock;

import org.mockito.Mockito;



public class HomePageModuleTest extends Mockito{
	

	private static final Stock TEST_STOCK_1 = new Stock("Apple Inc", "AAPL", 21, 946368000, 1609142400);
	private static final Stock TEST_STOCK_2 = new Stock("Some dummy value", "some dummy value", 0, 100, 100);
	private static List<Stock> stockList;
	
	@Mock
	private static Portfolio mockPortfolio;
	
	private static HomePageModule homePageModule;
	
	@BeforeClass 
	public static void setUp() {
		homePageModule = new HomePageModule(mockPortfolio);
		mockPortfolio = mock(Portfolio.class);
		stockList = new ArrayList<>();
		stockList.add(TEST_STOCK_1);
		stockList.add(TEST_STOCK_2);
	}
	
	
	@Test
	public void testHomePageModule() {
		@SuppressWarnings("unused")
		HomePageModule homePageModuleTemp = new HomePageModule(mockPortfolio);
	}

	@Test
	public void testGetChangePercentDouble() {
		when(mockPortfolio.getPortfolio()).thenReturn((ArrayList<Stock>) stockList);
		FinnhubClient finnhubClient = new FinnhubClient();
		Double todayTotalDouble = 0.0;
		Double yesterdayTotalDouble = 0.0;
		for (Stock stock : stockList) {
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
				// Could not fetch the info of this stock, just pass
				// e.printStackTrace();
				System.out.println("Could not fetch price of stock symbol: " + stock.getTicker());			
			}
		}
		// Calculate change percentage
		Double diffDouble = todayTotalDouble - yesterdayTotalDouble;
		Double changePercentageDouble = diffDouble / yesterdayTotalDouble;
		assertEquals(changePercentageDouble, homePageModule.getChangePercentDouble());
	}

}
