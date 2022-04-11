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

import programtervezesi_mintak.core.ProductNotFoundException;
import programtervezesi_mintak.core.ProductOutOfStockException;


public class StorageController {
    private static final String STORAGE_LOCATION = "../resources/storage.xml";

    private static StorageController instance;
    
    private Document document;

    private StorageController() {
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

    public static StorageController getInstance() {
        if (instance == null) {
            instance = new StorageController();
        }

        return instance;
    }
    
    public void changeProductQuantity(String productName, int quantity) throws ProductNotFoundException {
    	Node product = getProductByName(productName);
    	
    	Node qtyAttrNode = product.getAttributes().getNamedItem("quantity");
    	
    	int oldQty = Integer.parseInt(qtyAttrNode.getNodeValue());
    	int newQty = oldQty + quantity;
    	
    	qtyAttrNode.setNodeValue(String.valueOf(newQty));
    }
    
    public void increaseProductQuantityByOne(String productName) throws ProductNotFoundException {
    	changeProductQuantity(productName, 1);
    }
    
    public void decreaseProductQuantityByOne(String productName)
    		throws ProductNotFoundException, ProductOutOfStockException {
    	if(getProductQuantity(productName) < 1) {
    		throw new ProductOutOfStockException();
    	}
    	
    	changeProductQuantity(productName, -1);
    }
    
    public Node getProductByName(String productName) throws ProductNotFoundException {
    	NodeList products = document.getElementsByTagName("product");
    	
        for(int i = 0; i < products.getLength(); i++) {
        	Node product = products.item(i);
        	Node nameAttrNode = product.getAttributes().getNamedItem("name");
        	
            if(nameAttrNode.getNodeValue() == productName) {
            	return product;
            }
        }
        
        throw new ProductNotFoundException();
    }
    
    public int getProductQuantity(String productName) throws ProductNotFoundException {
    	Node product = getProductByName(productName);
    	
    	Node qtyAttrNode = product.getAttributes().getNamedItem("quantity");
    	
    	return Integer.parseInt(qtyAttrNode.getNodeValue());
    }
}
