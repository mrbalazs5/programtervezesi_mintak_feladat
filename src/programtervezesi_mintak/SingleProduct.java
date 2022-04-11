package programtervezesi_mintak;

public class SingleProduct extends Product {

	public SingleProduct(String name, int price, int quantity) {
		super(name, price, quantity);
	}

	@Override
	public int getTotalPrice() {
		return price;
	}

}
