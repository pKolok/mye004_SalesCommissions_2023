package output;

import data.Representative;

public abstract class ReportWriter {

	protected Representative agent;
	
	protected abstract void openFile();
	protected abstract void writeRepresentativeDetails();
	protected abstract void writeSalesSummary();
	protected abstract void closeFile();
	
	public void writeReport() {
		openFile();
		writeRepresentativeDetails();
		writeSalesSummary();
		closeFile();
	}
	
}