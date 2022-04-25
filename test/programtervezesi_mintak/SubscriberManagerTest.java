package programtervezesi_mintak;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programtervezesi_mintak.core.exception.UnknownEventTypeException;
import programtervezesi_mintak.core.models.product.SingleProduct;
import programtervezesi_mintak.core.models.user.User;

class SubscriberManagerTest {
	private SubscriberManager subscriberManager;
	
	@BeforeEach
	void setUp() {
		subscriberManager = new SubscriberManager();
	}

	@Test
	void testSubscribe() {
		User user = new User("test", new ArrayList<>());
		
		subscriberManager.subscribe(user);
		
		assertEquals(subscriberManager.getSubscribers().size(), 1);
	}

	@Test
	void testUnsubscribe() {
		User user = new User("test", new ArrayList<>());
		
		subscriberManager.subscribe(user);
		
		assertEquals(subscriberManager.getSubscribers().size(), 1);
		
		subscriberManager.unsubscribe(user);
		
		assertEquals(subscriberManager.getSubscribers().size(), 0);
	}
	
	@Test
	void testNotify() throws UnknownEventTypeException {
		SingleProduct product = new SingleProduct("apple", 300);
		
		User user1 = mock(User.class);
		User user2 = mock(User.class);
		
		subscriberManager.subscribe(user1);
		subscriberManager.subscribe(user2);
		
		subscriberManager.notify(Subscriber.EVENT_OUT_OF_STOCK, product);
		
		verify(user1, times(1)).update(Subscriber.EVENT_OUT_OF_STOCK, product);
		verify(user2, times(1)).update(Subscriber.EVENT_OUT_OF_STOCK, product);
	}
}
