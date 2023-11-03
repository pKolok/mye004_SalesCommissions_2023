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
		
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(
				fileExtension + " files (*." + fileExtension+ ")", 
				fileExtension);
		fileChooser.addChoosableFileFilter(txtFilter);
		fileChooser.setFileFilter(txtFilter);
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
}