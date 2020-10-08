package csci310.model;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {
	// Stock variables
	private String name;
	private String ticker;
	private String color;
	private int quantity;
	private Integer buyDate;
	private Integer sellDate;
	
	// Stock constructor
	public Stock(String name, String ticker, int quantity, Integer buyDate, Integer sellDate) {
		this.name = name;
		this.ticker = ticker;
		this.assignHexColor();
		this.quantity = quantity;
		this.buyDate = buyDate;
		this.sellDate = sellDate;
	}
	
	// Random hex color generator
	public String assignHexColor() {
		Random rand = new Random();
		
		// Generate random number with maximum being #FFFFFF
        int rawColor = rand.nextInt(0xffffff + 1);
        
        // If rawColor is white, generate a new color
        while(rawColor == 16777215) {
        	rawColor = rand.nextInt(0xffffff + 1);
        }
                
        // Format the number as a hex string
        String color = String.format("#%06x", rawColor);
        
        // Set this stock's color and return the color
		this.color = color;
		return color;
	}
	
	
	
	
}
