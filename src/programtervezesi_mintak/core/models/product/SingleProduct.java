package programtervezesi_mintak.core.models.product;

import java.util.ArrayList;
import java.util.List;

public class SingleProduct extends Product {

	public SingleProduct(String name, int price) {
		super(name, price);
	}

	@Override
	public int getTotalPrice() {
		return price;
	}

	@Override
	public int getTotalQuantity() {
		return 1;
	}

	@Override
	public List<Pair> getTotalQuantityByProductName() {
		Pair productQuantityPair = new Pair(this, 1);
		
		return new ArrayList<Pair>() {{
		    add(productQuantityPair);
		}};
	}
}
