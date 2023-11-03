package output;

import data.Representative;
import data.SaleItem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TXTReportWriter extends ReportWriter{
	
	private BufferedWriter bufferedWriter;

	public TXTReportWriter(Representative a){
		agent = a;
	}
	
	@Override
	protected void openFile() {
		String fullPathName =  "/users/Nick/Desktop/Reports/" + agent.getAfm() 
			+ "_SALES.txt";
		try{
        	bufferedWriter = new BufferedWriter(new FileWriter(
        			new File(fullPathName)));  
        } catch (IOException ex){
			ex.printStackTrace();
        }
	}
	
	@Override
	protected void writeRepresentativeDetails() {
		try{
        	bufferedWriter.write("Name: " + agent.getName()); 
            bufferedWriter.newLine();

            bufferedWriter.write("AFM: " + agent.getAfm());
            bufferedWriter.newLine();

        } catch (IOException ex){
        	ex.printStackTrace();
        }
	}

	@Override
	protected void writeSalesSummary() {
		try{          
            bufferedWriter.write("Total Sales: " + agent.calculateTotalSales());
            bufferedWriter.newLine();
 
            bufferedWriter.write("Trousers Sales: " + agent.calculateItemSales(
            		SaleItem.TROUSERS));
            bufferedWriter.newLine();

            bufferedWriter.write("Skirts Sales: " + agent.calculateItemSales(
            		SaleItem.SKIRT));
            bufferedWriter.newLine();

            bufferedWriter.write("Shirts Sales: " + agent.calculateItemSales(
            		SaleItem.SHIRT));
            bufferedWriter.newLine();
            
            bufferedWriter.write("Coats Sales: " + agent.calculateItemSales(
            		SaleItem.COAT));
            bufferedWriter.newLine();

            bufferedWriter.write("Commission: " + agent.calculateCommission());
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