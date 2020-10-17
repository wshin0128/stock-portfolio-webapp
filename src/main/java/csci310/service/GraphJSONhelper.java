package csci310.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;
import java.util.Random; 

public class GraphJSONhelper {

	public String StockGraphInfo(String symbol, Resolution resolution, long startTime, long endTime) // need to have another class that passes the x-axis labels to the jsp
	{
		FinnhubClient FC = new FinnhubClient();
		Random rand = new Random();
		try {
			Map<Date, Double> stock_info_temp = FC.getStockPrice(symbol, resolution, startTime, endTime); //unsorted
			
			Map<Date, Double> stock_info = new TreeMap<Date, Double>(stock_info_temp);
			
			ArrayList<String> dates = new ArrayList<String>();
			ArrayList<Double> values = new ArrayList<Double>();
			
			for (Map.Entry<Date, Double> entry : stock_info.entrySet())
			{
				
				 Date date =entry.getKey();
				 
				 //Note to self: make this a fn in the future with TDD. need to move this date chunk to new class that handles x-axis labels seperately
				 String final_date = "";
				 Calendar startDate = Calendar.getInstance();
				 startDate.setTime(date);
				 String year = Integer.toString(startDate.get(Calendar.YEAR));
				 String month = Integer.toString(startDate.get(Calendar.MONTH));
				 String day = Integer.toString(startDate.get(Calendar.DAY_OF_MONTH));
				 
				 final_date = month + "/" + day + "/" + year;
				 dates.add(final_date);
				 values.add(entry.getValue());
				 
				 System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				  
			}
			
			JSONObject json = new JSONObject();
			 json.put("label", "Apple value in $");
			 json.put("data", values);
			 json.put("fill", "false");
			 int r = rand.nextInt(256);
			 int g = rand.nextInt(256);
			 int b = rand.nextInt(256);
			 
			 ArrayList<String> rgb = new ArrayList<String>(); //need to do this because it is the required format in chart.js
			 String rgb_val = "rgba(" + Integer.toString(r) + ',' + Integer.toString(g) + ',' + Integer.toString(b) + ", 1)";
			 rgb.add(rgb_val);
			 json.put("borderColor", rgb);
			 json.put("borderWidth", 1);
			 
			 System.out.println(json.toString());
	          
			 return json.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
			
	}
	
	public static void main(String [] args) {
		GraphJSONhelper G = new GraphJSONhelper();
		G.StockGraphInfo("AAPL", Resolution.Monthly, 1572566400, 1601942400);
		
			
	}
}
