package programtervezesi_mintak.core.models.shipment.states;

import programtervezesi_mintak.core.models.shipment.Shipment;

public class DeliveredState extends State {

	public DeliveredState(Shipment shipment) {
		super(shipment);
	}

	@Override
	public void ship() {
		System.out.println("Shipment already shipped.");
	}

	@Override
	public void cancel() {
		System.out.println("Shipment can't be cancelled in delivered state.");
	}

	@Override
	public void deliver() {
		System.out.println("Shipment already delivered.");
	}

	@Override
	public void receive() {
		shipment.changeState(new ReceivedState(shipment));
	}

}