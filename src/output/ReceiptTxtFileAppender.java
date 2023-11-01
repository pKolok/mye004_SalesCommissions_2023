package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptTxtFileAppender extends ReceiptFileAppender{

	private FileWriter fileWriter;
	
	public ReceiptTxtFileAppender(File file) {
		super(file);
	}
	
	protected void openFile() {
		try {
			fileWriter = new FileWriter(fileToAppend, true);
			fileWriter.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	protected void append(String label, String value) {
		try {
			fileWriter.write(label + ": ");
			fileWriter.write(value);
			fileWriter.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void closeFile() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}