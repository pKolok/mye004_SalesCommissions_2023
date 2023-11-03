package output;

import data.Representative;

public abstract class ReportWriter {

	protected Representative agent;
	
	public abstract void writeReport();
	
}