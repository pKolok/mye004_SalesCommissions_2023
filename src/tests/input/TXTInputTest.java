package tests.input;

import static org.junit.Assert.*;

import data.Representative;
import enums.SaleItem;
import input.TXTInput;

import java.io.File;
import org.junit.Test;

public class TXTInputTest {

	@Test
	public void testReadFileOneReceiptHappyDay() {
		File file = new File(
			"src/tests/input/test_input_files/txt_1Receipt.txt");
		TXTInput txtInput = new TXTInput(file);
		txtInput.readFile();
		
		Representative agent = txtInput.getSalesRepresentative();
		
		assertEquals(agent.getName(), "Apostolos Zarras");
		assertEquals(agent.getAfm(), "130456093");
		
		assertEquals(agent.calculateTotalSales(), 2000, 0.01);
		assertEquals(agent.calculateTotalItems(), 10);
		assertEquals(agent.calculateItemSales(SaleItem.COAT), 2000, 0.01);
	}
	
	@Test
	public void testReadFileFourReceiptsHappyDay() {
		File file = new File(
			"src/tests/input/test_input_files/txt_4Receipts.txt");
		TXTInput txtInput = new TXTInput(file);
		txtInput.readFile();
		
		Representative agent = txtInput.getSalesRepresentative();
		
		assertEquals(agent.getName(), "Apostolos Zarras");
		assertEquals(agent.getAfm(), "130456093");
		
		assertEquals(agent.calculateTotalSales(), 12000, 0.01);
		assertEquals(agent.calculateTotalItems(), 85);
		assertEquals(agent.calculateItemSales(SaleItem.COAT), 2000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.SHIRT), 4000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.SKIRT), 1000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.TROUSERS), 5000, 0.01);
	}

}