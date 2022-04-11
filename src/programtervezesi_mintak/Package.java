package programtervezesi_mintak;

import java.util.ArrayList;
import java.util.List;

public class Package extends Product {
	private List<Product> products;
	
	public Package(String name, int price, int quantity) {
		super(name, price, quantity);
		
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
        int total = 0;
        
        for (Product product : products) {
        	total += product.getTotalPrice();
		}

        return total;
    }
}
