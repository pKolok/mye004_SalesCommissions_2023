package input;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data.Address;
import data.Company;
import data.Receipt;
import data.Representative;
import output.ReceiptXmlFileAppender;

public class XMLInput extends Input {
 
	private Document doc;
	
	public XMLInput(File receiptFileXML ){
		this.inputFile = receiptFileXML;	
	}
	
	protected void openFile() {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			
			doc = docBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Representative getRepresentative() {
		try {
            NodeList nodeLst = doc.getElementsByTagName("Agent");
			
        	String name = ((Element) nodeLst.item(0)).getElementsByTagName("Name").
        			item(0).getChildNodes().item(0).getNodeValue().trim();
        	String afm = ((Element) nodeLst.item(0)).getElementsByTagName("AFM").
        			item(0).getChildNodes().item(0).getNodeValue().trim();
        	
        	return new Representative(name, afm,
        			new ReceiptXmlFileAppender(inputFile));
        	  
        } catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	protected ArrayList<Receipt> getReceipts() {
		ArrayList<Receipt> receipts = new ArrayList<Receipt>();
		
		try {
			NodeList nodeLst = doc.getElementsByTagName("Agent");
			NodeList receiptsNodeList = ((Element) nodeLst.
	    			item(0)).getElementsByTagName("Receipt");
			
	        for (int i = 0; i < receiptsNodeList.getLength(); i++){
	        	int receiptID = Integer.parseInt(((Element) receiptsNodeList
	        			.item(i)).getElementsByTagName("ReceiptID").item(0)
	        			.getChildNodes().item(0).getNodeValue().trim());
	        	
	        	String date = ((Element) receiptsNodeList.item(i))
	        			.getElementsByTagName("Date").item(0).getChildNodes()
	        			.item(0).getNodeValue().trim();
				
	        	String kind = ((Element) receiptsNodeList.item(i))
	        			.getElementsByTagName("Kind").item(0).getChildNodes()
	        			.item(0).getNodeValue().trim();
				
	        	double sales = Double.parseDouble(((Element) receiptsNodeList
	        			.item(i)).getElementsByTagName("Sales").item(0)
	        			.getChildNodes().item(0).getNodeValue().trim());
	        	
				int items = Integer.parseInt(((Element) receiptsNodeList
						.item(i)).getElementsByTagName("Items").item(0)
						.getChildNodes().item(0).getNodeValue().trim());
	        	
				String companyName = ((Element) receiptsNodeList.item(i))
						.getElementsByTagName("Company").item(0).getChildNodes()
						.item(0).getNodeValue().trim();
	        	
				String companyCountry = ((Element) receiptsNodeList.item(i))
						.getElementsByTagName("Country").item(0).getChildNodes()
						.item(0).getNodeValue().trim();
	        	
				String companyCity = ((Element) receiptsNodeList.item(i))
						.getElementsByTagName("City").item(0).getChildNodes()
						.item(0).getNodeValue().trim();
	        	
				String companyStreet = ((Element) receiptsNodeList.item(i))
						.getElementsByTagName("Street").item(0).getChildNodes()
						.item(0).getNodeValue().trim();
	        	
				int companyStreetNumber = Integer.parseInt(
						((Element) receiptsNodeList.item(i))
						.getElementsByTagName("Number").item(0).getChildNodes()
						.item(0).getNodeValue().trim());
	        	
				Address address = new Address(companyCountry, companyCity,
		    			companyStreet, companyStreetNumber);
		    	Company company = new Company(companyName, address);
		    	Receipt receipt = new Receipt(receiptID, date, sales, kind,
		    			items, company);
		    	
		    	receipts.add(receipt);
	        }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return receipts;
	}
	
	protected void closeFile() {
		// Do nothing
	}
    
}