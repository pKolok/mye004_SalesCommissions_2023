package output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReceiptHtmlFileAppender extends ReceiptFileAppender {

	FileWriter fileWriter;
	ArrayList<String> fileContent;
	
	public ReceiptHtmlFileAppender(File file) {
		super(file);
		
		fileContent = new ArrayList<String>();
	}

	@Override
	protected void openFile() {
		getExistingFileContent();
		
	    try {
	    	fileWriter = new FileWriter(fileToAppend);
	    	writeExistingContentBeforeEnd();
	    	fileWriter.write("    <br>");
	    	fileWriter.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void append(String label, String value) {
		try {
			fileWriter.write("    <p>");
			fileWriter.write(label + ": ");
			fileWriter.write(value);
			fileWriter.write("</p>");
			fileWriter.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void closeFile() {
		try {
			fileWriter.write("  </body>");
			fileWriter.write("\n");
			fileWriter.write("</html>");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void getExistingFileContent() {	
	    try {
	    	BufferedReader bufferedReader = new BufferedReader(
	    			new FileReader(fileToAppend));
	    	
	    	String line;
	    	fileContent.clear();
	    	while ((line = bufferedReader.readLine()) != null) {
	    		fileContent.add(line);
	    	}
	    	
	    	bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeExistingContentBeforeEnd() {
		try {
			for (int i = 0; i < fileContent.size() - 2; ++i) {
				fileWriter.write(fileContent.get(i));
				fileWriter.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}