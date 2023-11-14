package tests.output;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import data.Representative;
import data.enums.SaleItem;
import data.Address;
import data.Company;
import data.Receipt;
import output.ReceiptTxtFileAppender;
import output.XMLReportWriter;

public class XMLReportWriterTest {

	private String fileName = "src/tests/output/test_output_files/xmlReport.xml";

	@After
    public void clean() throws IOException {
        // Delete the file once test over
		File file = new File(fileName);
        file.delete();
    }
	
	@Test
	public void testWriteReportHappyDay() {
		Representative agent = new Representative("Odysseus", "123456789",
				new ReceiptTxtFileAppender(new File("")));
		
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		
		Receipt receipt1 = new Receipt(0, "", 1000, SaleItem.OTHER, 10, company);
		agent.addRepresentativeReceipt(receipt1);
		Receipt receipt2 = new Receipt(0, "", 600, SaleItem.COAT, 30, company);
		agent.addRepresentativeReceipt(receipt2);
		Receipt receipt3 = new Receipt(0, "", 400, SaleItem.SHIRT, 20, company);
		agent.addRepresentativeReceipt(receipt3);
		Receipt receipt4 = new Receipt(0, "", 700, SaleItem.SKIRT, 35, company);
		agent.addRepresentativeReceipt(receipt4);
		Receipt receipt5 = new Receipt(0, "", 300, SaleItem.TROUSERS, 5,
				company);
		agent.addRepresentativeReceipt(receipt5);
		
		XMLReportWriter reportWriter = new XMLReportWriter(agent, fileName);
		reportWriter.writeReport();
		
		String fileExpected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		fileExpected += "<Agent>\n";
		fileExpected += "    <Name>Odysseus</Name>\n";
		fileExpected += "    <AFM>123456789</AFM>\n";
		fileExpected += "    <TotalSales>3000.0</TotalSales>\n";
		fileExpected += "    <TotalItems>100.0</TotalItems>\n";
		fileExpected += "    <TrouserSales>300.0</TrouserSales>\n";
		fileExpected += "    <ShirtsSales>400.0</ShirtsSales>\n";
		fileExpected += "    <CoatsSales>600.0</CoatsSales>\n";
		fileExpected += "    <SkirtsSales>700.0</SkirtsSales>\n";
		fileExpected += "    <Commission>0.0</Commission>\n";
		fileExpected += "</Agent>\n";

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
