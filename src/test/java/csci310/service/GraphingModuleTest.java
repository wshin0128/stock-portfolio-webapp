package csci310.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphingModuleTest {

	@Test
	public void testgetPortfolioValueBasic() {

		GraphingModule G = new GraphingModule();
		assertTrue(G.getPortfolioValue(null, null, 0, 0)!=null);
	}

}
