package programtervezesi_mintak.core.models.shipment;

import java.util.ArrayList;
import java.util.List;

import programtervezesi_mintak.core.models.shipment.states.ReadyState;
import programtervezesi_mintak.core.models.shipment.states.State;
import programtervezesi_mintak.core.models.product.Box;

public class Shipment {
	private State state;
	
	private List<Box> boxes = new ArrayList<>();
	
	public Shipment() {
		this.state = new ReadyState(this);
	}
	
    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

	public List<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}
	
	public void addBox(Box box) {
		this.boxes.add(box);
	}
	
	public void removeBox(Box box) {
		this.boxes.remove(box);
	}
}
