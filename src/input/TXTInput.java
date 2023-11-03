 package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import data.Address;
import data.Company;
import data.Receipt;
import data.Representative;

public class TXTInput extends Input {
	
	BufferedReader bufferedReader;

	public TXTInput(File recieptFileTXT){
		this.inputFile = recieptFileTXT;
		inputFilePath =  inputFile.getAbsolutePath();
	}
	
	protected void openFile() {
	    try {
			bufferedReader = new BufferedReader(new FileReader(inputFilePath));
		} catch (FileNotFoundException e1) {
				e1.printStackTrace();
		}
	}
	
	protected Representative getRepresentative() {
		String line;

	    try {
	    	// Name line
	    	line = bufferedReader.readLine();
	    	name = (line.substring(line.indexOf(":") + 1).trim());
	    	
	    	// Afm line
	    	line = bufferedReader.readLine();
	    	afm = (line.substring(line.indexOf(":") + 1).trim());
	    	
	    	return new Representative(name, afm);
	    	
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected ArrayList<Receipt> getReceipts() {
		ArrayList<Receipt> receipts = new ArrayList<Receipt>();
		String line;

	    try {
	    	// Receipts line and empty lines
	    	line = bufferedReader.readLine();
	    	line = bufferedReader.readLine();
	    	line = bufferedReader.readLine();
	    	
	    	while ((line = bufferedReader.readLine()) != null) {
	    		
	    		// Receipt ID line
		    	receiptID = (Integer.parseInt(line.substring(
		    			line.indexOf(":") + 1).trim()));
		    	
	    		// Date line
		    	line = bufferedReader.readLine();
		    	date = (line.substring(line.indexOf(":") + 1).trim());
		    	
		    	// Kind line
		    	line = bufferedReader.readLine();
		    	kind = getSaleItem(
		    			line.substring(line.indexOf(":") + 1).trim());
		    		
		    	// Sales line
		    	line = bufferedReader.readLine();
		    	sales = Double.parseDouble(line.substring(
		    			line.indexOf(":") + 1).trim());
		    	
		    	// Items line
		    	line = bufferedReader.readLine();
		    	items = Integer.parseInt(line.substring(
		    			line.indexOf(":") + 1).trim());
		    	
		    	// Company line
		    	line = bufferedReader.readLine();
		    	companyName = line.substring(line.indexOf(":") + 1).trim();
		    	
		    	// Country line
		    	line = bufferedReader.readLine();
		    	companyCountry =  line.substring(line.indexOf(":") + 1).trim();
		    	
		    	// City line
		    	line = bufferedReader.readLine();
		    	companyCity =  line.substring(line.indexOf(":") + 1).trim();
		    	
		    	// Street line
		    	line = bufferedReader.readLine();
		    	companyStreet = line.substring(line.indexOf(":") + 1).trim();
		    	
		    	// Street number line
		    	line = bufferedReader.readLine();
		    	companyStreetNumber = Integer.parseInt(line.substring(
		    			line.indexOf(":") + 1).trim());
		    	
		    	// Empty line
		    	line = bufferedReader.readLine();
		    	
		    	Address address = new Address(companyCountry, companyCity,
		    			companyStreet, companyStreetNumber);
		    	Company company = new Company(companyName, address);
		    	Receipt receipt = new Receipt(receiptID, date, sales, kind,
		    			items, company);
		    	
		    	receipts.add(receipt);
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return receipts;
	}	
	
	protected void closeFile() {
	    try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}