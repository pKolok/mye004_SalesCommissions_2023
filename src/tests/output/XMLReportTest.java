package tests.output;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Agent;
import data.Coat;
import data.Receipt;
import data.Shirt;
import data.Skirt;
import data.Trouser;
import output.XMLReport;

public class XMLReportTest {

	@Test
	public void testSaveFileHappyDay() {
		Agent agent = new Agent();
		agent.setName("Odysseus");
		agent.setAfm("123456789");
		
		Receipt receipt1 = new Receipt();
		receipt1.setSales(1000);
		Trouser receipt2 = new Trouser();
		receipt2.setSales(600);
		Skirt receipt3 = new Skirt();
		receipt3.setSales(400);
		Shirt receipt4 = new Shirt();
		receipt4.setSales(700);
		Coat receipt5 = new Coat();
		receipt5.setSales(300);
		
		XMLReport xmlReport = new XMLReport(agent);
		xmlReport.saveFile();
		
		
	}

}
