package programtervezesi_mintak.core.models.shipment.states;

import programtervezesi_mintak.StorageManager;
import programtervezesi_mintak.core.models.shipment.Shipment;

public class ShippedState extends State {

	public ShippedState(Shipment shipment, StorageManager storageManager) {
		super(shipment, storageManager);
	}

	@Override
	public void ship() {
		System.out.println("Shipment already shipped.");
	}

	@Override
	public void cancel() {
		this.bringBackEveryProduct();
		
		shipment.changeState(new CancelledState(shipment, storageManager));
	}

	@Override
	public void deliver() {
		shipment.changeState(new DeliveredState(shipment, storageManager));
	}

	@Override
	public void receive() {
		System.out.println("The shipment has not yet been delivered.");
	}

}
