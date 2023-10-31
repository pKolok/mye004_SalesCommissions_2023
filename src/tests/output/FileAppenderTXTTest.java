package tests.output;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import output.ReceiptTxtFileAppender;

public class FileAppenderTXTTest {

	private File file;
		
	@After
    public void clean() throws IOException {
        // Delete the file once test over
        file.delete();
    }
	
	@Test
	public void testAppendFileHappyDay() {
		String txtExpected = "\nReceipt ID: 1234\n";
		txtExpected += "Date: 10/10/2010\n";
		txtExpected += "Kind: Coat\n";
		txtExpected += "Sales: 2000\n";
		txtExpected += "Items: 3\n";
		txtExpected += "Company: Umbrella\n";
		txtExpected += "Country: US\n";
		txtExpected += "City: Racoon\n";
		txtExpected += "Street: Underground ave\n";
		txtExpected += "Number: -10\n";

		String txtFileRead = "";

		ReceiptTxtFileAppender fileAppenderTXT = new ReceiptTxtFileAppender();
		this.file = new File("src/tests/data/txtFile.txt");
		fileAppenderTXT.setFileToAppend(file);
		fileAppenderTXT.setReceiptID("1234");
		fileAppenderTXT.setDate("10/10/2010");
		fileAppenderTXT.setSales("2000");
		fileAppenderTXT.setKind("Coat");
		fileAppenderTXT.setItems("3");
		fileAppenderTXT.setCompany("Umbrella");
		fileAppenderTXT.setCountry("US");
		fileAppenderTXT.setCity("Racoon");
		fileAppenderTXT.setStreet("Underground ave");
		fileAppenderTXT.setNumber("-10");
		fileAppenderTXT.appendFile();
		
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