package tests.data;

import static org.junit.Assert.*;

import data.Address;
import data.Company;

import org.junit.Test;

public class CompanyTest {

	// ---------------------------- Get Company Address ------------------------
	@Test
	public void testGetCompanyAddress() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		assertTrue(company.getCompanyAddress() instanceof Address);
	}
	
	// ---------------------------- Get Name ---------------------------
	@Test
	public void testGetAndSetNameHappyDay() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("Umbrella", address);
		assertEquals(company.getName(), "Umbrella");
	}
	
	@Test
	public void testGetAndSetNameEmptyString() {
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		assertEquals(company.getName(), "");
	}
	
	@Test
	public void testGetAndSetNameMNull() {
		Address address = new Address("", "", "", 0);
		Company company = new Company(null, address);
		assertEquals(company.getName(), null);
	}

}