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
import output.ReceiptTxtFileAppender;

public class ReceiptTxtFileAppenderTest {

	private File file;
	private String fileName = "src/tests/output/test_output_files/txtFile.txt";
		
	@After
    public void clean() throws IOException {
        // Delete the file once test over
        file.delete();
    }
	
	@Test
	public void testAppendFileHappyDay() {
		String fileExpected = "\nReceiptID: 1234\n";
		fileExpected += "Date: 10/10/2010\n";
		fileExpected += "Kind: Coats\n";
		fileExpected += "Sales: 2000.0\n";
		fileExpected += "Items: 3\n";
		fileExpected += "Company: Umbrella\n";
		fileExpected += "Country: US\n";
		fileExpected += "City: Racoon\n";
		fileExpected += "Street: Underground ave\n";
		fileExpected += "Number: -10\n";

		String fileRead = "";

		this.file = new File(fileName);
		ReceiptTxtFileAppender fileAppender = new ReceiptTxtFileAppender(
				this.file);
		
		Address address = new Address("US", "Racoon", "Underground ave", -10);
		Company company = new Company("Umbrella", address);
		Receipt receipt = new Receipt(1234, "10/10/2010", 2000.0, 
				"Coats", 3, company);
		
		fileAppender.appendFile(receipt);
		
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