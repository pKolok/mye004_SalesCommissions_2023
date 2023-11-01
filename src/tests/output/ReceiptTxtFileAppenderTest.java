package tests.output;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import data.Receipt;
import data.SaleItem;
import output.ReceiptTxtFileAppender;

public class ReceiptTxtFileAppenderTest {

	private File file;
		
	@After
    public void clean() throws IOException {
        // Delete the file once test over
        file.delete();
    }
	
	@Test
	public void testAppendFileHappyDay() {
		String txtExpected = "\nReceiptID: 1234\n";
		txtExpected += "Date: 10/10/2010\n";
		txtExpected += "Kind: COAT\n";
		txtExpected += "Sales: 2000.0\n";
		txtExpected += "Items: 3\n";
		txtExpected += "Company: Umbrella\n";
		txtExpected += "Country: US\n";
		txtExpected += "City: Racoon\n";
		txtExpected += "Street: Underground ave\n";
		txtExpected += "Number: -10\n";

		String txtFileRead = "";

		this.file = new File("src/tests/data/txtFile.txt");
		ReceiptTxtFileAppender fileAppenderTXT = new ReceiptTxtFileAppender(
				this.file);
		
		Receipt receipt = new Receipt(SaleItem.COAT);
		receipt.setReceiptID(1234);
		receipt.setDate("10/10/2010");
		receipt.setSales(2000);
		receipt.setItems(3);
		receipt.getCompany().setName("Umbrella");
		receipt.getCompany().getCompanyAddress().setCountry("US");
		receipt.getCompany().getCompanyAddress().setCity("Racoon");
		receipt.getCompany().getCompanyAddress().setStreet("Underground ave");
		receipt.getCompany().getCompanyAddress().setStreetNumber(-10);
		
		fileAppenderTXT.appendFile(receipt);
		
		try {
			BufferedReader reader = new BufferedReader(
				new FileReader("src/tests/data/txtFile.txt"));
			String line; 
			while ((line = reader.readLine()) != null) {
				txtFileRead += line + "\n";
			}
			reader.close();
			
//			System.out.println("Expected: " + txtExpected);
//			System.out.println("Received: " + txtFileRead);
			
			assertEquals(txtExpected, txtFileRead);
			
		} catch (FileNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}

}