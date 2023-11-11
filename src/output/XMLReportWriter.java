package output;

import data.Representative;
import data.SaleItem;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLReportWriter extends ReportWriter{
	
	private Document document;
	Element agentElem;

	public XMLReportWriter(Representative representative, String fileName){
		this.representative = representative;
		this.fileName = fileName;
	}	
		
	@Override
	protected void createFile() {
        try {
        	 DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        	 DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        	 document = documentBuilder.newDocument();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	@Override
	protected void writeRepresentativeDetails() {
        try {
        	 // root element
        	 agentElem = document.createElement("Agent");
        	 document.appendChild(agentElem);
        	 
        	 Element name = document.createElement("Name");
        	 name.appendChild(document.createTextNode(representative.getName()));
        	 agentElem.appendChild(name);
        	 
        	 Element afm = document.createElement("AFM");
        	 afm.appendChild(document.createTextNode(representative.getAfm()));	
        	 agentElem.appendChild(afm);
        	 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	@Override
	protected void writeSalesSummary() {
        try {
        	 Element totalSales = document.createElement("TotalSales");
        	 totalSales.appendChild(document.createTextNode(Double.toString(
        			 representative.calculateTotalSales())));
        	 agentElem.appendChild(totalSales);
        	 
        	 Element totalItems = document.createElement("TotalItems");
        	 totalItems.appendChild(document.createTextNode(Double.toString(
        			 representative.calculateTotalItems())));
        	 agentElem.appendChild(totalItems);
        	 
        	 Element trouserSales = document.createElement("TrouserSales");
        	 trouserSales.appendChild(document.createTextNode(Float.toString(
        			 representative.calculateItemSales(SaleItem.TROUSERS))));
        	 agentElem.appendChild(trouserSales);
        	 
        	 Element shirtsSales = document.createElement("ShirtsSales");
        	 shirtsSales.appendChild(document.createTextNode(Float.toString(
        			 representative.calculateItemSales(SaleItem.SHIRT))));
        	 agentElem.appendChild(shirtsSales);
        	 
        	 Element coatsSales = document.createElement("CoatsSales");
        	 coatsSales.appendChild(document.createTextNode(Float.toString(
        			 representative.calculateItemSales(SaleItem.COAT))));
        	 agentElem.appendChild(coatsSales);
        	 
        	 Element skirtsSales = document.createElement("SkirtsSales");
        	 skirtsSales.appendChild(document.createTextNode(Float.toString(
        			 representative.calculateItemSales(SaleItem.SKIRT))));
        	 agentElem.appendChild(skirtsSales);
        	 
        	 Element commission = document.createElement("Commission");
        	 commission.appendChild(document.createTextNode(Double.toString(
        			 representative.calculateCommission())));
        	 agentElem.appendChild(commission);
        	 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	@Override
	protected void closeFile() {
        try {
        	 TransformerFactory transformerFactory = TransformerFactory
        			 .newInstance();
        	 Transformer transformer = transformerFactory.newTransformer();
        	 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        	 transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        	 DOMSource domSource = new DOMSource(document);
        	 StreamResult streamResult = new StreamResult(
        			 new File(this.fileName));
        	 transformer.transform(domSource, streamResult);
        	 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

}