package tests.data;

import data.Address;

import static org.junit.Assert.*;
import org.junit.Test;

public class AddressTest {

	// ------------------------------ Get Country ------------------------------
	@Test
	public void testGetAndSetCountryHappyDay() {
		Address address = new Address("Greece", "", "", 0);
		assertEquals("Greece", address.getCountry());
	}
	
	@Test
	public void testGetAndSetCountryEmptyString() {
		Address address = new Address("", "", "", 0);
		assertEquals("", address.getCountry());
	}

	@Test
	public void testGetAndSetCountryNull() {
		Address address = new Address(null, "", "", 0);
		assertEquals(null, address.getCountry());
	}
	
	// -------------------------------- Get City -------------------------------
	@Test
	public void testGetAndSetCityHappyDay() {
		Address address = new Address("", "Ioannina", "", 0);
		assertEquals("Ioannina", address.getCity());
	}
	
	@Test
	public void testGetAndSetCityEmptyString() {
		Address address = new Address("", "", "", 0);
		assertEquals("", address.getCity());
	}

	@Test
	public void testGetAndSetCityNull() {
		Address address = new Address("", null, "", 0);
		assertEquals(null, address.getCity());
	}
	
	// ------------------------------ Get Street -------------------------------
	@Test
	public void testGetAndSetStreetHappyDay() {
		Address address = new Address("", "", "Hollywood Ave", 0);
		assertEquals("Hollywood Ave", address.getStreet());
	}
	
	@Test
	public void testGetAndSetStreetEmptyString() {
		Address address = new Address("", "", "", 0);
		assertEquals("", address.getStreet());
	}

	@Test
	public void testGetAndSetStreetNull() {
		Address address = new Address("", "", null, 0);
		assertEquals(null, address.getStreet());
	}
	
	// -------------------------- Get Street Number ----------------------------
	@Test
	public void testGetAndSetStreetNumberHappyDay() {
		Address address = new Address("", "", "", 10);
		assertEquals(10, address.getStreetNumber());
	}
	
	@Test
	public void testGetAndSetStreetNumberZero() {
		Address address = new Address("", "", "", 0);
		assertEquals(0, address.getStreetNumber());
	}
	
	@Test
	public void testGetAndSetStreetNumberNegative() {
		Address address = new Address("", "", "", -6);
		assertEquals(-6, address.getStreetNumber());
	}

}