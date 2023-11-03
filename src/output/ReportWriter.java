package output;

import data.Representative;

public abstract class ReportWriter {

	protected Representative representative;
	protected String fileName;
	
	protected abstract void createFile();
	protected abstract void writeRepresentativeDetails();
	protected abstract void writeSalesSummary();
	protected abstract void closeFile();
	
	public void writeReport(String fileName) {
		createFile();
		writeRepresentativeDetails();
		writeSalesSummary();
		closeFile();
	}
	
}