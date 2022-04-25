package programtervezesi_mintak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import programtervezesi_mintak.core.exception.ProductNotFoundException;
import programtervezesi_mintak.core.models.product.Box;
import programtervezesi_mintak.core.models.product.Product;
import programtervezesi_mintak.core.models.product.SingleProduct;
import programtervezesi_mintak.core.models.shipment.Shipment;
import programtervezesi_mintak.core.models.user.User;

public class Application {
	private Random rand = new Random();
	
	public static void main(String[] args) {
		Application app = new Application();
		
		try {
			app.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws InterruptedException {
		int userCount = rand.nextInt(20) + 1;
		
		StorageManager storageManager = StorageManager.getInstance();
		
		Product apple = new SingleProduct("apple", 300);
		Product strawberry = new SingleProduct("strawberry", 900);
		Product computer = new SingleProduct("computer", 100000);
		Product smartphone = new SingleProduct("smartphone", 50000);
		
		if(storageManager.isStorageEmpty()) {
			System.out.println("Storage is empty!");
			
			return;
		}
		
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
		
		System.out.println("Number of users: " + users.size() + "\n");
		
		while(!storageManager.isStorageEmpty()) {
			for(User user: users) {
				Box rootBox = new Box("rootBox", 0);
				
				System.out.println(user.getName() + "'s shopping list:");
				
				for(Product product: user.getShoppingList()) {
					int productQty = rand.nextInt(10) + 1;
					
					try {
						int availableQty = storageManager.getProductQuantity(product);
						
						productQty = productQty > availableQty ? availableQty : productQty;
						
						if(productQty <= 0) {
							continue;
						}
						
						System.out.println(product.getName());
						System.out.println(productQty);
						
						Box productContainer = new Box("productContainer", 0);
						
						for(int i = 0; i < productQty; i++) {
							productContainer.add(product);
						}
						
						rootBox.add(productContainer);
					} catch (ProductNotFoundException e) {
						e.printStackTrace();
						
						continue;
					}
				}
				
				Shipment shipment = new Shipment();
				shipment.addBox(rootBox);
				
				if(rand.nextBoolean()) {
					shipment.getState().ship();
				} else {
					shipment.getState().cancel();
					
					System.out.println("Cancelled in ready state.");
					
					storageManager.printStorageStatistics();
					
					continue;
				}
				
				Thread.sleep(rand.nextInt(400) + 100);
				
				shipment.getState().deliver();
				
				if(rand.nextBoolean()) {
					shipment.getState().receive();
					
					System.out.println(user.getName() + " received a box. Total price: " + shipment.getTotalPrice());
				} else {
					shipment.getState().cancel();
					
					System.out.println("Cancelled in delivered state.");
					
					storageManager.printStorageStatistics();
					
					continue;
				}
				
				storageManager.printStorageStatistics();
			}
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
