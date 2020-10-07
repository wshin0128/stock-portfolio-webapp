package csci310.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class UnixConverterClientTest {

	@Test
	public void testConvertUnixToDate() {
	    long unix = 1604620800; // 2020-11-06
	    Date resultDate = UnixConverterClient.convertUnixToDate(unix);
	    assertEquals(resultDate.toString(), "2020-11-06");

	}

	@Test
	public void testConvertDateToUnix() {
		long unixSeconds = 1372339860;
		// convert seconds to milliseconds
		Date date = new java.util.Date(unixSeconds*1000L);
		assertEquals(UnixConverterClient.convertDateToUnix(date), unixSeconds);
	}

}
