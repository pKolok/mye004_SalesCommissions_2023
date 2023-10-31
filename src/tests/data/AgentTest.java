package tests.data;

import static org.junit.Assert.*;

import data.Representative;
import data.Receipt;
import data.SaleItem;
import output.ReceiptTxtFileAppender;
import output.ReceiptXmlFileAppender;

import org.junit.Test;

public class AgentTest {

	// ---------------------------- Get and Set Name ---------------------------
	@Test
	public void testGetAndSetNameHappyDay() {
		Representative representative = new Representative();
		representative.setName("Petros");
		assertEquals("Petros", representative.getName());
	}
	
	@Test
	public void testGetAndSetNameEmptyString() {
		Representative representative = new Representative();
		representative.setName("");
		assertEquals("", representative.getName());
	}

	@Test
	public void testGetAndSetNameNull() {
		Representative representative = new Representative();
		representative.setName(null);
		assertEquals(null, representative.getName());
	}

	// ---------------------------- Get and Set AFM ----------------------------
	@Test
	public void testGetAndSetAFMHappyDay() {
		Representative representative = new Representative();
		representative.setAfm("1234567fgdf");
		assertEquals("1234567fgdf", representative.getAfm());
	}
	
	@Test
	public void testGetAndSetAFMEmptyString() {
		Representative representative = new Representative();
		representative.setAfm("");
		assertEquals("", representative.getAfm());
	}

	@Test
	public void testGetAndSetAFMNull() {
		Representative representative = new Representative();
		representative.setAfm(null);
		assertEquals(null, representative.getAfm());
	}
	
	// -------------------- Set File type, Get FileAppender --------------------
	@Test
	public void testGetAndSetFileTypeTXTHappyDay() {
		Representative representative = new Representative();
		representative.setFileType("TXT");
		assertTrue(representative.getFileAppender() instanceof ReceiptTxtFileAppender);
	}
	
	@Test
	public void testGetAndSetFileTypeXMLHappyDay() {
		Representative representative = new Representative();
		representative.setFileType("XML");
		assertTrue(representative.getFileAppender() instanceof ReceiptXmlFileAppender);
	}
	
	@Test
	public void testGetAndSetFileTypeEmptyString() {
		Representative representative = new Representative();
		representative.setFileType("");
		assertEquals(representative.getFileAppender(), null);
	}
	
	@Test
	public void testGetAndSetFileTypeRandomString() {
		Representative representative = new Representative();
		representative.setFileType("mpla");
		assertEquals(representative.getFileAppender(), null);
	}
	
