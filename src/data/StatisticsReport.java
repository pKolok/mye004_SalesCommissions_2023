package data;

public class StatisticsReport {
	private double totalSales;
	private int totalItems;
	private float shirtSales;
	private float skirtSales;
	private float trousersSales;
	private float coatsSales;
	private double commission;

	public StatisticsReport(double totalSales, int totalItems, float shirtSales,
			float skirtSales, float trousersSales, float coatsSales, 
			double commission) {
		this.totalSales = totalSales;
		this.totalItems = totalItems;
		this.shirtSales = shirtSales;
		this.skirtSales = skirtSales;
		this.trousersSales = trousersSales;
		this.coatsSales = coatsSales;
		this.commission = commission;
	}

	public double getTotalSales() {
		return totalSales;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public float getShirtSales() {
		return shirtSales;
	}

	public float getSkirtSales() {
		return skirtSales;
	}

	public float getTrousersSales() {
		return trousersSales;
	}

	public float getCoatsSales() {
		return coatsSales;
	}

	public double getCommission() {
		return commission;
	}
	
	
}
