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
import csci310.model.User;

/**
 * Home page back end module to handle the home page functionalities
 * @author chunyangmou
 *
 */
public class HomePageModule {
	private User user;
	
	double portfolioValue = 0;
	
	public Double todayTotalDouble;
	private final FinnhubClient finnhubClient;
	private final DatabaseClient databaseClient;
	
	private Portfolio viewedStockPortfolio;
	
	public HomePageModule(User user, FinnhubClient finnhubClient, DatabaseClient databaseClient) {
		this.user = user;
		this.finnhubClient = finnhubClient;
		this.databaseClient = databaseClient;
		
		// Initialize viewed stocks when module is created
		viewedStockPortfolio = databaseClient.getViewedStocks(user.getUserID());
	}
	
	/**
	 * Get portfolio value change compare to  yesterday
	 * @return ex. 0.01 (today's stock price increased 1 percent compared with yesterday
	 */
    public Double getChangePercentDouble() {
    	Portfolio portfolio = user.getPortfolio();
    	List<Stock> stockList = portfolio.getPortfolio();
    	
		todayTotalDouble = 0.0;
		Double yesterdayTotalDouble = 0.0;
		for (Stock stock : stockList) {
			Calendar cal = Calendar.getInstance();
			long currentTime = cal.getTimeInMillis() / 1000;

			cal.add(Calendar.DAY_OF_YEAR, -7);
			long yesterdayTime = cal.getTimeInMillis() / 1000;
			
            if (yesterdayTime > stock.getSellDate()) {
            	// if the stock is sold before yesterday, do not calculate that
            	continue;
            }
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

				todayTotalDouble += todayPrice * stock.getQuantity();
				yesterdayTotalDouble += yesterdayPrice * stock.getQuantity();

			} catch (Exception e) {
				// Could not fetch the info of this stock, just pass
				// e.printStackTrace();
				System.out.println("Home Page Module: Could not fetch price of stock symbol: " + stock.getTicker());			
			}
		}
		// Calculate change percentage
		Double diffDouble = todayTotalDouble - yesterdayTotalDouble;
		portfolioValue = todayTotalDouble;
		Double changePercentageDouble = diffDouble / yesterdayTotalDouble;
		return changePercentageDouble;
    }
    
    public void addStock(Stock stock) {
    	// add stock to portfolio in homepage module
    	user.getPortfolio().getPortfolio().add(stock);
    }
    
    
    public void removeStock(String tickerString) {
    	// remove stock portfolio in page module
    	// remove stock from database.
    	
    	// deal with the case where stock is not already in portfolio_info
    	
    }
    
    public void addViewedStock(Stock stock) {
    	viewedStockPortfolio.addStock(stock);
    }
    
    public ArrayList<Stock> getStockList(){
    	return user.getPortfolio().getPortfolio();
    }
    
    public ArrayList<Stock> getViewedStockList(){
    	return viewedStockPortfolio.getPortfolio();
    }
    

    
    public double getPortfolioValue() {
		return portfolioValue;
    }
}
