package tests.data;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Address;
import data.Company;
import data.Receipt;
import data.SaleItem;

public class ReceiptTest {

	// ---------------------------- Get Company --------------------------------
	@Test
	public void testGetCompanyHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, SaleItem.OTHER, 0, company);
		assertTrue(receipt.getCompany() instanceof Company);
	}

	// ----------------------------- Get Kind ----------------------------------
	@Test
	public void testGetKindHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, SaleItem.OTHER, 0, company);
		assertEquals(receipt.getKind(), SaleItem.OTHER);
	}
	
	@Test
	public void testGetKindStringShirtsHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, "Shirts", 0, company);
		assertEquals(receipt.getKind(), SaleItem.SHIRT);
	}
	
	@Test
	public void testGetKindStringSkirtsHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, "Skirts", 0, company);
		assertEquals(receipt.getKind(), SaleItem.SKIRT);
	}
	
	@Test
	public void testGetKindStringTrousersHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, "Trousers", 0, company);
		assertEquals(receipt.getKind(), SaleItem.TROUSERS);
	}
	
	@Test
	public void testGetKindStringCoatsHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, "Coats", 0, company);
		assertEquals(receipt.getKind(), SaleItem.COAT);
	}
	
	@Test
	public void testGetKindStringInvald() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, "Random", 0, company);
		assertEquals(receipt.getKind(), SaleItem.OTHER);
	}
	
	// -------------------------------- Get Sales ------------------------------
	@Test
	public void testGetAndSetSalesHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 500.54, SaleItem.OTHER, 0,
				company);
		assertEquals(receipt.getSales(), 500.54, 0.01);
	}
	
	@Test
	public void testGetAndSetSalesZero() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, SaleItem.OTHER, 0, company);
		assertEquals(receipt.getSales(), 0.0, 0.01);
	}
	
	@Test
	public void testGetAndSetSalesNegative() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", -25.980, SaleItem.OTHER, 0,
				company);
		assertEquals(receipt.getSales(), -25.980, 0.01);
	}
	
	// -------------------------------- Get Items ------------------------------
	@Test
	public void testGetAndSetItemsHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, SaleItem.OTHER, 200, company);
		assertEquals(receipt.getItems(), 200);
	}
	
	@Test
	public void testGetAndSetItemsZero() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, SaleItem.OTHER, 0, company);
		assertEquals(receipt.getItems(), 0);
	}
	
	@Test
	public void testGetAndSetItemsNegative() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, SaleItem.OTHER, -35, company);
		assertEquals(receipt.getItems(), -35);
	}
	
	// ----------------------------- Get Receipt ID ----------------------------
	@Test
	public void testGetAndSetReceiptIDHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(200, "", 0.0, SaleItem.OTHER, 0, company);
		assertEquals(receipt.getReceiptID(), 200);
	}
	
	@Test
	public void testGetAndSetReceiptIDZero() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, SaleItem.OTHER, 0, company);
		assertEquals(receipt.getReceiptID(), 0);
	}
	
	@Test
	public void testGetAndSetReceiptIDNegative() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(-35, "", 0.0, SaleItem.OTHER, 0, company);
		assertEquals(receipt.getReceiptID(), -35);
	}
	
	// -------------------------------- Get Date -------------------------------
	@Test
	public void testGetAndSetDateHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "10-10-2010", 0.0, SaleItem.OTHER, 0,
				company);
		assertEquals(receipt.getDate(), "10-10-2010");
	}
	
	@Test
	public void testGetAndSetDateEmptyString() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, "", 0.0, SaleItem.OTHER, 0, company);
		assertEquals(receipt.getDate(), "");
	}
	
	@Test
	public void testGetAndSetDateNull() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		Receipt receipt = new Receipt(0, null, 0.0, SaleItem.OTHER, 0, company);
		assertEquals(receipt.getDate(), null);
	}
	
}