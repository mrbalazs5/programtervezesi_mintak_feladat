package programtervezesi_mintak.core.models.user;

import java.util.ArrayList;
import java.util.List;

import programtervezesi_mintak.Subscriber;
import programtervezesi_mintak.core.exception.UnknownEventTypeException;
import programtervezesi_mintak.core.models.product.Product;

public class User implements Subscriber {
	private String name;
	private List<Product> shoppingList = new ArrayList<>();
	private List<Product> originalShoppingList = new ArrayList<>();

	public User(String name, List<Product> shoppingList ) {
		this.name = name;
		this.shoppingList = shoppingList;
		this.originalShoppingList = new ArrayList<Product>(shoppingList);
	}

	@Override
	public void update(final int eventType, Product product) throws UnknownEventTypeException {
		switch(eventType) {
			case Subscriber.EVENT_BECAME_AVAILABLE:
				if(originalShoppingList.contains(product)) {
					shoppingList.add(product);
				}
				
				break;
			case Subscriber.EVENT_OUT_OF_STOCK:
				if(shoppingList.contains(product)) {
					shoppingList.remove(product);
				}
				
				break;
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
