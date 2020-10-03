package csci310.model;

public class Stock {
	// Stock variables
	private String ticker;
	private int quantity;
	private String buyDate;
	private String sellDate;
	
	// Stock constructor
	public Stock(String ticker, int quantity, String buyDate, String sellDate) {
		this.ticker = ticker;
		this.quantity = quantity;
		this.buyDate = buyDate;
		this.sellDate = sellDate;
	}
	
	// ticker getter
	public String getTicker() {
		return ticker;
	}
	
	// quantity getter
	public int getQuantity() {
		return quantity;
	}
	
	// buyDate getter
	public String getBuyDate() {
		return buyDate;
	}
	
	// sellDate getter
	public String getSellDate() {
		return sellDate;
	}
	
	// ticker setter
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	// quantity setter
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// buyDate setter
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	
	// sellDate setter
	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}
}
