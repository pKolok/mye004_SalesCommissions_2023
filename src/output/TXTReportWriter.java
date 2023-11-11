package output;

import data.Representative;
import data.SaleItem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TXTReportWriter extends ReportWriter{
	
	private BufferedWriter bufferedWriter;

	public TXTReportWriter(Representative representative, String fileName){
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
	}
	
	@Override
	protected void writeRepresentativeDetails() {
		try{
        	bufferedWriter.write("Name: " + representative.getName()); 
            bufferedWriter.newLine();

            bufferedWriter.write("AFM: " + representative.getAfm());
            bufferedWriter.newLine();

        } catch (IOException ex){
        	ex.printStackTrace();
        }
	}

	@Override
	protected void writeSalesSummary() {
		try{          
            bufferedWriter.write("Total Sales: " 
            		+ representative.calculateTotalSales());
            bufferedWriter.newLine();
            
            bufferedWriter.write("Total Items Sold: " 
            		+ representative.calculateTotalItems());
            bufferedWriter.newLine();
 
            bufferedWriter.write("Trousers Sales: " 
            		+ representative.calculateItemSales(SaleItem.TROUSERS));
            bufferedWriter.newLine();
            
            bufferedWriter.write("Shirts Sales: " 
            		+ representative.calculateItemSales(SaleItem.SHIRT));
            bufferedWriter.newLine();
            
            bufferedWriter.write("Coats Sales: " 
            		+ representative.calculateItemSales(SaleItem.COAT));
            bufferedWriter.newLine();

            bufferedWriter.write("Skirts Sales: " 
            		+ representative.calculateItemSales(SaleItem.SKIRT));
            bufferedWriter.newLine();

            bufferedWriter.write("Commission: " 
            		+ representative.calculateCommission());
        } catch (IOException ex){
        	ex.printStackTrace();
        }
	}

	@Override
	protected void closeFile() {
		try {
        	bufferedWriter.close();
        } catch (IOException ex){
        	ex.printStackTrace();
        }
	}
	
}