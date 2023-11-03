package tests.input;

import static org.junit.Assert.*;

import data.Representative;
import data.SaleItem;
import input.TXTInput;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TXTInputTest {

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

	@Test
	public void testReadFileOneReceiptHappyDay() {
		File file = new File(
			"src/tests/input/test_input_files/txt_1Receipt.txt");
		TXTInput txtInput = new TXTInput(file);
		txtInput.readFile();
		
		Representative agent = txtInput.getAgent();
		
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
		
		Representative agent = txtInput.getAgent();
		
		assertEquals(agent.getName(), "Apostolos Zarras");
		assertEquals(agent.getAfm(), "130456093");
		
		assertEquals(agent.calculateTotalSales(), 12000, 0.01);
		assertEquals(agent.calculateTotalItems(), 85);
		assertEquals(agent.calculateItemSales(SaleItem.COAT), 2000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.SHIRT), 4000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.SKIRT), 1000, 0.01);
		assertEquals(agent.calculateItemSales(SaleItem.TROUSERS), 5000, 0.01);
	}
	
//	@Test
//	public void testReadFileOneReceiptMissingName() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingName.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		
//		assertEquals(agent.getName(), null);
//	}
//	
//	@Test
//	public void testReadFileOneReceiptMissingAfm() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingAfm.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		
//		assertEquals(agent.getAfm(), null);
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingReceiptsWord() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingReceiptsWord.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		
//		assertEquals(agent.getName(), "Apostolos Zarras");
//		assertEquals(agent.getAfm(), "130456093");
//		assertEquals(agent.getReceipts().size(), 1);
//		
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(receipt.getReceiptID(), 1);
//		assertEquals(receipt.getDate(), "25/2/2014");
//		assertEquals(receipt.getKind(), SaleItem.COAT);
//		assertEquals(receipt.getSales(), 2000, 0.01);
//		assertEquals(receipt.getItems(), 10);
//		assertEquals(receipt.getCompany().getName(), "Hand Made Clothes");
//		assertEquals(
//			receipt.getCompany().getCompanyAddress().getCountry(), "Greece");
//		assertEquals(
//				receipt.getCompany().getCompanyAddress().getCity(), "Ioannina");
//		assertEquals(
//				receipt.getCompany().getCompanyAddress().getStreet(), "Kaloudi");
//		assertEquals(
//				receipt.getCompany().getCompanyAddress().getStreetNumber(), 10);
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingReceiptId() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingReceiptId.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(receipt.getReceiptID(), 0);
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingDate() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingDate.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(receipt.getDate(), null);
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingSales() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingSales.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(receipt.getSales(), 0.0, 0.01);
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingKind() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingKind.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(receipt.getKind(), SaleItem.OTHER);
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingItems() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingItems.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(receipt.getItems(), 0);
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingCompanyName() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingCompany.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(receipt.getCompany().getName(), null);
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingCountry() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingCountry.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(
//			receipt.getCompany().getCompanyAddress().getCountry(), null);
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingCity() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingCity.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(
//				receipt.getCompany().getCompanyAddress().getCity(), null);
//
//	}
//	
//	@Test
//	public void testReadFileOneReceipMissingStreet() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingStreet.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//		
//		assertEquals(
//				receipt.getCompany().getCompanyAddress().getStreet(), null);
//		assertEquals(
//				receipt.getCompany().getCompanyAddress().getStreetNumber(), 10);
//	}
//	
//	// TODO: rightfully fails: TXTInput::103 does not addReceipt
//	@Test
//	public void testReadFileOneReceipMissingStreetNumber() {
//		File file = new File(
//			"src/tests/input/test_input_files/txt_1Receipt_MissingNumber.txt");
//		TXTInput txtInput = new TXTInput(file);
//		txtInput.readFile();
//		
//		Representative agent = txtInput.getAgent();
//		Receipt receipt = agent.getReceipts().get(0);
//
//		assertEquals(
//			receipt.getCompany().getCompanyAddress().getStreetNumber(), 0);
//	}

}
