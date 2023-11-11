package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import enums.FileExtension;

public class FileChooser {
	
	JFileChooser fileChooser;
	String fileExtension;
	
	public FileChooser(FileExtension _fileExtension) {
		this.fileExtension = _fileExtension.toString(); 
		
		fileChooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				fileExtension + " files (*." + fileExtension+ ")", 
				fileExtension);
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
	}
	
	public String getSaveFileName() {
		fileChooser.setDialogTitle("Save Report As");
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooser.setSelectedFile(new File("Report." + fileExtension));
		
		int userSelection = fileChooser.showSaveDialog(null);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			String filename = fileChooser.getSelectedFile().toString();
		    if (!filename.endsWith("." + fileExtension)) {
		    	filename += "." + fileExtension;		    	
		    }
		    return filename;
		}
		return null;
	}
	
	// TODO: return some kind of warning when invalid file is selected
	public File openReceiptsFile() {
		fileChooser.setDialogTitle("Open Receipts File");
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		fileChooser.showOpenDialog(null);
		
		File receiptFile = fileChooser.getSelectedFile();
		
		// User cancelled the dialog
		if (receiptFile == null) {
			return null;
		}
		
		// User entered invalid information and pressed OK
		String filename = receiptFile.toString();
		if (!filename.endsWith("." + fileExtension)) {
	    	return null;		    	
	    }
		
		return receiptFile;
	}
}