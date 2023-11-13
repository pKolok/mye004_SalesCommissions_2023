package tests.data;

import data.Address;

import static org.junit.Assert.*;
import org.junit.Test;

public class AddressTest {

	// ------------------------------ Get Country ------------------------------
	@Test
	public void testGetCountryHappyDay() {
		Address address = new Address("Greece", "", "", 0);
		assertEquals("Greece", address.getCountry());
	}
	
	@Test
	public void testGetCountryEmptyString() {
		Address address = new Address("", "", "", 0);
		assertEquals("", address.getCountry());
	}

	@Test
	public void testGetCountryNull() {
		Address address = new Address(null, "", "", 0);
		assertEquals(null, address.getCountry());
	}
	
	// -------------------------------- Get City -------------------------------
	@Test
	public void testGetCityHappyDay() {
		Address address = new Address("", "Ioannina", "", 0);
		assertEquals("Ioannina", address.getCity());
	}
	
	@Test
	public void testGetCityEmptyString() {
		Address address = new Address("", "", "", 0);
		assertEquals("", address.getCity());
	}

	@Test
	public void testGetCityNull() {
		Address address = new Address("", null, "", 0);
		assertEquals(null, address.getCity());
	}
	
	// ------------------------------ Get Street -------------------------------
	@Test
	public void testGetStreetHappyDay() {
		Address address = new Address("", "", "Hollywood Ave", 0);
		assertEquals("Hollywood Ave", address.getStreet());
	}
	
	@Test
	public void testGetStreetEmptyString() {
		Address address = new Address("", "", "", 0);
		assertEquals("", address.getStreet());
	}

	@Test
	public void testGetStreetNull() {
		Address address = new Address("", "", null, 0);
		assertEquals(null, address.getStreet());
	}
	
	// -------------------------- Get Street Number ----------------------------
	@Test
	public void testGetStreetNumberHappyDay() {
		Address address = new Address("", "", "", 10);
		assertEquals(10, address.getStreetNumber());
	}
	
	@Test
	public void testGetStreetNumberZero() {
		Address address = new Address("", "", "", 0);
		assertEquals(0, address.getStreetNumber());
	}
	
	@Test
	public void testGetStreetNumberNegative() {
		Address address = new Address("", "", "", -6);
		assertEquals(-6, address.getStreetNumber());
	}

}