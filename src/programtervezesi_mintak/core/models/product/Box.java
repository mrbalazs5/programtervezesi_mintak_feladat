package programtervezesi_mintak.core.models.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Box extends Product {
	private List<Product> products;
	
	public Box(String name, int price) {
		super(name, price);
		
		products = new ArrayList<Product>();
	}

    public void add(Product product) {
    	products.add(product);
    }

    public void remove(Product product) {
    	products.remove(product);
    }

    @Override
    public int getTotalPrice() {
        int totalPrice = 0;
        
        for (Product product : products) {
        	totalPrice += product.getTotalPrice();
		}

        return totalPrice;
    }

	@Override
	public int getTotalQuantity() {
        int totalQuantity = 0;
        
        for (Product product : products) {
        	totalQuantity += product.getTotalQuantity();
		}

        return totalQuantity;
	}

	public Map<Product, Integer> getTotalQuantityByProductName() {
		return products
		  .stream()
		  .collect(
			  Collectors.groupingBy(
				  product -> product,
				  Collectors.summingInt(product -> product.getTotalQuantity())
			  )
		  );
	}
}
