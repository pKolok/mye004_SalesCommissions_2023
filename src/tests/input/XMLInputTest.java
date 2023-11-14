package tests.input;

import static org.junit.Assert.*;

import data.Representative;
import data.enums.SaleItem;
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
	public void testReadFileFourReceiptsHappyDay() {
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
	
}