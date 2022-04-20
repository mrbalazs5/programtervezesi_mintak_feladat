package programtervezesi_mintak;

import programtervezesi_mintak.core.exception.UnknownEventTypeException;
import programtervezesi_mintak.core.models.product.Product;

public interface Subscriber {
	public static final int EVENT_OUT_OF_STOCK = 0;
	public static final int EVENT_BECAME_AVAILABLE = 1;
	
	public void update(final int eventType, Product product) throws UnknownEventTypeException;
}