	// ------------------------- Calculate total sales -------------------------
	@Test
	public void testCalculateTotalSalesHappyDay() {
		Representative representative = new Representative();
		Receipt receipt1 = new Receipt();
		representative.getReceipts().add(receipt1);
		receipt1.setSales(20.05);
		Receipt receipt2 = new Receipt();
		receipt2.setSales(10.95);
		representative.getReceipts().add(receipt2);
		assertEquals(representative.calculateTotalSales(), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateTotalSalesPositiveAndNegativeSales() {
		Representative representative = new Representative();
		Receipt receipt1 = new Receipt();
		receipt1.setSales(20.95);
		representative.getReceipts().add(receipt1);
		Receipt receipt2 = new Receipt();
		receipt2.setSales(-10.05);
		representative.getReceipts().add(receipt2);
		assertEquals(representative.calculateTotalSales(), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateTotalSalesNegativeSales() {
		Representative representative = new Representative();
		Receipt receipt1 = new Receipt();
		receipt1.setSales(-5.95);
		representative.getReceipts().add(receipt1);
		Receipt receipt2 = new Receipt();
		receipt2.setSales(-10.05);
		representative.getReceipts().add(receipt2);
		assertEquals(representative.calculateTotalSales(), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateTotalSalesZeroSales() {
		Representative representative = new Representative();
		assertEquals(representative.calculateTotalSales(), 0.0, 0.01);
	}
	
	// ------------------------- Calculate total items -------------------------
	@Test
	public void testCalculateTotalItemsHappyDay() {
		Representative representative = new Representative();
		Receipt receipt1 = new Receipt();
		representative.getReceipts().add(receipt1);
		receipt1.setItems(2);
		Receipt receipt2 = new Receipt();
		receipt2.setItems(3);
		representative.getReceipts().add(receipt2);
		assertEquals(representative.calculateTotalItems(), 5);
	}
	
	@Test
	public void testCalculateTotalItemsPositiveAndNegativeItemes() {
		Representative representative = new Representative();
		Receipt receipt1 = new Receipt();
		receipt1.setItems(-20);
		representative.getReceipts().add(receipt1);
		Receipt receipt2 = new Receipt();
		receipt2.setItems(+10);
		representative.getReceipts().add(receipt2);
		assertEquals(representative.calculateTotalItems(), -10);
	}
	
	@Test
	public void testCalculateTotalItemsNegativeItemes() {
		Representative representative = new Representative();
		Receipt receipt1 = new Receipt();
		receipt1.setItems(-5);
		representative.getReceipts().add(receipt1);
		Receipt receipt2 = new Receipt();
		receipt2.setItems(-10);
		representative.getReceipts().add(receipt2);
		assertEquals(representative.calculateTotalItems(), -15);
	}
	
	@Test
	public void testCalculateTotalItemsZeroItems() {
		Representative representative = new Representative();
		assertEquals(representative.calculateTotalItems(), 0);
	}
	
	// ------------------------- Calculate skirt sales -------------------------
	@Test
	public void testCalculateSkirtSalesHappyDay() {
		Representative representative = new Representative();
		Receipt skirt1 = new Receipt(SaleItem.SKIRT);
		representative.getReceipts().add(skirt1);
		skirt1.setSales(20.05);
		Receipt skirt2 = new Receipt(SaleItem.SKIRT);
		skirt2.setSales(10.95);
		representative.getReceipts().add(skirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SKIRT), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateSkirtSalesPositiveAndNegativeSales() {
		Representative representative = new Representative();
		Receipt skirt1 = new Receipt(SaleItem.SKIRT);
		skirt1.setSales(20.95);
		representative.getReceipts().add(skirt1);
		Receipt skirt2 = new Receipt(SaleItem.SKIRT);
		skirt2.setSales(-10.05);
		representative.getReceipts().add(skirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SKIRT), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateSkirtSalesNegativeSales() {
		Representative representative = new Representative();
		Receipt skirt1 = new Receipt(SaleItem.SKIRT);
		skirt1.setSales(-5.95);
		representative.getReceipts().add(skirt1);
		Receipt skirt2 = new Receipt(SaleItem.SKIRT);
		skirt2.setSales(-10.05);
		representative.getReceipts().add(skirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SKIRT), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateSkirtSalesZeroSales() {
		Representative representative = new Representative();
		assertEquals(representative.calculateItemSales(SaleItem.SKIRT), 0.0, 0.01);
	}
	
	// ------------------------- Calculate coat sales -------------------------
	@Test
	public void testCalculateCoatSalesHappyDay() {
		Representative representative = new Representative();
		Receipt coat1 = new Receipt(SaleItem.COAT);
		representative.getReceipts().add(coat1);
		coat1.setSales(20.05);
		Receipt coat2 = new Receipt(SaleItem.COAT);
		coat2.setSales(10.95);
		representative.getReceipts().add(coat2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.COAT), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateCoatSalesPositiveAndNegativeSales() {
		Representative representative = new Representative();
		Receipt coat1 = new Receipt(SaleItem.COAT);
		coat1.setSales(20.95);
		representative.getReceipts().add(coat1);
		Receipt coat2 = new Receipt(SaleItem.COAT);
		coat2.setSales(-10.05);
		representative.getReceipts().add(coat2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.COAT), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateCoatSalesNegativeSales() {
		Representative representative = new Representative();
		Receipt coat1 = new Receipt(SaleItem.COAT);
		coat1.setSales(-5.95);
		representative.getReceipts().add(coat1);
		Receipt coat2 = new Receipt(SaleItem.COAT);
		coat2.setSales(-10.05);
		representative.getReceipts().add(coat2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.COAT), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateCoatSalesZeroSales() {
		Representative representative = new Representative();
		assertEquals(representative.calculateItemSales(SaleItem.COAT), 0.0, 0.01);
	}
	
	// ------------------------- Calculate trouser sales -------------------------
	@Test
	public void testCalculateTrouserSalesHappyDay() {
		Representative representative = new Representative();
		Receipt trousers1 = new Receipt(SaleItem.TROUSERS);
		representative.getReceipts().add(trousers1);
		trousers1.setSales(20.05);
		Receipt trousers2 = new Receipt(SaleItem.TROUSERS);
		trousers2.setSales(10.95);
		representative.getReceipts().add(trousers2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.TROUSERS), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateTrouserSalesPositiveAndNegativeSales() {
		Representative representative = new Representative();
		Receipt trousers1 = new Receipt(SaleItem.TROUSERS);
		trousers1.setSales(20.95);
		representative.getReceipts().add(trousers1);
		Receipt trousers2 = new Receipt(SaleItem.TROUSERS);
		trousers2.setSales(-10.05);
		representative.getReceipts().add(trousers2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.TROUSERS), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateTrouserSalesNegativeSales() {
		Representative representative = new Representative();
		Receipt trousers1 = new Receipt(SaleItem.TROUSERS);
		trousers1.setSales(-5.95);
		representative.getReceipts().add(trousers1);
		Receipt trousers2 = new Receipt(SaleItem.TROUSERS);
		trousers2.setSales(-10.05);
		representative.getReceipts().add(trousers2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.TROUSERS), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateTrouserSalesZeroSales() {
		Representative representative = new Representative();
		assertEquals(representative.calculateItemSales(SaleItem.TROUSERS), 0.0, 0.01);
	}
	
	// ------------------------- Calculate shirt sales -------------------------
	@Test
	public void testCalculateShirtSalesHappyDay() {
		Representative representative = new Representative();
		Receipt shirt1 = new Receipt(SaleItem.SHIRT);
		representative.getReceipts().add(shirt1);
		shirt1.setSales(20.05);
		Receipt shirt2 = new Receipt(SaleItem.SHIRT);
		shirt2.setSales(10.95);
		representative.getReceipts().add(shirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SHIRT), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateShirtSalesPositiveAndNegativeSales() {
		Representative representative = new Representative();
		Receipt shirt1 = new Receipt(SaleItem.SHIRT);
		shirt1.setSales(20.95);
		representative.getReceipts().add(shirt1);
		Receipt shirt2 = new Receipt(SaleItem.SHIRT);
		shirt2.setSales(-10.05);
		representative.getReceipts().add(shirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SHIRT), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateShirtSalesNegativeSales() {
		Representative representative = new Representative();
		Receipt shirt1 = new Receipt(SaleItem.SHIRT);
		shirt1.setSales(-5.95);
		representative.getReceipts().add(shirt1);
		Receipt shirt2 = new Receipt(SaleItem.SHIRT);
		shirt2.setSales(-10.05);
		representative.getReceipts().add(shirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateItemSales(SaleItem.SHIRT), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateShirtSalesZeroSales() {
		Representative representative = new Representative();
		assertEquals(representative.calculateItemSales(SaleItem.SHIRT), 0.0, 0.01);
	}
	
	// ------------------------- Calculate commission --------------------------
	@Test
	public void testCalculateCommissionLessThan6000() {
		Representative representative = new Representative();
		Receipt skirt = new Receipt(SaleItem.SKIRT);
		representative.getReceipts().add(skirt);
		skirt.setSales(1000);
		Receipt coat = new Receipt(SaleItem.COAT);
		representative.getReceipts().add(coat);
		coat.setSales(500);
		Receipt trouser = new Receipt(SaleItem.TROUSERS);
		representative.getReceipts().add(trouser);
		trouser.setSales(1000);
		Receipt shirt = new Receipt(SaleItem.SHIRT);
		representative.getReceipts().add(shirt);
		shirt.setSales(500);
		Receipt receipt = new Receipt();
		receipt.setSales(1000);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 0.0, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess6000() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(6000);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 0.0, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess6001() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(6001);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 1*0.1, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess8000() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(8000);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 2000*0.1, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess10000() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(10000);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 4000*0.1, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess10001() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(10001);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 1000+1*0.15, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess20000() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(20000);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 1000+10000*0.15, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess40000() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(40000);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 1000+30000*0.15, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess40001() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(40001);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 1000+4500+1*0.2, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess80000() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(80000);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 1000+4500+40000*0.2, 0.01);
	}
	
	@Test
	public void testCalculateCommissionZero() {
		Representative representative = new Representative();
		assertEquals(representative.calculateCommission(), 0.0, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLessNegative() {
		Representative representative = new Representative();
		Receipt receipt = new Receipt();
		receipt.setSales(-30000);
		representative.getReceipts().add(receipt);
		assertEquals(representative.calculateCommission(), 0.0, 0.01);
	}
	
	
}
