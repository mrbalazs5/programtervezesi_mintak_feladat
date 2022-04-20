package programtervezesi_mintak.core.models.product;

public class Pair {
	public Product product;
	public int quantity;
	
	public Pair(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return product.name + "-" + quantity;
	}
}
