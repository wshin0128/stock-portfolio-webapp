package csci310.model;

import java.util.ArrayList;

public class Portfolio {
	private ArrayList<String> portfolio;
	
	public Portfolio() {
		portfolio = new ArrayList<String>();
	}
	
	public ArrayList<String> getArrayList() {
		return portfolio;
	}
	
	public int getSize() {
		return portfolio.size();
	}
	
	public void addStock(String stock) {
		portfolio.add(stock);
	}
	
	public void removeStock(String stock) {
		portfolio.remove(stock);
	}
	
	public void resetPortfolio() {
		portfolio.clear();
	}
	
	public boolean contains(String stock) {
		return portfolio.contains(stock);
	}
	
}
