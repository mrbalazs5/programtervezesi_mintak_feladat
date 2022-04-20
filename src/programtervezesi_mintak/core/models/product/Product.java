package programtervezesi_mintak.core.models.product;

public abstract class Product {
	protected String name;
	protected int price;
	
	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public abstract int getTotalQuantity();
	
	public abstract int getTotalPrice();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
}
