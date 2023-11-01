package tests.output;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import data.Receipt;
import data.SaleItem;
import output.ReceiptXmlFileAppender;

public class ReceiptXmlFileAppenderTest {

	private String fileName = "src/tests/data/txtFile.xml";
		
	@Before
	public void setUp() throws Exception {
		// Create a temporary xml file
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
			.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	   	Document document = documentBuilder.newDocument();
		// root element
		Element agentElem = document.createElement("Agent");
		document.appendChild(agentElem);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new File(fileName));
		transformer.transform(domSource, streamResult);
	}
	
	@After
    public void clean() throws IOException {
        // Delete the file once test over
        File file = new File(fileName);
		file.delete();
    }
	
	@Test
	public void testAppendFileHappyDay() {
		String txtExpected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		txtExpected += "<Agent>\n";
		txtExpected += "    <Receipt>\n";
		txtExpected += "        <ReceiptID>1234</ReceiptID>\n";
		txtExpected += "        <Date>10/10/2010</Date>\n";
		txtExpected += "        <Kind>COAT</Kind>\n";
		txtExpected += "        <Sales>2000.0</Sales>\n";
		txtExpected += "        <Items>3</Items>\n";
		txtExpected += "        <Company>Umbrella</Company>\n";
		txtExpected += "        <Country>US</Country>\n";
		txtExpected += "        <City>Racoon</City>\n";
		txtExpected += "        <Street>Underground ave</Street>\n";
		txtExpected += "        <Number>-10</Number>\n";
		txtExpected += "    </Receipt>\n";
		txtExpected += "</Agent>\n";
		
		String txtFileRead = "";

		File file = new File(fileName);
		ReceiptXmlFileAppender fileAppenderXML = new ReceiptXmlFileAppender(
				file);
		
		Receipt receipt = new Receipt(SaleItem.COAT);
		receipt.setReceiptID(1234);
		receipt.setDate("10/10/2010");
		receipt.setSales(2000);
		receipt.setItems(3);
		receipt.getCompany().setName("Umbrella");
		receipt.getCompany().getCompanyAddress().setCountry("US");
		receipt.getCompany().getCompanyAddress().setCity("Racoon");
		receipt.getCompany().getCompanyAddress().setStreet("Underground ave");
		receipt.getCompany().getCompanyAddress().setStreetNumber(-10);
		
		fileAppenderXML.appendFile(receipt);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line; 
			while ((line = reader.readLine()) != null) {
				txtFileRead += line + "\n";
			}
			reader.close();
			
//			System.out.println("Expected: " + txtExpected);
//			System.out.println("Received: " + txtFileRead);
			
			assertEquals(txtExpected, txtFileRead);
			
		} catch (FileNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}

}