package nl.voeding.voedingsMeter.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.voeding.voedingsmeter.model.Product;

public class ProductTest {
	
	Product product;
	
	@Before
	public void before() {
		product = new Product();
	}

	@Test
	public void ProductHoeveelheidTest() {
		product.setHoeveelheid(-3f);
		assertEquals(0, product.getHoeveelheid(), 0);
	}
	
}
