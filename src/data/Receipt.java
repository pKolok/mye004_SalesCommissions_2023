package data;

public class Receipt {

	protected int receiptId;
	protected String date;
	protected double sales;
	protected int items;
	protected Company company;
	protected SaleItem kind;
	
	public Receipt(int id, String date, double sales, SaleItem saleItem, 
			int items, Company company) {
		this.receiptId = id;
		this.date = date;
		this.sales = sales;
		this.kind = saleItem;
		this.items = items;
		this.company  = company;
	}
	
	/**
	 * Constructs a Receipt object.
	 * Accepts a String _kind of "Shirts", "Skirts", "Trousers" or "Coats". If 
	 * _kind is none of these then kind will be SaleItem.OTHER.
	 * @param _kind
	 */
	public Receipt(int id, String date, double sales, String _kind, 
			int items, Company company) {
		this.kind = SaleItem.OTHER;
		if(_kind.equals("Shirts")) {
			kind = SaleItem.SHIRT;
		} else if (_kind.equals("Skirts")) {
			kind = SaleItem.SKIRT;
		} else if (_kind.equals("Trousers")) {
			kind = SaleItem.TROUSERS;
		} else if(_kind.equals("Coats")) {
			kind = SaleItem.COAT;
		}
		this.receiptId = id;
		this.date = date;
		this.sales = sales;
		this.items = items;
		this.company  = company;
	}
	
	public Company getCompany(){
		return company;
	}

	public SaleItem getKind() {
		return kind;
	}

	public double getSales() {
		return sales;
	}

	public int getItems() {
		return this.items;
	}

	public int getReceiptID() {
		return receiptId;
	}

	public String getDate() {
		return date;			
	}

}