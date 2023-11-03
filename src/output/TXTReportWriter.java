package output;

import data.Representative;
import data.SaleItem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class TXTReportWriter extends ReportWriter{

	public TXTReportWriter(Representative a){
		agent = a;
	}
	
	public void writeReport() {
        BufferedWriter bufferedWriter = null;
        try{
        	String fullPathName =  "/users/Nick/Desktop/Reports/" + agent.getAfm() + "_SALES.txt";
        	bufferedWriter = new BufferedWriter(new FileWriter(new File(fullPathName)));
            
        	bufferedWriter.write("Name: " + agent.getName()); 
            bufferedWriter.newLine();

            bufferedWriter.write("AFM: " + agent.getAfm());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            
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
            
        	bufferedWriter.close();


        }catch (IOException ex){
			JOptionPane.showMessageDialog(null,"������ ������ �������� ���� ��� ���������� ��� �������");

        }
		
	}

}