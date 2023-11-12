package tests.input;

import static org.junit.Assert.*;

import data.Representative;
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
			
		Representative agent = xmlInput.getSalesRepresentative();
		
		assertEquals(agent.getName(), "Vassileios Zarras");
		assertEquals(agent.getAfm(), "130456097");
		
		assertEquals(agent.calculateTotalSales(), 2000, 0.01);
		assertEquals(agent.calculateTotalItems(), 10);
		assertEquals(agent.calculateItemSales(SaleItem.COAT), 2000, 0.01);
	}
	
	@Test
	public void testReadFileTwoReceiptsHappyDay() {
		File file = new File(
			"src/tests/input/test_input_files/xml_4Receipts.xml");
		XMLInput xmlInput = new XMLInput(file);
		xmlInput.readFile();
		
		Representative agent = xmlInput.getSalesRepresentative();
		
		assertEquals(agent.getName(), "Vassileios Zarras");
		assertEquals(agent.getAfm(), "130456097");

		assertEquals(agent.calculateTotalSales(), 12000, 0.01);
		assertEquals(agent.calculateTotalItems(), 85);
		assertEquals(agent.calculateItemSales(SaleItem.COAT), 2000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.SHIRT), 4000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.SKIRT), 1000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.TROUSERS), 5000, 0.01);
	}

	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceiptMissingName() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingName.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//		
//		assertEquals(agent.getName(), null);
//		assertTrue(agent.getReceipts().size() > 0);
//	}
//	
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceiptMissingAfm() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingAfm.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//		
//		assertEquals(agent.getAfm(), null);
//		assertTrue(agent.getReceipts().size() > 0);
//	}
//		
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceipMissingReceiptId() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingReceiptId.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getReceiptID(), null);
//	}
//	
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceipMissingDate() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingDate.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getDate(), null);
//	}
//	
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceipMissingSales() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingSales.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getSales(), null);
//	}
//	
//	// TODO: triggers dialog message 
//	@Test
//	public void testReadFileOneReceipMissingKind() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingKind.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getKind(), SaleItem.OTHER);
//	}
//	
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceipMissingItems() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingItems.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getItems(), null);
//	}
//	
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceipMissingCompanyName() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingCompany.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getCompany(), null);
//	}
//	
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceipMissingCountry() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingCountry.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getCompany()
//				.getCompanyAddress().getCountry(), null);
//	}
//	
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceipMissingCity() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingCity.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getCompany()
//				.getCompanyAddress().getCity(), null);
//
//	}
//	
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceipMissingStreet() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingStreet.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getCompany()
//				.getCompanyAddress().getStreet(), null);
//	}
//	
//	// TODO: triggers dialog message
//	@Test
//	public void testReadFileOneReceipMissingStreetNumber() {
//		File file = new File(
//			"src/tests/input/test_input_files/xml_1Receipt_MissingNumber.xml");
//		XMLInput xmlInput = new XMLInput(file);
//		xmlInput.readFile();
//		
//		Representative agent = xmlInput.getAgent();
//
//		assertEquals(agent.getReceipts().get(0).getCompany()
//				.getCompanyAddress().getStreetNumber(), null);
//	}
	
}