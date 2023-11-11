package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.Representative;
import data.SaleItem;

public class HTMLReportWriter extends ReportWriter {

	private BufferedWriter bufferedWriter;
	
	public HTMLReportWriter(Representative representative, String fileName){
		this.representative = representative;
		this.fileName = fileName;
	}
	
	@Override
	protected void createFile() {
		try{
        	bufferedWriter = new BufferedWriter(new FileWriter(
        			new File(this.fileName)));
        } catch (IOException ex){
			ex.printStackTrace();
        }
		
		writeBeginningOfFile();
	}

	@Override
	protected void writeRepresentativeDetails() {
		try{
        	bufferedWriter.write("    <h1>Name: " + representative.getName() 
        		+ "</h1>"); 
            bufferedWriter.newLine();

            bufferedWriter.write("<h2>AFM: " + representative.getAfm() 
            	+ "</h2>");
            bufferedWriter.newLine();
        } catch (IOException ex){
        	ex.printStackTrace();
        }

	}

	@Override
	protected void writeSalesSummary() {
		try{
			bufferedWriter.write("    <br>");
			bufferedWriter.write("    <h2>Report:</h2>");
			
            bufferedWriter.write("    <p>Total Sales: " 
            		+ representative.calculateTotalSales() + "</p>");
            bufferedWriter.newLine();
			
            bufferedWriter.write("    <p>Total Items Sold: " 
            		+ representative.calculateTotalItems() + "</p>");
            bufferedWriter.newLine();
 
            bufferedWriter.write("    <p>Trousers Sales: " 
            		+ representative.calculateItemSales(SaleItem.TROUSERS) 
            		+ "</p>");
            bufferedWriter.newLine();
            
            bufferedWriter.write("    <p>Shirts Sales: " 
            		+ representative.calculateItemSales(SaleItem.SHIRT) 
            		+ "</p>");
            bufferedWriter.newLine();
            
            bufferedWriter.write("    <p>Coats Sales: " 
            		+ representative.calculateItemSales(SaleItem.COAT) 
            		+ "</p>");
            bufferedWriter.newLine();

            bufferedWriter.write("    <p>Skirts Sales: " 
            		+ representative.calculateItemSales(SaleItem.SKIRT) 
            		+ "</p>");
            bufferedWriter.newLine();

            bufferedWriter.write("    <p>Commission: " 
            		+ representative.calculateCommission() + "</p>");
        } catch (IOException ex){
        	ex.printStackTrace();
        }
	}

	@Override
	protected void closeFile() {
		try {
			bufferedWriter.write("  </body>");
			bufferedWriter.write("</html>");
        	bufferedWriter.close();
        } catch (IOException ex){
        	ex.printStackTrace();
        }
	}
	
	private void writeBeginningOfFile() {
		try{
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
        } catch (IOException ex){
			ex.printStackTrace();
        }
	}

}