package tests.output;

import static org.junit.Assert.*;

import data.Representative;
import data.Address;
import data.Company;
import data.Receipt;
import data.SaleItem;
import output.TXTReport;

import org.junit.Test;


public class TXTReportTest {

	@Test
	public void testSaveFileHappyDay() {
		Representative agent = new Representative("Odysseus", "123456789");
		
		Address address = new Address("", "", "", 0);
		Company company = new Company("", address);
		
		Receipt receipt1 = new Receipt(0, "", 1000, SaleItem.OTHER, 0, company);
		agent.addRepresentativeReceipt(receipt1);
		Receipt receipt2 = new Receipt(0, "", 600, SaleItem.COAT, 0, company);
		agent.addRepresentativeReceipt(receipt2);
		Receipt receipt3 = new Receipt(0, "", 400, SaleItem.SHIRT, 0, company);
		agent.addRepresentativeReceipt(receipt3);
		Receipt receipt4 = new Receipt(0, "", 700, SaleItem.SKIRT, 0, company);
		agent.addRepresentativeReceipt(receipt4);
		Receipt receipt5 = new Receipt(0, "", 300, SaleItem.TROUSERS, 0,
				company);
		agent.addRepresentativeReceipt(receipt5);
		
		TXTReport txtReport = new TXTReport(agent);
		txtReport.saveFile();
		
		fail();
	}

}