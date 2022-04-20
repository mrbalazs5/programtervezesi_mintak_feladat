package programtervezesi_mintak.core.models.order;

import java.util.List;

import programtervezesi_mintak.core.models.shipment.Shipment;

public class Order {
	private List<Shipment> shipments;

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}
	
	public void addShipment(Shipment shipment) {
		this.shipments.add(shipment);
	}
	
	public void removeBox(Shipment shipment) {
		this.shipments.remove(shipment);
	}
}
