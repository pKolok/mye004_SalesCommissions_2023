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

import output.ReceiptXmlFileAppender;

public class FileAppenderXMLTest {

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
		txtExpected += "        <Kind>Coat</Kind>\n";
		txtExpected += "        <Sales>2000</Sales>\n";
		txtExpected += "        <Items>3</Items>\n";
		txtExpected += "        <Company>Umbrella</Company>\n";
		txtExpected += "        <Country>US</Country>\n";
		txtExpected += "        <City>Racoon</City>\n";
		txtExpected += "        <Street>Underground ave</Street>\n";
		txtExpected += "        <Number>-10</Number>\n";
		txtExpected += "    </Receipt>\n";
		txtExpected += "</Agent>\n";
		
		String txtFileRead = "";

		ReceiptXmlFileAppender fileAppenderXML = new ReceiptXmlFileAppender();
		File file = new File(fileName);
		fileAppenderXML.setFileToAppend(file);
		fileAppenderXML.setReceiptID("1234");
		fileAppenderXML.setDate("10/10/2010");
		fileAppenderXML.setSales("2000");
		fileAppenderXML.setKind("Coat");
		fileAppenderXML.setItems("3");
		fileAppenderXML.setCompany("Umbrella");
		fileAppenderXML.setCountry("US");
		fileAppenderXML.setCity("Racoon");
		fileAppenderXML.setStreet("Underground ave");
		fileAppenderXML.setNumber("-10");
		fileAppenderXML.appendFile();
		
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