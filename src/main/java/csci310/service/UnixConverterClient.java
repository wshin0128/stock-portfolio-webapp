package csci310.service;

import java.util.Date;

public class UnixConverterClient {
	
    public static Date convertUnixToDate(long unix) {
    	// Convert unixSeconds to milliseconds
    	Date date = new Date(unix*1000L);
    	return date;
    }
    
    public static long convertDateToUnix(Date date) {
    	return date.getTime();
    }
}
