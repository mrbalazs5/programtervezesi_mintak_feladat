package programtervezesi_mintak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import programtervezesi_mintak.core.models.product.Box;
import programtervezesi_mintak.core.models.product.Product;
import programtervezesi_mintak.core.models.product.SingleProduct;
import programtervezesi_mintak.core.models.shipment.Shipment;
import programtervezesi_mintak.core.models.user.User;

public class Application {
	private Random rand = new Random();
	
	public static void main(String[] args) {
		Application app = new Application();
		
		app.run();
	}
	
	public void run() {
		int userCount = rand.nextInt(20);
		
		StorageManager storageManager = StorageManager.getInstance();
		
		Product apple = new SingleProduct("apple", 300);
		Product strawberry = new SingleProduct("strawberry", 900);
		Product computer = new SingleProduct("computer", 100000);
		Product smartphone = new SingleProduct("smartphone", 50000);
		
		List<Product> products = new ArrayList<Product>() {{
		    add(apple);
		    add(strawberry);
		    add(computer);
		    add(smartphone);
		}};
		
		List<User> users = new ArrayList<User>();
		
		for(int i = 0; i < userCount; i++) {
			List<Product> randProducts = getNRandomElements(products, rand.nextInt(4) + 1);
			
			User user = new User(String.valueOf(i), randProducts);
			
			storageManager.getSubscriberManager().subscribe(user);
			
			users.add(user);
		}
		
		while(!storageManager.isStoreEmpty()) {
			for(User user: users) {
				Box rootBox = new Box("rootBox", 0);
				
				for(Product product: user.getShoppingList()) {
					int productQty = rand.nextInt(10) + 1;
					
					Box productContainer = new Box("productContainer", 0);
					
					for(int i = 0; i < productQty; i++) {
						productContainer.add(product);
					}
					
					rootBox.add(productContainer);
				}
				
				Shipment shipment = new Shipment();
			}
			
			storageManager.printStorageStatistics();
		}
	}
	
    public <T> List<T> getNRandomElements(List<T> list, int n) {
    	List<T> copyList = new ArrayList<T>(list);
 
        List<T> newList = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int randomIndex = rand.nextInt(copyList.size());
 
            newList.add(copyList.get(randomIndex));

            copyList.remove(randomIndex);
        }
        
        return newList;
    }
}
