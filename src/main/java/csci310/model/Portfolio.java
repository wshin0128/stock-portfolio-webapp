package csci310.model;

import java.util.ArrayList;

/*
*Current rendition of portfolio class that will be utilized within our webapp
*Every user would have a stock portfolio, this class provides definition of what would be in every user's portfolio
*/
public class Portfolio {
	//Arraylist that is considered the "portfolio" in this rendition of our webapp
	//Currently ever stock in the portfolio is considered a string, thus a portfolio is an arraylist of strings
	//TODO: See if making a a stock object to signify a stock(which would make portfolio become a map with string as the key)
	//would be more useful implementation of the portfolio/stock object 
	private ArrayList<String> portfolio;
	
	//Only constructor of the portfolio, creates an empty portfolio.
	//Only 1 constructor is needed ATM portfolio is to be created upon user creation
	public Portfolio() {
		portfolio = new ArrayList<String>();
	}
	//Getter for the arraylist portfolio
	public ArrayList<String> getArrayList() {
		return portfolio;
	}
	
	//Returns the size of the portfolio arraylist
	public int getSize() {
		return portfolio.size();
	}
	
	// Adds a "stock" to the key value pair which in this case would be a string
	// TODO: See if a more complex implementation of stock is required (mentioned above)
	// If so addStock would have to be changed to fit the new format 
	public void addStock(String stock) {
		portfolio.add(stock);
	}
	
	// Removes a "stock" to the key value pair which in this case would be a string
	// TODO: See if a more complex implementation of stock is required (mentioned above)
	// If so removeStock would have to be changed to fit the new format 
	public void removeStock(String stock) {
		portfolio.remove(stock);
	}
	
	//Clears the portfolio of the user, in other words clears the array list
	public void resetPortfolio() {
		portfolio.clear();
	}
	
	//Checks to see if  the portfolio contains a stock
	// TODO: See if a more complex implementation of stock is required (mentioned above)
	// If so removeStock would have to be changed to fit the new format 
	public boolean contains(String stock) {
		return portfolio.contains(stock);
	}
	
}
