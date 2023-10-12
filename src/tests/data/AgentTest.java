package tests.data;

import static org.junit.Assert.*;

import data.Agent;
import data.FileAppenderTXT;
import data.FileAppenderXML;
import data.Receipt;

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

	// ---------------------------- Get and Set AFM ---------------------------
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
	public void testCalculateTotalSalesNegativeSales() {
		Agent agent = new Agent();
		Receipt receipt1 = new Receipt();
		receipt1.setSales(20.95);
		agent.getReceipts().add(receipt1);
		Receipt receipt2 = new Receipt();
		receipt2.setSales(-10.05);
		agent.getReceipts().add(receipt2);
		assertEquals(agent.calculateTotalSales(), 10.9, 0.01);
	}
	
}
