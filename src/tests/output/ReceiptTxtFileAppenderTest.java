package tests.output;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import data.Address;
import data.Company;
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
		
		Address address = new Address("US", "Racoon", "Underground ave", -10);
		Company company = new Company("Umbrella", address);
		Receipt receipt = new Receipt(1234, "10/10/2010", 2000.0, 
				SaleItem.COAT, 3, company);
		
		fileAppenderTXT.appendFile(receipt);
		
		try {
			BufferedReader reader = new BufferedReader(
				new FileReader("src/tests/data/txtFile.txt"));
			String line; 
			while ((line = reader.readLine()) != null) {
				txtFileRead += line + "\n";
			}
			reader.close();
			
			assertEquals(txtExpected, txtFileRead);
			
		} catch (FileNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}

}