package tests.data;

import static org.junit.Assert.*;

import java.io.File;

import data.Representative;
import data.enums.SaleItem;
import data.Address;
import data.Company;
import data.Receipt;
import output.ReceiptTxtFileAppender;

import org.junit.Before;
import org.junit.Test;

public class RepresentativeTest {
	
	private Address address;
	private Company company;
	private ReceiptTxtFileAppender fileAppender = 
			new ReceiptTxtFileAppender(new File(""));
	
	@Before
	public void setUp() throws Exception {
		address = new Address("", "", "", 0);
		company = new Company("", address);
	}

	// -------------------------------- Get Name -------------------------------
	@Test
	public void testGetAndSetNameHappyDay() {
		Representative representative = new Representative("Petros", "", 
				fileAppender);
		assertEquals("Petros", representative.getName());
	}
	
	@Test
	public void testGetAndSetNameEmptyString() {
		Representative representative = new Representative("", "", 
				fileAppender);
		assertEquals("", representative.getName());
	}

	@Test
	public void testGetAndSetNameNull() {
		Representative representative = new Representative(null, "", 
				fileAppender);
		assertEquals(null, representative.getName());
	}

	// -------------------------------- Get AFM --------------------------------
	@Test
	public void testGetAndSetAFMHappyDay() {
		Representative representative = new Representative(
				"Petros", "1234567fgdf", fileAppender);
		assertEquals("1234567fgdf", representative.getAfm());
	}
	
	@Test
	public void testGetAndSetAFMEmptyString() {
		Representative representative = new Representative("Petros", "",
				fileAppender);
		assertEquals("", representative.getAfm());
	}

	@Test
	public void testGetAndSetAFMNull() {
		Representative representative = new Representative("Petros", null,
				fileAppender);
		assertEquals(null, representative.getAfm());
	}
	
	//----------------------- addRepresentativeReceipt -------------------------
	@Test
	public void testAddRepresentativeReceiptHappyDay() {
		Representative representative = new Representative("", "", fileAppender);
		Address address = new Address("Greece", "Ioannina", "Pyrsinella", 120);
		Company company = new Company("MyCompany", address);
		Receipt receipt = new Receipt(1, "23/11/2023", 230.4, "Coats", 10, 
				company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateTotalSales(), 230.4, 0.01);
		assertEquals(representative.calculateTotalItems(), 10);
		assertEquals(representative.calculateItemSales(SaleItem.COAT), 230.4,
				0.01);
	}
	
	// ------------------------- Calculate total sales -------------------------
	@Test
	public void testCalculateTotalSalesHappyDay() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt1 = new Receipt(0, "", 20.05, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt1);
		Receipt receipt2 = new Receipt(0, "", 10.95, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt2);
		assertEquals(representative.calculateTotalSales(), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateTotalSalesPositiveAndNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt1 = new Receipt(0, "", 20.05, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt1);
		Receipt receipt2 = new Receipt(0, "", -10.95, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt2);
		assertEquals(representative.calculateTotalSales(), 9.1, 0.01);
	}
	
	@Test
	public void testCalculateTotalSalesNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt1 = new Receipt(0, "", -5.95, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt1);
		Receipt receipt2 = new Receipt(0, "", -10.05, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt2);
		assertEquals(representative.calculateTotalSales(), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateTotalSalesZeroSales() {
		Representative representative = new Representative("", "", fileAppender);
		assertEquals(representative.calculateTotalSales(), 0.0, 0.01);
	}
	
	// ------------------------- Calculate total items -------------------------
	@Test
	public void testCalculateTotalItemsHappyDay() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt1 = new Receipt(0, "", 0.0, "Random", 2, company);
		representative.addRepresentativeReceipt(receipt1);
		Receipt receipt2 = new Receipt(0, "", 0.0, "Random", 3, company);
		representative.addRepresentativeReceipt(receipt2);
		assertEquals(representative.calculateTotalItems(), 5);
	}
	
