package input;

import data.Representative;
import data.Receipt;
import data.SaleItem;

import java.io.File;

public abstract class Input {
	
	protected Representative agent;
	protected File inputFile;
	protected String inputFilePath;
	protected String name;
	protected String afm;
	protected int receiptID;
	protected String date;
	protected SaleItem kind;
	protected double sales;
	protected int items;
	protected String companyName;
	protected String companyCountry;
	protected String companyCity;
	protected String companyStreet;
	protected int companyStreetNumber;

	public abstract void readFile();

	public Input() {
		agent = new Representative();
		kind  = SaleItem.OTHER;
	}
	
	// TODO: protected
	public void addAgent() {
		agent = new Representative(name, afm);
	}
	
	// TODO: protected
	public void addReceipt(){
		Receipt receipt = new Receipt(kind);
		
		receipt.setReceiptID(receiptID);			
		receipt.setDate(date);
		receipt.setSales(sales);
		receipt.setItems(items);
		receipt.getCompany().setName(companyName);
		receipt.getCompany().getCompanyAddress().setCountry(companyCountry);
		receipt.getCompany().getCompanyAddress().setCity(companyCity);
		receipt.getCompany().getCompanyAddress().setStreet(companyStreet);
		receipt.getCompany().getCompanyAddress().setStreetNumber(companyStreetNumber);
		agent.getReceipts().add(receipt);
	}
	
	public Representative getAgent() {
		return agent;
	}
	
	protected SaleItem getSaleItem(String item) {
		if (item.equals("Coats")) {
			return SaleItem.COAT;
		} else if (item.equals("Shirts")) {
			return SaleItem.SHIRT;
		} else if (item.equals("Skirts")) {
			return SaleItem.SKIRT;
		} else if (item.equals("Trousers")) {
			return SaleItem.TROUSERS;
		} else {
			return SaleItem.OTHER;
		}
	}
	
}