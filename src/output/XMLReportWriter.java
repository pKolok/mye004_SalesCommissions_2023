package output;

import data.Representative;
import data.enums.SaleItem;

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
	Element representativeElem;

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
        	 representativeElem = document.createElement("Agent");
        	 document.appendChild(representativeElem);
        	 
        	 Element name = document.createElement("Name");
        	 name.appendChild(document.createTextNode(representative.getName()));
        	 representativeElem.appendChild(name);
        	 
        	 Element afm = document.createElement("AFM");
        	 afm.appendChild(document.createTextNode(representative.getAfm()));	
        	 representativeElem.appendChild(afm);
        	 
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
        	 representativeElem.appendChild(totalSales);
        	 
        	 Element totalItems = document.createElement("TotalItems");
        	 totalItems.appendChild(document.createTextNode(Double.toString(
        			 representative.calculateTotalItems())));
        	 representativeElem.appendChild(totalItems);
        	 
        	 Element trouserSales = document.createElement("TrouserSales");
        	 trouserSales.appendChild(document.createTextNode(Float.toString(
        			 representative.calculateItemSales(SaleItem.TROUSERS))));
        	 representativeElem.appendChild(trouserSales);
        	 
        	 Element shirtsSales = document.createElement("ShirtsSales");
        	 shirtsSales.appendChild(document.createTextNode(Float.toString(
        			 representative.calculateItemSales(SaleItem.SHIRT))));
        	 representativeElem.appendChild(shirtsSales);
        	 
        	 Element coatsSales = document.createElement("CoatsSales");
        	 coatsSales.appendChild(document.createTextNode(Float.toString(
        			 representative.calculateItemSales(SaleItem.COAT))));
        	 representativeElem.appendChild(coatsSales);
        	 
        	 Element skirtsSales = document.createElement("SkirtsSales");
        	 skirtsSales.appendChild(document.createTextNode(Float.toString(
        			 representative.calculateItemSales(SaleItem.SKIRT))));
        	 representativeElem.appendChild(skirtsSales);
        	 
        	 Element commission = document.createElement("Commission");
        	 commission.appendChild(document.createTextNode(Double.toString(
        			 representative.calculateCommission())));
        	 representativeElem.appendChild(commission);
        	 
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