package programtervezesi_mintak.core.models.shipment.states;

import programtervezesi_mintak.core.models.shipment.Shipment;

public class ReceivedState extends State {

	public ReceivedState(Shipment shipment) {
		super(shipment);
	}

	@Override
	public void ship() {
		System.out.println("Shipment already shipped.");
	}

	@Override
	public void cancel() {
		System.out.println("Shipment can't be cancelled in received state.");
	}

	@Override
	public void deliver() {
		System.out.println("Shipment already delivered.");
	}

	@Override
	public void receive() {
		System.out.println("Shipment already received.");
	}

}
