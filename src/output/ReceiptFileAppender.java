package output;

import java.io.File;

import data.Receipt;

public abstract class ReceiptFileAppender {

	protected File fileToAppend;
	protected Receipt receipt;
		
	public abstract void appendFile();
	
	public  void setFileToAppend(File fileToAppend) {
		this.fileToAppend = fileToAppend;
	}
	
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

}