package programtervezesi_mintak.core.models.user;

import java.util.ArrayList;
import java.util.List;

import programtervezesi_mintak.Subscriber;
import programtervezesi_mintak.core.exception.UnknownEventTypeException;
import programtervezesi_mintak.core.models.product.Product;

public class User implements Subscriber {
	private String name;
	private List<Product> shoppingList = new ArrayList<Product>();

	public User(String name, List<Product> shoppingList ) {
		this.name = name;
		this.shoppingList = shoppingList;
	}

	@Override
	public void update(String eventType, Product product) throws UnknownEventTypeException {
		switch(eventType) {
			case Subscriber.EVENT_BECAME_AVAILABLE:
				shoppingList.add(product);
			case Subscriber.EVENT_OUT_OF_STOCK:
				shoppingList.remove(product);
			default:
				throw new UnknownEventTypeException();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Product> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<Product> shoppingList) {
		this.shoppingList = shoppingList;
	}
	
}
