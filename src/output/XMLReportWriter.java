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

	public XMLReportWriter(Representative a){
		agent = a;
	}	
		
	@Override
	protected void openFile() {
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
        	 name.appendChild(document.createTextNode(agent.getName()));
        	 agentElem.appendChild(name);
        	 
        	 Element afm = document.createElement("AFM");
        	 afm.appendChild(document.createTextNode(agent.getAfm()));	
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
        			 agent.calculateTotalSales())));
        	 agentElem.appendChild(totalSales);
        	 
        	 Element trouserSales = document.createElement("TrouserSales");
        	 trouserSales.appendChild(document.createTextNode(Float.toString(
        			 agent.calculateItemSales(SaleItem.TROUSERS))));
        	 agentElem.appendChild(trouserSales);
        	 
        	 Element skirtsSales = document.createElement("SkirtsSales");
        	 skirtsSales.appendChild(document.createTextNode(Float.toString(
        			 agent.calculateItemSales(SaleItem.SKIRT))));
        	 agentElem.appendChild(skirtsSales);
        	 
        	 Element shirtsSales = document.createElement("ShirtsSales");
        	 shirtsSales.appendChild(document.createTextNode(Float.toString(
        			 agent.calculateItemSales(SaleItem.SHIRT))));
        	 agentElem.appendChild(shirtsSales);
        	 
        	 Element coatsSales = document.createElement("CoatsSales");
        	 coatsSales.appendChild(document.createTextNode(Float.toString(
        			 agent.calculateItemSales(SaleItem.COAT))));
        	 agentElem.appendChild(coatsSales);
        	 
        	 Element commission = document.createElement("Commission");
        	 commission.appendChild(document.createTextNode(Double.toString(
        			 agent.calculateCommission())));
        	 agentElem.appendChild(commission);
        	 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	@Override
	protected void closeFile() {
		String fullPathName =  "/users/Nick/Desktop/Reports/" + agent.getAfm() 
			+ "_SALES.xml";
        try {
        	 TransformerFactory transformerFactory = TransformerFactory
        			 .newInstance();
        	 Transformer transformer = transformerFactory.newTransformer();
        	 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        	 transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        	 DOMSource domSource = new DOMSource(document);
        	 StreamResult streamResult = new StreamResult(
        			 new File(fullPathName));
        	 transformer.transform(domSource, streamResult);
        	 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

}