package programtervezesi_mintak;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programtervezesi_mintak.core.models.product.SingleProduct;

class SingleProductTest {
	private SingleProduct product;
	
	@BeforeEach
	void setUp() {
		product = new SingleProduct("apple", 300);
	}
	
	@Test
	void testGetTotalPrice() {
		assertEquals(product.getTotalPrice(), 300);
	}

	@Test
	void testGetTotalQuantity() {
		assertEquals(product.getTotalQuantity(), 1);
	}

	@Test
	void testGetTotalQuantityByProductName() {
		assertEquals(product.getTotalQuantityByProductName().size(), 1);
		
		assertEquals(product.getTotalQuantityByProductName().get(0).product, product);
		assertEquals(product.getTotalQuantityByProductName().get(0).quantity, 1);
	}
}
