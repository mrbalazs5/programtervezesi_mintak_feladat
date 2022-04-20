package programtervezesi_mintak.core.models.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Box extends Product {
	private List<Product> products = new ArrayList<>();
	
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

	@Override
	public List<Pair> getTotalQuantityByProductName() {
		List<Pair> totalProductQuantityPairs = new ArrayList<>();
		List<Pair> aggregatedTotalProductQuantityPairs = new ArrayList<>();
		
        for (Product product : products) {
        	totalProductQuantityPairs.addAll(product.getTotalQuantityByProductName());
		}
        
        totalProductQuantityPairs
	        .stream()
	        .collect(
	    		Collectors.groupingBy(
					pQ -> pQ.product,
	                Collectors.summingInt(pQ -> pQ.quantity)
	            )
			)
	        .forEach(
	    		(product, quantity) -> 
	    			aggregatedTotalProductQuantityPairs.add(new Pair(product, quantity))
			);   
        
		return aggregatedTotalProductQuantityPairs;
	}
}
