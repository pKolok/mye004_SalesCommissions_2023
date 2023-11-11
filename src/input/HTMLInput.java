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

public class HTMLInput extends Input {
	
	private String inputFilePath;
	private BufferedReader bufferedReader;
	
	public HTMLInput(File receiptFile){
		this.inputFile = receiptFile;
		this.inputFilePath =  inputFile.getAbsolutePath();
	}

	@Override
	protected void openFile() {
	    try {
			bufferedReader = new BufferedReader(new FileReader(inputFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    consumeBeginningOfFile();
	}

	@Override
	protected Representative getRepresentative() {
		String line;

	    try {
	    	// Name line
	    	line = bufferedReader.readLine();
	    	line = line.replace("<h1>", "").replace("</h1>", "");
	    	String name = (line.substring(line.indexOf(":") + 1).trim());
	    	
	    	// Afm line
	    	line = bufferedReader.readLine();
	    	line = line.replace("<h2>", "").replace("</h2>", "");
	    	String afm = (line.substring(line.indexOf(":") + 1).trim());
	    	
	    	return new Representative(name, afm);
	    	
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected ArrayList<Receipt> getReceipts() {
		ArrayList<Receipt> receipts = new ArrayList<Receipt>();
		String line;

	    try {
	    	// Receipts line and empty lines
	    	line = bufferedReader.readLine();
	    	line = bufferedReader.readLine();
	    	
	    	while (!(line = bufferedReader.readLine()).equals("</html>")) {
	    		
	    		// Receipt ID line
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	int receiptID = (Integer.parseInt(line.substring(
		    			line.indexOf(":") + 1).trim()));
		    	
	    		// Date line
		    	line = bufferedReader.readLine();
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	String date = (line.substring(line.indexOf(":") + 1).trim());
		    	
		    	// Kind line
		    	line = bufferedReader.readLine();
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	String kind = line.substring(line.indexOf(":") + 1).trim();
		    		
		    	// Sales line
		    	line = bufferedReader.readLine();
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	double sales = Double.parseDouble(line.substring(
		    			line.indexOf(":") + 1).trim());
		    	
		    	// Items line
		    	line = bufferedReader.readLine();
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	int items = Integer.parseInt(line.substring(
		    			line.indexOf(":") + 1).trim());
		    	
		    	// Company line
		    	line = bufferedReader.readLine();
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	String companyName = line.substring(line.indexOf(":") + 1).trim();
		    	
		    	// Country line
		    	line = bufferedReader.readLine();
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	String companyCountry =  line.substring(line.indexOf(":") + 1).trim();
		    	
		    	// City line
		    	line = bufferedReader.readLine();
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	String companyCity =  line.substring(line.indexOf(":") + 1).trim();
		    	
		    	// Street line
		    	line = bufferedReader.readLine();
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	String companyStreet = line.substring(line.indexOf(":") + 1).trim();
		    	
		    	// Street number line
		    	line = bufferedReader.readLine();
		    	line = line.replace("<p>", "").replace("</p>", "");
		    	int companyStreetNumber = Integer.parseInt(line.substring(
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

	@Override
	protected void closeFile() {
	    try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void consumeBeginningOfFile() {
	    try {
	    	for (int i = 0; i < 6; ++i) {
	    		bufferedReader.readLine();
	    	}
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
