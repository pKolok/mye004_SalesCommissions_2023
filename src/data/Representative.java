package data;

import java.util.ArrayList;

import data.enums.SaleItem;
import output.ReceiptFileAppender;

public class Representative {
	
	private String name;
	private String afm;
	private ArrayList<Receipt> allReceipts;
	private ReceiptFileAppender fileAppender;	// TODO: dependency
	
	public Representative(String name, String afm, 
			ReceiptFileAppender fileAppender){
		this.name = name;
		this.afm = afm;
		this.fileAppender = fileAppender;
		allReceipts = new ArrayList<Receipt>();
	}
	
	public String getName() {
		return name;
	}

	public String getAfm() {
		return afm;
	}

	public void addRepresentativeReceipt(Receipt receipt) {
		allReceipts.add(receipt);
	}
	
	public void appendReceiptToReceiptsFile(Receipt receipt) {
		fileAppender.appendFile(receipt);
	}
	
	public double calculateTotalSales(){
		double sumSales = 0;
		for(int i = 0; i< allReceipts.size(); i++){
			sumSales += allReceipts.get(i).getSales();
		}
		return sumSales;
	}
	
	public int calculateTotalItems(){
		int sumItems = 0;
		for(int i = 0; i< allReceipts.size(); i++){
			sumItems += allReceipts.get(i).getItems();
		}
		return sumItems;
	}
	
	public float calculateItemSales(SaleItem item){
		float sum = 0;
		for (int i = 0; i< allReceipts.size(); i++){
			if(allReceipts.get(i).getKind() == item){
				sum += allReceipts.get(i).getSales();
			}
		}
		return sum;
	}
	
	public double calculateCommission(){
		double commission = 0;
		if( this.calculateTotalSales() > 6000 && this.calculateTotalSales()<= 10000){
			commission = 0.1*(calculateTotalSales()-6000);
		}
		else if(this.calculateTotalSales() > 10000 && this.calculateTotalSales() <= 40000 ){
			commission = (((calculateTotalSales() - 10000) * 0.15) + (10000*0.1));			
		}
		else if(this.calculateTotalSales() > 40000 ) {
			commission = 10000*0.1 + 30000*0.15 + (calculateTotalSales() - 40000)*0.2;			
		}
		return commission;
	}

}