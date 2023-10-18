package tests.data;

import static org.junit.Assert.*;

import data.Agent;
import data.FileAppenderTXT;
import data.FileAppenderXML;
import data.Receipt;
import data.Skirt;
import data.Coat;
import data.Trouser;
import data.Shirt;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AgentTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// ---------------------------- Get and Set Name ---------------------------
	@Test
	public void testGetAndSetNameHappyDay() {
		Agent agent = new Agent();
		agent.setName("Petros");
		assertEquals("Petros", agent.getName());
	}
	
	@Test
	public void testGetAndSetNameEmptyString() {
		Agent agent = new Agent();
		agent.setName("");
		assertEquals("", agent.getName());
	}

	@Test
	public void testGetAndSetNameNull() {
		Agent agent = new Agent();
		agent.setName(null);
		assertEquals(null, agent.getName());
	}

	// ---------------------------- Get and Set AFM ----------------------------
	@Test
	public void testGetAndSetAFMHappyDay() {
		Agent agent = new Agent();
		agent.setAfm("1234567fgdf");
		assertEquals("1234567fgdf", agent.getAfm());
	}
	
	@Test
	public void testGetAndSetAFMEmptyString() {
		Agent agent = new Agent();
		agent.setAfm("");
		assertEquals("", agent.getAfm());
	}

	@Test
	public void testGetAndSetAFMNull() {
		Agent agent = new Agent();
		agent.setAfm(null);
		assertEquals(null, agent.getAfm());
	}
	
	// -------------------- Set File type, Get FileAppender --------------------
	@Test
	public void testGetAndSetFileTypeTXTHappyDay() {
		Agent agent = new Agent();
		agent.setFileType("TXT");
		assertTrue(agent.getFileAppender() instanceof FileAppenderTXT);
	}
	
	@Test
	public void testGetAndSetFileTypeXMLHappyDay() {
		Agent agent = new Agent();
		agent.setFileType("XML");
		assertTrue(agent.getFileAppender() instanceof FileAppenderXML);
	}
	
	@Test
	public void testGetAndSetFileTypeEmptyString() {
		Agent agent = new Agent();
		agent.setFileType("");
		assertEquals(agent.getFileAppender(), null);
	}
	
	@Test
	public void testGetAndSetFileTypeRandomString() {
		Agent agent = new Agent();
		agent.setFileType("mpla");
		assertEquals(agent.getFileAppender(), null);
	}
	
	// ------------------------- Calculate total sales -------------------------
	@Test
	public void testCalculateTotalSalesHappyDay() {
		Agent agent = new Agent();
		Receipt receipt1 = new Receipt();
		agent.getReceipts().add(receipt1);
		receipt1.setSales(20.05);
		Receipt receipt2 = new Receipt();
		receipt2.setSales(10.95);
		agent.getReceipts().add(receipt2);
		assertEquals(agent.calculateTotalSales(), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateTotalSalesPositiveAndNegativeSales() {
		Agent agent = new Agent();
		Receipt receipt1 = new Receipt();
		receipt1.setSales(20.95);
		agent.getReceipts().add(receipt1);
		Receipt receipt2 = new Receipt();
		receipt2.setSales(-10.05);
		agent.getReceipts().add(receipt2);
		assertEquals(agent.calculateTotalSales(), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateTotalSalesNegativeSales() {
		Agent agent = new Agent();
		Receipt receipt1 = new Receipt();
		receipt1.setSales(-5.95);
		agent.getReceipts().add(receipt1);
		Receipt receipt2 = new Receipt();
		receipt2.setSales(-10.05);
		agent.getReceipts().add(receipt2);
		assertEquals(agent.calculateTotalSales(), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateTotalSalesZeroSales() {
		Agent agent = new Agent();
		assertEquals(agent.calculateTotalSales(), 0.0, 0.01);
	}
	
	// ------------------------- Calculate total items -------------------------
	@Test
	public void testCalculateTotalItemsHappyDay() {
		Agent agent = new Agent();
		Receipt receipt1 = new Receipt();
		agent.getReceipts().add(receipt1);
		receipt1.setItems(2);
		Receipt receipt2 = new Receipt();
		receipt2.setItems(3);
		agent.getReceipts().add(receipt2);
		assertEquals(agent.calculateTotalItems(), 5);
	}
	
	@Test
	public void testCalculateTotalItemsPositiveAndNegativeItemes() {
		Agent agent = new Agent();
		Receipt receipt1 = new Receipt();
		receipt1.setItems(-20);
		agent.getReceipts().add(receipt1);
		Receipt receipt2 = new Receipt();
		receipt2.setItems(+10);
		agent.getReceipts().add(receipt2);
		assertEquals(agent.calculateTotalItems(), -10);
	}
	
	@Test
	public void testCalculateTotalItemsNegativeItemes() {
		Agent agent = new Agent();
		Receipt receipt1 = new Receipt();
		receipt1.setItems(-5);
		agent.getReceipts().add(receipt1);
		Receipt receipt2 = new Receipt();
		receipt2.setItems(-10);
		agent.getReceipts().add(receipt2);
		assertEquals(agent.calculateTotalItems(), -15);
	}
	
	@Test
	public void testCalculateTotalItemsZeroItems() {
		Agent agent = new Agent();
		assertEquals(agent.calculateTotalItems(), 0);
	}
	
	// ------------------------- Calculate skirt sales -------------------------
	@Test
	public void testCalculateSkirtSalesHappyDay() {
		Agent agent = new Agent();
		Skirt skirt1 = new Skirt();
		agent.getReceipts().add(skirt1);
		skirt1.setSales(20.05);
		Skirt skirt2 = new Skirt();
		skirt2.setSales(10.95);
		agent.getReceipts().add(skirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateSkirtsSales(), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateSkirtSalesPositiveAndNegativeSales() {
		Agent agent = new Agent();
		Skirt skirt1 = new Skirt();
		skirt1.setSales(20.95);
		agent.getReceipts().add(skirt1);
		Skirt skirt2 = new Skirt();
		skirt2.setSales(-10.05);
		agent.getReceipts().add(skirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateSkirtsSales(), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateSkirtSalesNegativeSales() {
		Agent agent = new Agent();
		Skirt skirt1 = new Skirt();
		skirt1.setSales(-5.95);
		agent.getReceipts().add(skirt1);
		Skirt skirt2 = new Skirt();
		skirt2.setSales(-10.05);
		agent.getReceipts().add(skirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateSkirtsSales(), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateSkirtSalesZeroSales() {
		Agent agent = new Agent();
		assertEquals(agent.calculateSkirtsSales(), 0.0, 0.01);
	}
	
	// ------------------------- Calculate coat sales -------------------------
	@Test
	public void testCalculateCoatSalesHappyDay() {
		Agent agent = new Agent();
		Coat coat1 = new Coat();
		agent.getReceipts().add(coat1);
		coat1.setSales(20.05);
		Coat coat2 = new Coat();
		coat2.setSales(10.95);
		agent.getReceipts().add(coat2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCoatsSales(), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateCoatSalesPositiveAndNegativeSales() {
		Agent agent = new Agent();
		Coat coat1 = new Coat();
		coat1.setSales(20.95);
		agent.getReceipts().add(coat1);
		Coat coat2 = new Coat();
		coat2.setSales(-10.05);
		agent.getReceipts().add(coat2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCoatsSales(), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateCoatSalesNegativeSales() {
		Agent agent = new Agent();
		Coat coat1 = new Coat();
		coat1.setSales(-5.95);
		agent.getReceipts().add(coat1);
		Coat coat2 = new Coat();
		coat2.setSales(-10.05);
		agent.getReceipts().add(coat2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCoatsSales(), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateCoatSalesZeroSales() {
		Agent agent = new Agent();
		assertEquals(agent.calculateCoatsSales(), 0.0, 0.01);
	}
	
	// ------------------------- Calculate trouser sales -------------------------
	@Test
	public void testCalculateTrouserSalesHappyDay() {
		Agent agent = new Agent();
		Trouser trouser1 = new Trouser();
		agent.getReceipts().add(trouser1);
		trouser1.setSales(20.05);
		Trouser trouser2 = new Trouser();
		trouser2.setSales(10.95);
		agent.getReceipts().add(trouser2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateTrouserSales(), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateTrouserSalesPositiveAndNegativeSales() {
		Agent agent = new Agent();
		Trouser trouser1 = new Trouser();
		trouser1.setSales(20.95);
		agent.getReceipts().add(trouser1);
		Trouser trouser2 = new Trouser();
		trouser2.setSales(-10.05);
		agent.getReceipts().add(trouser2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateTrouserSales(), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateTrouserSalesNegativeSales() {
		Agent agent = new Agent();
		Trouser trouser1 = new Trouser();
		trouser1.setSales(-5.95);
		agent.getReceipts().add(trouser1);
		Trouser trouser2 = new Trouser();
		trouser2.setSales(-10.05);
		agent.getReceipts().add(trouser2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateTrouserSales(), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateTrouserSalesZeroSales() {
		Agent agent = new Agent();
		assertEquals(agent.calculateTrouserSales(), 0.0, 0.01);
	}
	
	// ------------------------- Calculate shirt sales -------------------------
	@Test
	public void testCalculateShirtSalesHappyDay() {
		Agent agent = new Agent();
		Shirt shirt1 = new Shirt();
		agent.getReceipts().add(shirt1);
		shirt1.setSales(20.05);
		Shirt shirt2 = new Shirt();
		shirt2.setSales(10.95);
		agent.getReceipts().add(shirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateShirtsSales(), 31.0, 0.01);
	}
	
	@Test
	public void testCalculateShirtSalesPositiveAndNegativeSales() {
		Agent agent = new Agent();
		Shirt shirt1 = new Shirt();
		shirt1.setSales(20.95);
		agent.getReceipts().add(shirt1);
		Shirt shirt2 = new Shirt();
		shirt2.setSales(-10.05);
		agent.getReceipts().add(shirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateShirtsSales(), 10.9, 0.01);
	}
	
	@Test
	public void testCalculateShirtSalesNegativeSales() {
		Agent agent = new Agent();
		Shirt shirt1 = new Shirt();
		shirt1.setSales(-5.95);
		agent.getReceipts().add(shirt1);
		Shirt shirt2 = new Shirt();
		shirt2.setSales(-10.05);
		agent.getReceipts().add(shirt2);
		Receipt receipt = new Receipt();
		receipt.setSales(10.95);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateShirtsSales(), -16.00, 0.01);
	}
	
	@Test
	public void testCalculateShirtSalesZeroSales() {
		Agent agent = new Agent();
		assertEquals(agent.calculateShirtsSales(), 0.0, 0.01);
	}
	
	// ------------------------- Calculate commission --------------------------
	@Test
	public void testCalculateCommissionLessThan6000() {
		Agent agent = new Agent();
		Skirt skirt = new Skirt();
		agent.getReceipts().add(skirt);
		skirt.setSales(1000);
		Coat coat = new Coat();
		agent.getReceipts().add(coat);
		coat.setSales(500);
		Trouser trouser = new Trouser();
		agent.getReceipts().add(trouser);
		trouser.setSales(1000);
		Shirt shirt = new Shirt();
		agent.getReceipts().add(shirt);
		shirt.setSales(500);
		Receipt receipt = new Receipt();
		receipt.setSales(1000);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 0.0, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess6000() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(6000);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 0.0, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess6001() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(6001);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 1*0.1, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess8000() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(8000);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 2000*0.1, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess10000() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(10000);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 4000*0.1, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess10001() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(10001);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 1000+1*0.15, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess20000() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(20000);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 1000+10000*0.15, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess40000() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(40000);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 1000+30000*0.15, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess40001() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(40001);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 1000+4500+1*0.2, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLess80000() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(80000);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 1000+4500+40000*0.2, 0.01);
	}
	
	@Test
	public void testCalculateCommissionZero() {
		Agent agent = new Agent();
		assertEquals(agent.calculateCommission(), 0.0, 0.01);
	}
	
	@Test
	public void testCalculateCommissionLessNegative() {
		Agent agent = new Agent();
		Receipt receipt = new Receipt();
		receipt.setSales(-30000);
		agent.getReceipts().add(receipt);
		assertEquals(agent.calculateCommission(), 0.0, 0.01);
	}
	
	
}
