package programtervezesi_mintak;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import programtervezesi_mintak.core.exception.ProductNotFoundException;
import programtervezesi_mintak.core.models.product.Product;


public class StorageManager {
    private static final String STORAGE_LOCATION = "./resources/storage.xml";

    private static StorageManager instance;
    
    private Document document;
    
    private SubscriberManager subscriberManager;

	private StorageManager() {
    	subscriberManager = new SubscriberManager();
    	
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
        	dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        	
        	DocumentBuilder db = dbf.newDocumentBuilder();

        	document = db.parse(new File(STORAGE_LOCATION));

        	document.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }

        return instance;
    }
    
    public SubscriberManager getSubscriberManager() {
		return subscriberManager;
	}

	public void setSubscriberManager(SubscriberManager subscriberManager) {
		this.subscriberManager = subscriberManager;
	}
    
    public void changeProductQuantity(Product product, int quantity) throws ProductNotFoundException {
    	Node productNode = getProductNodeByName(product.getName());
    	
    	Node qtyAttrNode = productNode.getAttributes().getNamedItem("quantity");
    	
    	int oldQty = Integer.parseInt(qtyAttrNode.getNodeValue());
    	int newQty = oldQty + quantity;
    	
    	if(newQty < 0) {
    		subscriberManager.notifyAboutOutOfStock(product);
    		
    		qtyAttrNode.setNodeValue("0");
    		
    		return;
    	}
    	
    	qtyAttrNode.setNodeValue(String.valueOf(newQty));
    	
    	if(oldQty <= 0 && newQty > 0) {
    		subscriberManager.notifyAboutBecameAvailable(product);
    	}
    }
    
    public Node getProductNodeByName(String productName) throws ProductNotFoundException {
    	NodeList products = document.getElementsByTagName("product");
    	
        for(int i = 0; i < products.getLength(); i++) {
        	Node product = products.item(i);
        	Node nameAttrNode = product.getAttributes().getNamedItem("name");
        	
            if(nameAttrNode.getNodeValue().equals(productName)) {
            	return product;
            }
        }
        
        throw new ProductNotFoundException();
    }
    
    public int getProductQuantity(Product product) throws ProductNotFoundException {
    	Node productNode = getProductNodeByName(product.getName());
    	
    	Node qtyAttrNode = productNode.getAttributes().getNamedItem("quantity");
    	
    	return Integer.parseInt(qtyAttrNode.getNodeValue());
    }
    
    public boolean isStoreEmpty() {
    	NodeList products = document.getElementsByTagName("product");
    	
        for(int i = 0; i < products.getLength(); i++) {
        	Node product = products.item(i);
        	Node qtyAttrNode = product.getAttributes().getNamedItem("quantity");

            if(Integer.parseInt(qtyAttrNode.getNodeValue()) > 0) {
            	return false;
            }
        }
        
        return true;
    }
    
    public void printStorageStatistics() {
    	NodeList products = document.getElementsByTagName("product");
    	
        for(int i = 0; i < products.getLength(); i++) {
        	Node product = products.item(i);
        	Node nameAttrNode = product.getAttributes().getNamedItem("name");
        	Node qtyAttrNode = product.getAttributes().getNamedItem("quantity");
        	String name = nameAttrNode.getNodeValue();
        	String qty = qtyAttrNode.getNodeValue();

        	System.out.print(name + ": " + qty + " ");
        }
    }
}
