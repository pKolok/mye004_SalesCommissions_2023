package tests.data;

import static org.junit.Assert.*;

import data.Address;
import data.Company;

import org.junit.Test;

public class CompanyTest {

	// ---------------------------- Get Company Address ------------------------
	@Test
	public void testGetCompanyAddress() {
		Company company = new Company();
		assertTrue(company.getCompanyAddress() instanceof Address);
	}
	
	// ---------------------------- Get and Set Name ---------------------------
	@Test
	public void testGetAndSetNameHappyDay() {
		Company company = new Company();
		company.setName("Umbrella");
		assertEquals(company.getName(), "Umbrella");
	}
	
	@Test
	public void testGetAndSetNameEmptyString() {
		Company company = new Company();
		company.setName("");
		assertEquals(company.getName(), "");
	}
	
	@Test
	public void testGetAndSetNameMNull() {
		Company company = new Company();
		company.setName(null);
		assertEquals(company.getName(), null);
	}

}