package programtervezesi_mintak;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programtervezesi_mintak.core.models.product.Box;
import programtervezesi_mintak.core.models.product.Pair;
import programtervezesi_mintak.core.models.product.SingleProduct;

class BoxTest {
	SingleProduct product1;
	SingleProduct product2;
	private Box box;
	
	@BeforeEach
	void setUp() {
		product1 = new SingleProduct("apple", 300);
		product2 = new SingleProduct("strawberry", 900);
		
		box = new Box("root", 0);
		
		box.add(product1);
		box.add(product2);
	}
	
	@Test
	void testGetTotalPrice() {
		assertEquals(box.getTotalPrice(), 1200);
	}

	@Test
	void testGetTotalQuantity() {
		assertEquals(box.getTotalQuantity(), 2);
	}

	@Test
	void testGetTotalQuantityByProductName() {
		assertEquals(box.getTotalQuantityByProductName().size(), 2);
		
		Pair productQuantity1 = box.getTotalQuantityByProductName().stream()
		  .filter(productQuantity -> productQuantity.product == product1)
		  .findFirst()
		  .orElse(null);
		
		Pair productQuantity2 = box.getTotalQuantityByProductName().stream()
				  .filter(productQuantity -> productQuantity.product == product2)
				  .findFirst()
				  .orElse(null);
		
		assertNotNull(productQuantity1);
		assertEquals(productQuantity1.quantity, 1);
		
		assertNotNull(productQuantity2);
		assertEquals(productQuantity2.quantity, 1);
	}
}
