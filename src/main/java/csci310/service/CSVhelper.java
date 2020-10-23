package csci310.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import csci310.model.Stock;

public class CSVhelper {

	private boolean IOException = false;

	public boolean getIOException() {
		return IOException;
	}

	public void setIOException() {
		IOException = true;
	}

	public String validateStock(String filePath) {
		BufferedReader br = null;
		String line = "";
		String errorMsg = "";
		try {
			br = new BufferedReader(new FileReader(filePath));
			if(IOException) {
				br.close();
			}
			int i = 1;
			while ((line = br.readLine()) != null) {
				String[] parameters = line.split(",");

				if(parameters.length < 5) {
					errorMsg += ("Row " + String.valueOf(i) + ": requires minimum of 5 parameters.\n");
				}
				else {
					if(parameters[0].trim().length() == 0) {
						errorMsg += ("Row " + String.valueOf(i) + ", Col A: stock name is a required field.\n");
					}
					if(parameters[1].trim().length() == 0) {
						errorMsg += ("Row " + String.valueOf(i) + ", Col B: stock ticker is a required field.\n");
					}
					try {
						Integer.valueOf(parameters[2]);
					}catch(NumberFormatException e) {
						errorMsg += ("Row " + String.valueOf(i) + ", Col C: stock quantity must be a Integer.\n");
					}
					try {
						Long.valueOf(parameters[3]);
					}catch(NumberFormatException e) {
						errorMsg += ("Row " + String.valueOf(i) + ", Col D: stock buy date must be a Long.\n");
					}
					try {
						Long.valueOf(parameters[4]);
					}catch(NumberFormatException e) {
						errorMsg += ("Row " + String.valueOf(i) + ", Col E: stock sell date must be a Long.\n");
					}
				}
				i += 1;
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException from CSVhelper.validateStock()");
			return "FileNotFoundException";
		} catch (IOException e) {
			System.out.println("IOException from CSVhelper.validateStock()");
			return "IOException";
		}


		if(errorMsg.length() == 0) {
			errorMsg += "No Error Found.";
		}
		return errorMsg;
	}

	public ArrayList<Stock> parseCSV(String filePath) {
		ArrayList<Stock> stocks = new ArrayList<Stock>();

		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(filePath));
			if(IOException) {
				br.close();
			}

			while ((line = br.readLine()) != null) {
				String[] parameters = line.split(",");
				Stock stock = new Stock(parameters[0], parameters[1], null, 
						Integer.valueOf(parameters[2]), Long.valueOf(parameters[3]), Long.valueOf(parameters[4]));
				stocks.add(stock);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException from CSVhelper.parseCSV()");
			return null;
		} catch (IOException e) {
			System.out.println("IOException from CSVhelper.parseCSV()");
			return null;
		}

		return stocks;
	}
}
