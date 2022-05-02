package programtervezesi_mintak;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import programtervezesi_mintak.core.models.product.Box;
import programtervezesi_mintak.core.models.product.Product;
import programtervezesi_mintak.core.models.product.SingleProduct;
import programtervezesi_mintak.core.models.shipment.Shipment;
import programtervezesi_mintak.core.models.shipment.states.CancelledState;
import programtervezesi_mintak.core.models.shipment.states.DeliveredState;
import programtervezesi_mintak.core.models.shipment.states.ReceivedState;

class DeliveredStateTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	private Shipment shipment;

    @BeforeEach
    void setUp() {
    	System.setOut(new PrintStream(outContent));
    	
    	Product product = new SingleProduct("apple", 500);
    	Box box = new Box("root", 0);
    	box.add(product);
    	
    	StorageManager storageManager = mock(StorageManager.class);
    	
    	shipment = new Shipment(storageManager);
    	shipment.addBox(box);
    	
    	shipment.getState().ship();
    	
    	shipment.getState().deliver();
    	
    	assertThat(shipment.getState(), instanceOf(DeliveredState.class));
    }
    
    @AfterEach
    void teardown() {
    	System.setOut(originalOut);
    }
    
	@Test
	void testShip() {
		shipment.getState().ship();
		
		assertEquals("Shipment already shipped.\n", outContent.toString());
	}
	
	@Test
	void testCancel() {
		shipment.getState().cancel();
		
		assertThat(shipment.getState(), instanceOf(CancelledState.class));
	}
	
	@Test
	void testDeliver() {
		shipment.getState().deliver();
		
		assertEquals("Shipment already delivered.\n", outContent.toString());
	}

	@Test
	void testReceive() {
		shipment.getState().receive();
		
		assertThat(shipment.getState(), instanceOf(ReceivedState.class));
	}
}
