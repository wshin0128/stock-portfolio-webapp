package csci310.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResolutionGetterTest {

	@Test
	public void testMonth() {
		assertTrue(ResolutionGetter.Month()==Resolution.Monthly);
	}

}
