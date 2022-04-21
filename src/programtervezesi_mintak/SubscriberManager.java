package programtervezesi_mintak;

import java.util.ArrayList;
import java.util.List;

import programtervezesi_mintak.core.exception.UnknownEventTypeException;
import programtervezesi_mintak.core.models.product.Product;

public class SubscriberManager {
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();
    
    public List<Subscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}
	
	public void subscribe(Subscriber subscriber) {
		subscribers.add(subscriber);
	}
	
	public void unsubscribe(Subscriber subscriber) {
		subscribers.remove(subscriber);
	}
	
    public void notify(final int eventType, Product product) {
    	try {
            for (Subscriber subscriber : subscribers) {
            	subscriber.update(eventType, product);
            }
    	} catch(UnknownEventTypeException e) {
    		e.printStackTrace();
    	}
    }
}
