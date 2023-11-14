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

import data.Address;
import data.Company;
import data.Receipt;
import data.SaleItem;
import output.ReceiptXmlFileAppender;

public class ReceiptXmlFileAppenderTest {

	private String fileName = "src/tests/output/test_output_files/xmlFile.xml";
		
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
		String fileExpected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		fileExpected += "<Agent>\n";
		fileExpected += "    <Receipt>\n";
		fileExpected += "        <ReceiptID>1234</ReceiptID>\n";
		fileExpected += "        <Date>10/10/2010</Date>\n";
		fileExpected += "        <Kind>Coats</Kind>\n";
		fileExpected += "        <Sales>2000.0</Sales>\n";
		fileExpected += "        <Items>3</Items>\n";
		fileExpected += "        <Company>Umbrella</Company>\n";
		fileExpected += "        <Country>US</Country>\n";
		fileExpected += "        <City>Racoon</City>\n";
		fileExpected += "        <Street>Underground ave</Street>\n";
		fileExpected += "        <Number>-10</Number>\n";
		fileExpected += "    </Receipt>\n";
		fileExpected += "</Agent>\n";
		
		String fileRead = "";

		File file = new File(fileName);
		ReceiptXmlFileAppender fileAppender = new ReceiptXmlFileAppender(
				file);
		
		Address address = new Address("US", "Racoon", "Underground ave", -10);
		Company company = new Company("Umbrella", address);
		Receipt receipt = new Receipt(1234, "10/10/2010", 2000.0, 
				SaleItem.COAT, 3, company);
		
		fileAppender.appendFile(receipt);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line; 
			while ((line = reader.readLine()) != null) {
				fileRead += line + "\n";
			}
			reader.close();
			
			assertEquals(fileExpected, fileRead);
			
		} catch (FileNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}

}