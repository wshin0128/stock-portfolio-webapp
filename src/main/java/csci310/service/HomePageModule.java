package csci310.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import csci310.model.Portfolio;
import csci310.model.Stock;

/**
 * Home page back end module to handle the home page functionalities
 * @author chunyangmou
 *
 */
public class HomePageModule {
	private Portfolio portfolio;
	
	public HomePageModule(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	
	/**
	 * Get portfolio value change compare to  yesterday
	 * @return ex. 0.01 (today's stock price increased 1 percent compared with yesterday
	 */
    public Double getChangePercentDouble() {
    	List<Stock> stockList = portfolio.getPortfolio();
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
		return changePercentageDouble;
    }
}
