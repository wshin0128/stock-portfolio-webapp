package csci310.service;

import java.util.ArrayList;

import csci310.model.Stock;

public class CSVhelper {

	private boolean IOException = false;

	public boolean getIOException() {
		return IOException;
	}

	public void setIOException() {
		// shell for setter
	}

	public String validateStock(String filePath) {
		// shell for validate stock
		return "msg";
	}

	public ArrayList<Stock> parseCSV(String filePath) {
		// shell for parsing CSV 
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		return stocks;
	}
}
