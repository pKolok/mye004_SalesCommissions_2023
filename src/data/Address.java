package data;

public class Address {
	
	private String country;
	private String city;
	private String street;
	private int streetNumber;
	
	public Address(String country, String city, String street,
			int streetNumber) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
	
	public String getStreet() {
		return street;
	}
	
	public int getStreetNumber() {
		return streetNumber;
	}
	
}