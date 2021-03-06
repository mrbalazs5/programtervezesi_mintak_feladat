package programtervezesi_mintak.core.models.shipment;

import java.util.ArrayList;
import java.util.List;

import programtervezesi_mintak.core.models.shipment.states.ReadyState;
import programtervezesi_mintak.core.models.shipment.states.State;
import programtervezesi_mintak.StorageManager;
import programtervezesi_mintak.core.models.product.Box;
import programtervezesi_mintak.core.models.product.Product;

public class Shipment {
	private State state;
	
	private List<Box> boxes = new ArrayList<>();
	
	public Shipment(StorageManager storageManager) {
		this.state = new ReadyState(this, storageManager);
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
	
	public int getTotalPrice() {
		int total = 0;
		
		for(Product product: boxes) {
			total += product.getTotalPrice();
		}
		
		return total;
	}
}
