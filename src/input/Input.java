package input;

import data.Representative;
import data.Address;
import data.Company;
import data.Receipt;
import data.SaleItem;

import java.io.File;

public abstract class Input {
	
	protected Representative agent;	// TODO: rename
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
		agent = new Representative("", "");
		kind  = SaleItem.OTHER;
	}
	
	// TODO: protected
	public void addAgent() {
		agent = new Representative(name, afm);
	}
	
	// TODO: protected
	public void addReceipt(){
		Address address = new Address(companyCountry, companyCity,
				companyStreet, companyStreetNumber);
		Company company = new Company(companyName, address);
		Receipt receipt = new Receipt(receiptID, date, sales, kind, items,
				company);
		agent.addRepresentativeReceipt(receipt);
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