package tests.output;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import data.Address;
import data.Company;
import data.Receipt;
import data.Representative;
import enums.SaleItem;
import output.ReceiptTxtFileAppender;
import output.HTMLReportWriter;

public class HTMLReportWriterTest {

	private String fileName = "src/tests/output/test_output_files/htmlReport.html";

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
		
		HTMLReportWriter reportWriter = new HTMLReportWriter(representative, fileName);
		reportWriter.writeReport();
		
		String fileExpected = "<!doctype html>\n";
		fileExpected += "<html>\n";
		fileExpected += "  <head>\n";
		fileExpected += "    <title>Sales Report</title>\n";
		fileExpected += "  </head>\n";
		fileExpected += "  <body>\n";
		fileExpected += "    <h1>Name: Odysseus</h1>\n";
		fileExpected += "    <h2>AFM: 123456789</h2>\n";
		fileExpected += "    <br>\n";
		fileExpected += "    <h2>Report:</h2>\n";
		fileExpected += "    <p>Total Sales: 3000.0</p>\n";
		fileExpected += "    <p>Total Items Sold: 100</p>\n";
		fileExpected += "    <p>Trousers Sales: 300.0</p>\n";
		fileExpected += "    <p>Shirts Sales: 400.0</p>\n";
		fileExpected += "    <p>Coats Sales: 600.0</p>\n";
		fileExpected += "    <p>Skirts Sales: 700.0</p>\n";
		fileExpected += "    <p>Commission: 0.0</p>\n";
		fileExpected += "  </body>\n";
		fileExpected += "</html>\n";

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
