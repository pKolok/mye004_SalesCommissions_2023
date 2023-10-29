package tests.data;

import data.Address;

import static org.junit.Assert.*;
import org.junit.Test;

public class AddressTest {

	// ---------------------------- Get and Set City ---------------------------
	@Test
	public void testGetAndSetCityHappyDay() {
		Address address = new Address();
		address.setCity("Ioannina");
		assertEquals("Ioannina", address.getCity());
	}
	
	@Test
	public void testGetAndSetCityEmptyString() {
		Address address = new Address();
		address.setCity("");
		assertEquals("", address.getCity());
	}

	@Test
	public void testGetAndSetCityNull() {
		Address address = new Address();
		address.setCity(null);
		assertEquals(null, address.getCity());
	}
	
	// -------------------------- Get and Set Country --------------------------
	@Test
	public void testGetAndSetCountryHappyDay() {
		Address address = new Address();
		address.setCountry("Greece");
		assertEquals("Greece", address.getCountry());
	}
	
	@Test
	public void testGetAndSetCountryEmptyString() {
		Address address = new Address();
		address.setCountry("");
		assertEquals("", address.getCountry());
	}

	@Test
	public void testGetAndSetCountryNull() {
		Address address = new Address();
		address.setCountry(null);
		assertEquals(null, address.getCountry());
	}
	
	// -------------------------- Get and Set Street ---------------------------
	@Test
	public void testGetAndSetStreetHappyDay() {
		Address address = new Address();
		address.setStreet("Hollywood Ave");
		assertEquals("Hollywood Ave", address.getStreet());
	}
	
	@Test
	public void testGetAndSetStreetEmptyString() {
		Address address = new Address();
		address.setStreet("");
		assertEquals("", address.getStreet());
	}

	@Test
	public void testGetAndSetStreetNull() {
		Address address = new Address();
		address.setStreet(null);
		assertEquals(null, address.getStreet());
	}
	
	// ---------------------- Get and Set Street Number ------------------------
	@Test
	public void testGetAndSetStreetNumberHappyDay() {
		Address address = new Address();
		address.setStreetNumber(10);
		assertEquals(10, address.getStreetNumber());
	}
	
	@Test
	public void testGetAndSetStreetNumberZero() {
		Address address = new Address();
		address.setStreetNumber(0);
		assertEquals(0, address.getStreetNumber());
	}
	
	@Test
	public void testGetAndSetStreetNumberNegative() {
		Address address = new Address();
		address.setStreetNumber(-6);
		assertEquals(-6, address.getStreetNumber());
	}
	
	// ----------------------- Get and Set Phone Number ------------------------
	@Test
	public void testGetAndSetPhoneNumberHappyDay() {
		Address address = new Address();
		address.setPhoneNumber(10);
		assertEquals(10, address.getPhoneNumber());
	}
	
	@Test
	public void testGetAndSetPhoneNumberZero() {
		Address address = new Address();
		address.setPhoneNumber(0);
		assertEquals(0, address.getPhoneNumber());
	}
	
	@Test
	public void testGetAndSetPhoneNumberNegative() {
		Address address = new Address();
		address.setPhoneNumber(-6);
		assertEquals(-6, address.getPhoneNumber());
	}

}
