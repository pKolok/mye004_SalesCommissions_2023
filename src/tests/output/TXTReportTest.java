package tests.output;

import static org.junit.Assert.*;

import data.Agent;
import data.Receipt;
import data.SaleItem;
import output.TXTReport;

import org.junit.Test;


public class TXTReportTest {

	@Test
	public void testSaveFileHappyDay() {
		Agent agent = new Agent();
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
		
		TXTReport txtReport = new TXTReport(agent);
		txtReport.saveFile();
	}

}