	@Test
	public void testCalculateTotalItemsPositiveAndNegativeItemes() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt1 = new Receipt(0, "", 0.0, "Random", -20, company);
		representative.addRepresentativeReceipt(receipt1);
		Receipt receipt2 = new Receipt(0, "", 0.0, "Random", 10, company);
		representative.addRepresentativeReceipt(receipt2);
		assertEquals(representative.calculateTotalItems(), -10);
	}
	
	@Test
	public void testCalculateTotalItemsNegativeItemes() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt1 = new Receipt(0, "", 0.0, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt1);
		Receipt receipt2 = new Receipt(0, "", 0.0, "Random", -10, company);
		representative.addRepresentativeReceipt(receipt2);
		assertEquals(representative.calculateTotalItems(), -15);
	}
	
	@Test
	public void testCalculateTotalItemsZeroItems() {
		Representative representative = new Representative("", "", fileAppender);
		assertEquals(representative.calculateTotalItems(), 0);
	}
	
	// ------------------------- Calculate skirt sales -------------------------
	@Test
	public void testCalculateSkirtSalesHappyDay() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt skirt1 = new Receipt(0, "", 20.05, "Skirts", -5, company);
		representative.addRepresentativeReceipt(skirt1);
		Receipt skirt2 = new Receipt(0, "", 10.95, "Skirts", -5, company);
		representative.addRepresentativeReceipt(skirt2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SKIRT), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateSkirtSalesPositiveAndNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt skirt1 = new Receipt(0, "", 20.95, "Skirts", -5, company);
		representative.addRepresentativeReceipt(skirt1);
		Receipt skirt2 = new Receipt(0, "", -10.05, "Skirts", -5, company);
		representative.addRepresentativeReceipt(skirt2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SKIRT), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateSkirtSalesNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt skirt1 = new Receipt(0, "", -5.95, "Skirts", -5, company);
		representative.addRepresentativeReceipt(skirt1);
		Receipt skirt2 = new Receipt(0, "", -10.05, "Skirts", -5, company);
		representative.addRepresentativeReceipt(skirt2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SKIRT), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateSkirtSalesZeroSales() {
		Representative representative = new Representative("", "", fileAppender);
		assertEquals(representative.calculateItemSales(SaleItem.SKIRT), 0.0, 0.01);
	}
	
	// ------------------------- Calculate coat sales -------------------------
	@Test
	public void testCalculateCoatSalesHappyDay() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt coat1 = new Receipt(0, "", 20.05, "Coats", -5, company);
		representative.addRepresentativeReceipt(coat1);
		Receipt coat2 = new Receipt(0, "", 10.95, "Coats", -5, company);
		representative.addRepresentativeReceipt(coat2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.COAT), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateCoatSalesPositiveAndNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt coat1 = new Receipt(0, "", 20.95, "Coats", -5, company);
		representative.addRepresentativeReceipt(coat1);
		Receipt coat2 = new Receipt(0, "", -10.05, "Coats", -5, company);
		representative.addRepresentativeReceipt(coat2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.COAT), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateCoatSalesNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt coat1 = new Receipt(0, "", -5.95, "Coats", -5, company);
		representative.addRepresentativeReceipt(coat1);
		Receipt coat2 = new Receipt(0, "", -10.05, "Coats", -5, company);
		representative.addRepresentativeReceipt(coat2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.COAT), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateCoatSalesZeroSales() {
		Representative representative = new Representative("", "", fileAppender);
		assertEquals(representative.calculateItemSales(SaleItem.COAT), 0.0, 0.01);
	}
	
	// ------------------------- Calculate trouser sales -------------------------
	@Test
	public void testCalculateTrouserSalesHappyDay() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt trousers1 = new Receipt(0, "", 20.05, "Trousers", -5,
				company);
		representative.addRepresentativeReceipt(trousers1);
		Receipt trousers2 = new Receipt(0, "", 10.95, "Trousers", -5,
				company);
		representative.addRepresentativeReceipt(trousers2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.TROUSERS), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateTrouserSalesPositiveAndNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt trousers1 = new Receipt(0, "", 20.95, "Trousers", -5,
				company);
		representative.addRepresentativeReceipt(trousers1);
		Receipt trousers2 = new Receipt(0, "", -10.05, "Trousers", -5,
				company);
		representative.addRepresentativeReceipt(trousers2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.TROUSERS), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateTrouserSalesNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt trousers1 = new Receipt(0, "", -5.95, "Trousers", -5,
				company);
		representative.addRepresentativeReceipt(trousers1);
		Receipt trousers2 = new Receipt(0, "", -10.05, "Trousers", -5,
				company);
		representative.addRepresentativeReceipt(trousers2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.TROUSERS), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateTrouserSalesZeroSales() {
		Representative representative = new Representative("", "", fileAppender);
		assertEquals(representative.calculateItemSales(SaleItem.TROUSERS), 0.0, 0.01);
	}
	
	// ------------------------- Calculate shirt sales -------------------------
	@Test
	public void testCalculateShirtSalesHappyDay() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt shirt1 = new Receipt(0, "", 20.05, "Shirts", -5, company);
		representative.addRepresentativeReceipt(shirt1);
		Receipt shirt2 = new Receipt(0, "", 10.95, "Shirts", -5, company);
		representative.addRepresentativeReceipt(shirt2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SHIRT), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateShirtSalesPositiveAndNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt shirt1 = new Receipt(0, "", 20.95, "Shirts", -5, company);
		representative.addRepresentativeReceipt(shirt1);
		Receipt shirt2 = new Receipt(0, "", -10.05, "Shirts", -5, company);
		representative.addRepresentativeReceipt(shirt2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SHIRT), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateShirtSalesNegativeSales() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt shirt1 = new Receipt(0, "", -5.95, "Shirts", -5, company);
		representative.addRepresentativeReceipt(shirt1);
		Receipt shirt2 = new Receipt(0, "", -10.05, "Shirts", -5, company);
		representative.addRepresentativeReceipt(shirt2);
		Receipt receipt = new Receipt(0, "", 10.95, "Random", -5, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SHIRT), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateShirtSalesZeroSales() {
		Representative representative = new Representative("", "", fileAppender);
		assertEquals(representative.calculateItemSales(SaleItem.SHIRT), 0.0, 0.01);
	}
	
	// ------------------------- Calculate commission --------------------------
	@Test
	public void testCalculateCommissionLessThan6000() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt skirt = new Receipt(0, "", 0.0, "Skirts", 1000, company);
		representative.addRepresentativeReceipt(skirt);
		Receipt coat = new Receipt(0, "", 0.0, "Coats", 500, company);
		representative.addRepresentativeReceipt(coat);
		Receipt trouser = new Receipt(0, "", 0.0, "Trousers", 1000,
				company);
		representative.addRepresentativeReceipt(trouser);
		Receipt shirt = new Receipt(0, "", 0.0, "Shirts", 500, company);
		representative.addRepresentativeReceipt(shirt);
		Receipt receipt = new Receipt(0, "", 0.0, "Random", 1000, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 0.0, 0.01);
	}
	
	@Test
	public void testCalculateCommission6000() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", 0.0, "Random", 6000, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 0.0, 0.01);
	}
	
	@Test
	public void testCalculateCommission6001() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", 6001, "Random", 0, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 1*0.1, 0.01);
	}
	
	@Test
	public void testCalculateCommission8000() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", 8000, "Random", 0, company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 2000*0.1, 0.01);
	}
	
	@Test
	public void testCalculateCommission10000() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", 10000, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 4000*0.1, 0.01);
	}
	
	@Test
	public void testCalculateCommission10001() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", 10001, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 1000+1*0.15, 0.01);
	}
	
	@Test
	public void testCalculateCommission20000() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", 20000, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 1000+10000*0.15, 0.01);
	}
	
	@Test
	public void testCalculateCommission40000() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", 40000, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 1000+30000*0.15, 0.01);
	}
	
	@Test
	public void testCalculateCommission40001() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", 40001, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 1000+4500+1*0.2, 0.01);
	}
	
	@Test
	public void testCalculateCommission80000() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", 80000, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 1000+4500+40000*0.2, 0.01);
	}
	
	@Test
	public void testCalculateCommissionZero() {
		Representative representative = new Representative("", "", fileAppender);
		assertEquals(representative.calculateCommission(), 0.0, 0.01);
	}
	
	@Test
	public void testCalculateCommissionNegative() {
		Representative representative = new Representative("", "", fileAppender);
		Receipt receipt = new Receipt(0, "", -30000, "Random", 0,
				company);
		representative.addRepresentativeReceipt(receipt);
		assertEquals(representative.calculateCommission(), 0.0, 0.01);
	}
	
	
}
