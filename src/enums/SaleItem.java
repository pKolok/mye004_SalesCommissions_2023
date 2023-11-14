package enums;

public enum SaleItem {
	COAT("Coats"),
	SHIRT("Shirts"),
	SKIRT("Skirts"),
	TROUSERS("Trousers"),
	OTHER("Other");
	
	private final String label;

	SaleItem(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return this.label;
	}
}