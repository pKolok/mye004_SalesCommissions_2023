package output;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ReceiptXmlFileAppender extends ReceiptFileAppender{

	public void appendFile(){

		try{
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(fileToAppend);
		
			Node agent = doc.getFirstChild();

			Element receiptElem = doc.createElement("Receipt");
			agent.appendChild(receiptElem);		
		
			Element receiptIDElem = doc.createElement("ReceiptID");
			receiptIDElem.appendChild(doc.createTextNode(
					receipt.getReceiptID() + ""));
			receiptElem.appendChild(receiptIDElem);

	       	Element dateElem = doc.createElement("Date");
	       	dateElem.appendChild(doc.createTextNode(receipt.getDate()));
	       	receiptElem.appendChild(dateElem);
       	
	       	Element kindElem = doc.createElement("Kind");
	       	kindElem.appendChild(doc.createTextNode(receipt.getKind().name()));
	       	receiptElem.appendChild(kindElem);
	       	
	       	Element salesElem = doc.createElement("Sales");
	       	salesElem.appendChild(doc.createTextNode(receipt.getSales() + ""));
	       	receiptElem.appendChild(salesElem);
	       	
	       	Element itemsElem = doc.createElement("Items");
	       	itemsElem.appendChild(doc.createTextNode(receipt.getItems() + ""));
	       	receiptElem.appendChild(itemsElem);
	       	
	       	Element companyElem = doc.createElement("Company");
			companyElem.appendChild(doc.createTextNode(receipt.getCompany()
					.getName()));
			receiptElem.appendChild(companyElem);
	       	
	       	Element countryElem = doc.createElement("Country");
	       	countryElem.appendChild(doc.createTextNode(receipt.getCompany()
	       			.getCompanyAddress().getCountry()));
	       	receiptElem.appendChild(countryElem);
	       	
	       	Element cityElem = doc.createElement("City");
	       	cityElem.appendChild(doc.createTextNode(receipt.getCompany()
	       			.getCompanyAddress().getCity()));
	       	receiptElem.appendChild(cityElem);
	       	
	       	Element streetElem = doc.createElement("Street");
	       	streetElem.appendChild(doc.createTextNode(receipt.getCompany()
	       			.getCompanyAddress().getStreet()));
	       	receiptElem.appendChild(streetElem);
	       	
	       	Element numberElem = doc.createElement("Number");
	       	numberElem.appendChild(doc.createTextNode(receipt.getCompany()
	       			.getCompanyAddress().getStreetNumber() + ""));
	       	receiptElem.appendChild(numberElem);
	    
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	   	 	transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fileToAppend);
			transformer.transform(source, result);

		} catch (Exception e){
			e.printStackTrace();
		}
	}

}