package csci310.model;

public class Stock {
	// Stock variables
	private String name;
	private String ticker;
	private String color;
	private int quantity;
	private String buyDate;
	private String sellDate;
	
	// Stock constructor
	public Stock(String name, String ticker, int quantity, String buyDate, String sellDate) {
		this.name = name;
		this.ticker = ticker;
		this.quantity = quantity;
		this.buyDate = buyDate;
		this.sellDate = sellDate;
	}
	
	// Random hex color generator
	public String assignHexColor() {
		return "";
	}
	
	// name getter
	public String getName() {
		return name;
	}
	
	// ticker getter
	public String getTicker() {
		return ticker;
	}
	
	// color getter
	public String getColor() {
		return color;
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
	
	// name setter
	public void setName(String name) {
		this.name = name;
	}
	
	// ticker setter
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	// color setter
	public void setColor(String color) {
		this.color = color;
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
