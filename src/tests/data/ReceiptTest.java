package tests.data;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Company;
import data.Receipt;
import data.SaleItem;

public class ReceiptTest {

	// ---------------------------- Get Company --------------------------------
	@Test
	public void testGetCompanyHappyDay() {
		Receipt receipt = new Receipt();
		assertTrue(receipt.getCompany() instanceof Company);
	}

	// ----------------------------- Get Kind ----------------------------------
	@Test
	public void testGetKindHappyDay() {
		Receipt receipt = new Receipt();
		assertEquals(receipt.getKind(), SaleItem.OTHER);
	}
	
	// ---------------------------- Get and Set Sales --------------------------
	@Test
	public void testGetAndSetSalesHappyDay() {
		Receipt receipt = new Receipt();
		receipt.setSales(500.54);
		assertEquals(receipt.getSales(), 500.54, 0.01);
	}
	
	@Test
	public void testGetAndSetSalesZero() {
		Receipt receipt = new Receipt();
		receipt.setSales(0);
		assertEquals(receipt.getSales(), 0.0, 0.01);
	}
	
	@Test
	public void testGetAndSetSalesNegative() {
		Receipt receipt = new Receipt();
		receipt.setSales(-25.980);
		assertEquals(receipt.getSales(), -25.980, 0.01);
	}
	
	// ---------------------------- Get and Set Items --------------------------
	@Test
	public void testGetAndSetItemsHappyDay() {
		Receipt receipt = new Receipt();
		receipt.setItems(200);
		assertEquals(receipt.getItems(), 200);
	}
	
	@Test
	public void testGetAndSetItemsZero() {
		Receipt receipt = new Receipt();
		receipt.setItems(0);
		assertEquals(receipt.getItems(), 0);
	}
	
	@Test
	public void testGetAndSetItemsNegative() {
		Receipt receipt = new Receipt();
		receipt.setItems(-35);
		assertEquals(receipt.getItems(), -35);
	}
	
	// ------------------------- Get and Set Receipt ID ------------------------
	@Test
	public void testGetAndSetReceiptIDHappyDay() {
		Receipt receipt = new Receipt();
		receipt.setReceiptID(200);
		assertEquals(receipt.getReceiptID(), 200);
	}
	
	@Test
	public void testGetAndSetReceiptIDZero() {
		Receipt receipt = new Receipt();
		receipt.setReceiptID(0);
		assertEquals(receipt.getReceiptID(), 0);
	}
	
	@Test
	public void testGetAndSetReceiptIDNegative() {
		Receipt receipt = new Receipt();
		receipt.setReceiptID(-35);
		assertEquals(receipt.getReceiptID(), -35);
	}
	
	// ---------------------------- Get and Set Date ---------------------------
	@Test
	public void testGetAndSetDateHappyDay() {
		Receipt receipt = new Receipt();
		receipt.setDate("10-10-2010");
		assertEquals(receipt.getDate(), "10-10-2010");
	}
	
	@Test
	public void testGetAndSetDateEmptyString() {
		Receipt receipt = new Receipt();
		receipt.setDate("");
		assertEquals(receipt.getDate(), "");
	}
	
	@Test
	public void testGetAndSetDateNull() {
		Receipt receipt = new Receipt();
		receipt.setDate(null);
		assertEquals(receipt.getDate(), null);
	}
	
}