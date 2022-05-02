package programtervezesi_mintak.core.models.shipment.states;

import programtervezesi_mintak.StorageManager;
import programtervezesi_mintak.core.models.shipment.Shipment;

public class DeliveredState extends State {

	public DeliveredState(Shipment shipment, StorageManager storageManager) {
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
		System.out.println("Shipment already delivered.");
	}

	@Override
	public void receive() {
		shipment.changeState(new ReceivedState(shipment, storageManager));
	}

}
