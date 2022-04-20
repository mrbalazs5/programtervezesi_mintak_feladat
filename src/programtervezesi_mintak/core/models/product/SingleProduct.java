package programtervezesi_mintak.core.models.product;

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
}
