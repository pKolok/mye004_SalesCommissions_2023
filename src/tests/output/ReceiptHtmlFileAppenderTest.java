package tests.output;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Address;
import data.Company;
import data.Receipt;
import data.SaleItem;
import output.ReceiptHtmlFileAppender;

public class ReceiptHtmlFileAppenderTest {

	private File file;
	private String fileName = "src/tests/output/test_output_files/htmlFile.html";
	
	@Before
	public void setUp() throws Exception {
		// Create a temporary no-receipts HTML file
		try{
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
        			new File(this.fileName)));
        	bufferedWriter.write("<!doctype html>");
        	bufferedWriter.newLine();
        	bufferedWriter.write("<html>");
        	bufferedWriter.newLine();
        	bufferedWriter.write("  <head>");
        	bufferedWriter.newLine();
        	bufferedWriter.write("    <title>Sales Report</title>");
        	bufferedWriter.newLine();
        	bufferedWriter.write("  </head>");
        	bufferedWriter.newLine();
        	bufferedWriter.write("  <body>");
        	bufferedWriter.newLine();
        	bufferedWriter.write("    <h1>Name: Jack Smith </h1>"); 
        	bufferedWriter.newLine();
	        bufferedWriter.write("    <h2>AFM: 33232432432fds </h2>");
	        bufferedWriter.newLine();
	        bufferedWriter.write("  </body>");
	        bufferedWriter.newLine();
			bufferedWriter.write("</html>");
			bufferedWriter.close();
        } catch (IOException ex){
			ex.printStackTrace();
        }
	}
	
	@After
    public void clean() throws IOException {
        // Delete the file once test over
        file.delete();
    }
	
	@Test
	public void testAppendFileHappyDay() {
		String htmlExpected = "<!doctype html>\n";
		htmlExpected += "<html>\n";
		htmlExpected += "  <head>\n";
		htmlExpected += "    <title>Sales Report</title>\n";
		htmlExpected += "  </head>\n";
		htmlExpected += "  <body>\n";
		htmlExpected += "    <h1>Name: Jack Smith </h1>\n";
		htmlExpected += "    <h2>AFM: 33232432432fds </h2>\n";
		htmlExpected += "    <br>\n";
		htmlExpected += "    <p>ReceiptID: 1234</p>\n";
		htmlExpected += "    <p>Date: 10/10/2010</p>\n";
		htmlExpected += "    <p>Kind: Coats</p>\n";
		htmlExpected += "    <p>Sales: 2000.0</p>\n";
		htmlExpected += "    <p>Items: 3</p>\n";
		htmlExpected += "    <p>Company: Umbrella</p>\n";
		htmlExpected += "    <p>Country: US</p>\n";
		htmlExpected += "    <p>City: Racoon</p>\n";
		htmlExpected += "    <p>Street: Underground ave</p>\n";
		htmlExpected += "    <p>Number: -10</p>\n";
		htmlExpected += "  </body>\n";
		htmlExpected += "</html>\n";

		String htmlFileRead = "";

		this.file = new File(fileName);
		ReceiptHtmlFileAppender fileAppenderTXT = new ReceiptHtmlFileAppender(
				this.file);
		
		Address address = new Address("US", "Racoon", "Underground ave", -10);
		Company company = new Company("Umbrella", address);
		Receipt receipt = new Receipt(1234, "10/10/2010", 2000.0, 
				SaleItem.COAT, 3, company);
		
		fileAppenderTXT.appendFile(receipt);
		
		try {
			BufferedReader reader = new BufferedReader(
				new FileReader(fileName));
			String line; 
			while ((line = reader.readLine()) != null) {
				htmlFileRead += line + "\n";
			}
			reader.close();
			
			assertEquals(htmlExpected, htmlFileRead);
			
		} catch (FileNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}

}
