package programtervezesi_mintak;

import programtervezesi_mintak.core.exception.UnknownEventTypeException;
import programtervezesi_mintak.core.models.product.Product;

public interface Subscriber {
	public final String EVENT_OUT_OF_STOCK = "out_of_stock";
	public final String EVENT_BECAME_AVAILABLE = "available";
	
	public void update(String eventType, Product product) throws UnknownEventTypeException;
}
