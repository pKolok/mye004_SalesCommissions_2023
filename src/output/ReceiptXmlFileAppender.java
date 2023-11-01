package output;

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
import org.w3c.dom.Node;

public class ReceiptXmlFileAppender extends ReceiptFileAppender{

	private Document doc;
	private Element receiptElem;
	
	public ReceiptXmlFileAppender(File file) {
		super(file);
	}
	
	protected void openFile() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(fileToAppend);
			
			Node agent = doc.getFirstChild();
			
			receiptElem = doc.createElement("Receipt");
			agent.appendChild(receiptElem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void append(String label, String value) {
		Element element = doc.createElement(label);
		element.appendChild(doc.createTextNode(value));
		receiptElem.appendChild(element);
	}
	
	protected void closeFile() {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fileToAppend);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}