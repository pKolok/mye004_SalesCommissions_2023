package tests.output;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Representative;
import data.Receipt;
import data.SaleItem;
import output.XMLReport;

public class XMLReportTest {

	@Test
	public void testSaveFileHappyDay() {
		Representative agent = new Representative();
		agent.setName("Odysseus");
		agent.setAfm("123456789");
		
		Receipt receipt1 = new Receipt();
		receipt1.setSales(1000);
		Receipt receipt2 = new Receipt(SaleItem.COAT);
		receipt2.setSales(600);
		Receipt receipt3 = new Receipt(SaleItem.SHIRT);
		receipt3.setSales(400);
		Receipt receipt4 = new Receipt(SaleItem.SKIRT);
		receipt4.setSales(700);
		Receipt receipt5 = new Receipt(SaleItem.TROUSERS);
		receipt5.setSales(300);
		
		XMLReport xmlReport = new XMLReport(agent);
		xmlReport.saveFile();
		
		
	}

}
