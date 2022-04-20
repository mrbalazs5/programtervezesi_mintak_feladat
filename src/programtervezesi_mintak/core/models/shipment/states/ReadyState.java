package programtervezesi_mintak.core.models.shipment.states;

import programtervezesi_mintak.core.models.shipment.Shipment;

public class ReadyState extends State {
    public ReadyState(Shipment shipment) {
		super(shipment);
	}

	@Override
	public void ship() {
		this.takeOutEveryProduct();
		
		shipment.changeState(new ShippedState(shipment));
	}

	@Override
	public void cancel() {
		shipment.changeState(new CancelledState(shipment));
	}

	@Override
	public void deliver() {
		System.out.println("The shipment has not yet been shipped.");
	}

	@Override
	public void receive() {
		System.out.println("The shipment has not yet been shipped.");
	}

}
