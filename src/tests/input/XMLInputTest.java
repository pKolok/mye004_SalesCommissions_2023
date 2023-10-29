package tests.input;

import static org.junit.Assert.*;

import data.Agent;
import data.Receipt;
import data.SaleItem;
import input.XMLInput;

import java.io.File;
import org.junit.Test;

public class XMLInputTest {

	@Test
	public void testReadFileOneReceiptHappyDay() {
		File file = new File(
				"src/tests/input/test_input_files/xml_1Receipt.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
			
		Agent agent = xmlInput.getAgent();
		
		assertEquals(agent.getName(), "Vassileios Zarras");
		assertEquals(agent.getAfm(), "130456097");
		assertEquals(agent.getReceipts().size(), 1);
		
		Receipt receipt = agent.getReceipts().get(0);
		
		assertEquals(receipt.getReceiptID(), 1);
		assertEquals(receipt.getDate(), "25/2/2014");
		assertEquals(receipt.getKind(), SaleItem.COAT);
		assertEquals(receipt.getSales(), 2000, 0.01);
		assertEquals(receipt.getItems(), 10);
		assertEquals(receipt.getCompany().getName(), "Hand Made Clothes");
		assertEquals(
			receipt.getCompany().getCompanyAddress().getCountry(), "Greece");
		assertEquals(
				receipt.getCompany().getCompanyAddress().getCity(), "Ioannina");
		assertEquals(
				receipt.getCompany().getCompanyAddress().getStreet(), "Kaloudi");
		assertEquals(
				receipt.getCompany().getCompanyAddress().getStreetNumber(), 10);
	}
	
	@Test
	public void testReadFileTwoReceiptsHappyDay() {
		File file = new File(
			"src/tests/input/test_input_files/xml_4Receipts.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();
		
		assertEquals(agent.getName(), "Vassileios Zarras");
		assertEquals(agent.getAfm(), "130456097");
		assertEquals(agent.getReceipts().size(), 4);
		
		Receipt receipt1 = agent.getReceipts().get(0);
		assertEquals(receipt1.getReceiptID(), 1);
		assertEquals(receipt1.getDate(), "25/2/2014");
		assertEquals(receipt1.getKind(), SaleItem.COAT);
		assertEquals(receipt1.getSales(), 2000, 0.01);
		assertEquals(receipt1.getItems(), 10);
		assertEquals(receipt1.getCompany().getName(), "Hand Made Clothes");
		assertEquals(
			receipt1.getCompany().getCompanyAddress().getCountry(), "Greece");
		assertEquals(
			receipt1.getCompany().getCompanyAddress().getCity(), "Ioannina");
		assertEquals(
			receipt1.getCompany().getCompanyAddress().getStreet(), "Kaloudi");
		assertEquals(
			receipt1.getCompany().getCompanyAddress().getStreetNumber(), 10);
		
		Receipt receipt2 = agent.getReceipts().get(1);
		assertEquals(receipt2.getReceiptID(), 3);
		assertEquals(receipt2.getDate(), "25/2/2015");
		assertEquals(receipt2.getKind(), SaleItem.SHIRT);
		assertEquals(receipt2.getSales(), 4000, 0.01);
		assertEquals(receipt2.getItems(), 20);
		assertEquals(receipt2.getCompany().getName(), "Hand Made Clothes2");
		assertEquals(
			receipt2.getCompany().getCompanyAddress().getCountry(), "Greece");
		assertEquals(
			receipt2.getCompany().getCompanyAddress().getCity(), "Trikala");
		assertEquals(
			receipt2.getCompany().getCompanyAddress().getStreet(), "Pyrsinella");
		assertEquals(
			receipt2.getCompany().getCompanyAddress().getStreetNumber(), 20);
		
		Receipt receipt3 = agent.getReceipts().get(2);
		assertEquals(receipt3.getReceiptID(), 4);
		assertEquals(receipt3.getDate(), "25/2/2016");
		assertEquals(receipt3.getKind(), SaleItem.SKIRT);
		assertEquals(receipt3.getSales(), 1000, 0.01);
		assertEquals(receipt3.getItems(), 5);
		assertEquals(receipt3.getCompany().getName(), "Hand Made Clothes3");
		assertEquals(
			receipt3.getCompany().getCompanyAddress().getCountry(), "Greece");
		assertEquals(
			receipt3.getCompany().getCompanyAddress().getCity(), "Preveza");
		assertEquals(
			receipt3.getCompany().getCompanyAddress().getStreet(), "Dwdwnhs");
		assertEquals(
			receipt3.getCompany().getCompanyAddress().getStreetNumber(), 30);
		
		Receipt receipt4 = agent.getReceipts().get(3);
		assertEquals(receipt4.getReceiptID(), 6);
		assertEquals(receipt4.getDate(), "25/2/2017");
		assertEquals(receipt4.getKind(), SaleItem.TROUSERS);
		assertEquals(receipt4.getSales(), 5000, 0.01);
		assertEquals(receipt4.getItems(), 50);
		assertEquals(receipt4.getCompany().getName(), "Hand Made Clothes4");
		assertEquals(
			receipt4.getCompany().getCompanyAddress().getCountry(), "Greece");
		assertEquals(
			receipt4.getCompany().getCompanyAddress().getCity(), "Parga");
		assertEquals(
			receipt4.getCompany().getCompanyAddress().getStreet(), "Ane3arthsias");
		assertEquals(
			receipt4.getCompany().getCompanyAddress().getStreetNumber(), 40);
	}

	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceiptMissingName() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingName.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();
		
		assertEquals(agent.getName(), null);
		assertTrue(agent.getReceipts().size() > 0);
	}
	
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceiptMissingAfm() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingAfm.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();
		
		assertEquals(agent.getAfm(), null);
		assertTrue(agent.getReceipts().size() > 0);
	}
		
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceipMissingReceiptId() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingReceiptId.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getReceiptID(), null);
	}
	
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceipMissingDate() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingDate.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getDate(), null);
	}
	
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceipMissingSales() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingSales.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getSales(), null);
	}
	
	// TODO: triggers dialog message 
	@Test
	public void testReadFileOneReceipMissingKind() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingKind.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getKind(), SaleItem.OTHER);
	}
	
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceipMissingItems() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingItems.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getItems(), null);
	}
	
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceipMissingCompanyName() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingCompany.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getCompany(), null);
	}
	
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceipMissingCountry() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingCountry.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getCompany()
				.getCompanyAddress().getCountry(), null);
	}
	
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceipMissingCity() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingCity.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getCompany()
				.getCompanyAddress().getCity(), null);

	}
	
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceipMissingStreet() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingStreet.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getCompany()
				.getCompanyAddress().getStreet(), null);
	}
	
	// TODO: triggers dialog message
	@Test
	public void testReadFileOneReceipMissingStreetNumber() {
		File file = new File(
			"src/tests/input/test_input_files/xml_1Receipt_MissingNumber.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Agent agent = xmlInput.getAgent();

		assertEquals(agent.getReceipts().get(0).getCompany()
				.getCompanyAddress().getStreetNumber(), null);
	}
	
}