package output;

import java.io.File;

import data.Receipt;

public abstract class ReceiptFileAppender {

	protected File fileToAppend;
	
	protected abstract void openFile();
	protected abstract void append(String label, String value);
	protected abstract void closeFile();
	
	protected ReceiptFileAppender(File file) {
		this.fileToAppend = file;
	}

	public void appendFile(Receipt receipt) {
		openFile();
		append("ReceiptID", Integer.toString(receipt.getReceiptID()));
		append("Date", receipt.getDate());
		append("Kind", receipt.getKind().name());
		append("Sales", Double.toString(receipt.getSales()));
		append("Items", Integer.toString(receipt.getItems()));
		append("Company", receipt.getCompany().getName());
		append("Country", receipt.getCompany().getCompanyAddress()
				.getCountry());
		append("City", receipt.getCompany().getCompanyAddress().getCity());
		append("Street", receipt.getCompany().getCompanyAddress().getStreet());
		append("Number", Integer.toString(receipt.getCompany()
       			.getCompanyAddress().getStreetNumber()));
		closeFile();
	}
	
}