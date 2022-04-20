package programtervezesi_mintak.core.models.shipment.states;

import programtervezesi_mintak.core.models.shipment.Shipment;

public class ShippedState extends State {

	public ShippedState(Shipment shipment) {
		super(shipment);
	}

	@Override
	public void ship() {
		System.out.println("Shipment already shipped.");
	}

	@Override
	public void cancel() {
		this.bringBackEveryProduct();
		
		shipment.changeState(new CancelledState(shipment));
	}

	@Override
	public void deliver() {
		shipment.changeState(new DeliveredState(shipment));
	}

	@Override
	public void receive() {
		System.out.println("The shipment has not yet been delivered.");
	}

}
