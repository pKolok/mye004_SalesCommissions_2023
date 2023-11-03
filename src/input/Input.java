package input;

import data.Representative;
import data.Receipt;

import java.io.File;
import java.util.ArrayList;

public abstract class Input {
	
	protected Representative representative;
	protected File inputFile;

	protected abstract void openFile();
	protected abstract Representative getRepresentative();
	protected abstract ArrayList<Receipt> getReceipts();
	protected abstract void closeFile();
	
	public void readFile() {
		openFile();
		
		representative = getRepresentative();
		
		ArrayList<Receipt> receipts = getReceipts();
		for (Receipt receipt : receipts) {
			representative.addRepresentativeReceipt(receipt);
		}
		
		closeFile();
	}
	
	public Representative getAgent() {
		return representative;
	}
	
}