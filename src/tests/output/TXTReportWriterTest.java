package tests.output;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data.Representative;
import enums.SaleItem;
import data.Address;
import data.Company;
import data.Receipt;
import output.TXTReportWriter;
import output.ReceiptTxtFileAppender;

import org.junit.After;
import org.junit.Test;

public class TXTReportWriterTest {
	
	private String fileName = "src/tests/output/test_output_files/txtReport.txt";

	@After
    public void clean() throws IOException {
        // Delete the file once test over
		File file = new File(fileName);
        file.delete();
    }
	
	@Test
	public void testWriteReportHappyDay() {
		Representative representative = new Representative("Odysseus", "123456789",
				new ReceiptTxtFileAppender(new File("")));
		
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		
		Receipt receipt1 = new Receipt(0, "", 1000, SaleItem.OTHER, 10, company);
		representative.addRepresentativeReceipt(receipt1);
		Receipt receipt2 = new Receipt(0, "", 600, SaleItem.COAT, 30, company);
		representative.addRepresentativeReceipt(receipt2);
		Receipt receipt3 = new Receipt(0, "", 400, SaleItem.SHIRT, 20, company);
		representative.addRepresentativeReceipt(receipt3);
		Receipt receipt4 = new Receipt(0, "", 700, SaleItem.SKIRT, 35, company);
		representative.addRepresentativeReceipt(receipt4);
		Receipt receipt5 = new Receipt(0, "", 300, SaleItem.TROUSERS, 5,
				company);
		representative.addRepresentativeReceipt(receipt5);
		
		TXTReportWriter reportWriter = new TXTReportWriter(
				representative, fileName);
		reportWriter.writeReport();
		
		String fileExpected = "Name: Odysseus\n";
		fileExpected += "AFM: 123456789\n";
		fileExpected += "Total Sales: 3000.0\n";
		fileExpected += "Total Items Sold: 100\n";
		fileExpected += "Trousers Sales: 300.0\n";
		fileExpected += "Shirts Sales: 400.0\n";
		fileExpected += "Coats Sales: 600.0\n";
		fileExpected += "Skirts Sales: 700.0\n";
		fileExpected += "Commission: 0.0\n";

		String fileRead = "";
		
		try {
			BufferedReader reader = new BufferedReader(
				new FileReader(fileName));
			String line; 
			while ((line = reader.readLine()) != null) {
				fileRead += line + "\n";
			}
			reader.close();
			
			assertEquals(fileExpected, fileRead);
			
		} catch (FileNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}

}