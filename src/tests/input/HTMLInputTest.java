package tests.input;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import data.Representative;
import data.SaleItem;
import input.HTMLInput;

public class HTMLInputTest {

	@Test
	public void testReadFileOneReceiptHappyDay() {
		File file = new File(
				"src/tests/input/test_input_files/html_1Receipt.html");
		HTMLInput xmlInput = new HTMLInput(file);
		xmlInput.readFile();
			
		Representative agent = xmlInput.getSalesRepresentative();
		
		assertEquals(agent.getName(), "Panagiotis");
		assertEquals(agent.getAfm(), "130456097");
		
		assertEquals(agent.calculateTotalSales(), 2000, 0.01);
		assertEquals(agent.calculateTotalItems(), 10);
		assertEquals(agent.calculateItemSales(SaleItem.COAT), 2000, 0.01);
	}
	
	@Test
	public void testReadFileFourReceiptsHappyDay() {
		File file = new File(
			"src/tests/input/test_input_files/html_4Receipts.html");
		HTMLInput xmlInput = new HTMLInput(file);
		xmlInput.readFile();
		
		Representative agent = xmlInput.getSalesRepresentative();
		
		assertEquals(agent.getName(), "Panagiotis");
		assertEquals(agent.getAfm(), "130456097");

		assertEquals(agent.calculateTotalSales(), 12000, 0.01);
		assertEquals(agent.calculateTotalItems(), 40);
		assertEquals(agent.calculateItemSales(SaleItem.COAT), 2000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.SHIRT), 4000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.SKIRT), 1000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.TROUSERS), 5000, 0.01);
	}
	
}