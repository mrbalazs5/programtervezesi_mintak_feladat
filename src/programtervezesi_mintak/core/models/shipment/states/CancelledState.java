package programtervezesi_mintak.core.models.shipment.states;

import programtervezesi_mintak.StorageManager;
import programtervezesi_mintak.core.models.shipment.Shipment;

public class CancelledState extends State {

	public CancelledState(Shipment shipment, StorageManager storageManager) {
		super(shipment, storageManager);
	}

	@Override
	public void ship() {
		System.out.println("Can't ship a cancelled shipment.");
	}

	@Override
	public void cancel() {
		System.out.println("Shipment already cancelled.");
	}

	@Override
	public void deliver() {
		System.out.println("Can't deliver a cancelled shipment.");
	}

	@Override
	public void receive() {
		System.out.println("Can't receive a cancelled shipment.");
	}

}
