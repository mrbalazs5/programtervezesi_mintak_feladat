package programtervezesi_mintak.core.models.shipment.states;

import programtervezesi_mintak.StorageManager;
import programtervezesi_mintak.core.exception.ProductNotFoundException;
import programtervezesi_mintak.core.models.product.Box;
import programtervezesi_mintak.core.models.shipment.Shipment;

public abstract class State {
	protected StorageManager storageManager;
	
	Shipment shipment;
	
	public State(Shipment shipment, StorageManager storageManager) {
        this.shipment = shipment;
        this.storageManager = storageManager;
    }
	
	public abstract void ship();
	
	public abstract void cancel();
	
	public abstract void deliver();
	
	public abstract void receive();
	
	public void takeOutEveryProduct() {
		for(Box box: shipment.getBoxes()) {
			box.getTotalQuantityByProductName()
			.forEach((pair) -> 
				{
					try {
						storageManager.changeProductQuantity(pair.product, -pair.quantity);
					} catch (ProductNotFoundException e) {
						e.printStackTrace();
					}
				}
			);
		}
	}
	
	public void bringBackEveryProduct() {
		for(Box box: shipment.getBoxes()) {
			box.getTotalQuantityByProductName()
			.forEach((pair) -> 
				{
					try {
						storageManager.changeProductQuantity(pair.product, pair.quantity);
					} catch (ProductNotFoundException e) {
						e.printStackTrace();
					}
				}
			);
		}
	}
}
