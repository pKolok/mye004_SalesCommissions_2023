package data;

public class Receipt {

	protected int receiptId;
	protected String date;
	protected double sales;
	protected int items;
	protected Company company;
	protected SaleItem kind;

	public Receipt(){
		kind = SaleItem.OTHER;
		company  = new Company();
	}
	
	public Receipt(SaleItem saleItem) {
		kind = saleItem;
		company  = new Company();
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

	public void setSales(double sales) {
		this.sales = sales;
	}

	public int getItems() {
		return this.items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public int getReceiptID() {
		return receiptId;
	}

	public void setReceiptID(int id) {
		this.receiptId = id;		
	}

	public String getDate() {
		return date;			
	}

	public void setDate(String date) {
		this.date = date;
	}

}