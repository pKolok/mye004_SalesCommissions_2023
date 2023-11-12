package gui;

import data.Representative;
import enums.FileExtension;
import input.TXTInput;
import input.XMLInput;
import input.HTMLInput;

import java.io.File;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ReceiptImportWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private static ReceiptImportWindow dialog = new ReceiptImportWindow();
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> representativeList = new JList<String>();
	private ArrayList<Representative> allRepresentatives;
	private Representative selectedRepresentative = null;
	private JButton nextButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = 
					javax.swing.UIManager.getInstalledLookAndFeels();
			for (int i = 0; i < installedLookAndFeels.length; i++) {
				UIManager.setLookAndFeel(
						"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ReceiptImportWindow() {
		initialise();
	}
	
	public void initialise() {
		JPanel inputWindowPanel = new JPanel();
		
		allRepresentatives = new ArrayList<Representative>();
		
		setBackground(new Color(0, 0, 0));
		setBounds(100, 100, 736, 472);
		getContentPane().setLayout(new BorderLayout());
		inputWindowPanel.setBackground(SystemColor.controlHighlight);
		inputWindowPanel.setBorder(new EmptyBorder(10, 25, 10, 25));
		getContentPane().add(inputWindowPanel, BorderLayout.CENTER);
			
		JButton buttonTXTInput = new JButton("Import from txt file");
		buttonTXTInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonTXTInput.setBackground(UIManager.getColor(
				"InternalFrame.borderLight"));
		buttonTXTInput.setFocusPainted(false);
		buttonTXTInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				insertFromTXT(evt);
			}
		});
		
		JButton buttonXMLInput = new JButton("Import from xml file");
		buttonXMLInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonXMLInput.setBackground(UIManager.getColor(
				"InternalFrame.borderLight"));
		buttonXMLInput.setFocusPainted(false);
		buttonXMLInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertFromXML(e);
			}
		});
	
		JButton buttonHTMLInput = new JButton("Import from html file");
		buttonHTMLInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonHTMLInput.setBackground(UIManager.getColor(
				"InternalFrame.borderLight"));
		buttonHTMLInput.setFocusPainted(false);
		buttonHTMLInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertFromHTML(e);
			}
		});
		
		JLabel chooseFilelabel = new JLabel("Chooce receipt file type for loading:");
		chooseFilelabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JLabel salesListLabel = new JLabel("Choose sales sepresentative:");
		salesListLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		representativeList.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		representativeList.setBackground(UIManager.getColor("Button.light"));
		representativeList.setBorder(new LineBorder(new Color(0, 0, 0)));
		representativeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectAgent(e);
			}
		});
		
		nextButton = new JButton("Next");
		nextButton.setToolTipText("");
		nextButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nextButton.setBackground(UIManager.getColor("Button.shadow"));
		nextButton.setVisible(false);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				okButtonPressed(evt);						
			}
		});
		
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		exitButton.setBackground(UIManager.getColor("Button.shadow"));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitButtonPressed(e);
			}
		});
		
		GroupLayout layout = new GroupLayout(inputWindowPanel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// Set up horizontal layout
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(chooseFilelabel)
					.addComponent(buttonTXTInput, 0, 
							GroupLayout.DEFAULT_SIZE, 300)
					.addComponent(buttonXMLInput, GroupLayout.DEFAULT_SIZE, 
							GroupLayout.DEFAULT_SIZE, 300)
					.addComponent(buttonHTMLInput, GroupLayout.DEFAULT_SIZE, 
							GroupLayout.DEFAULT_SIZE, 300)					
					.addGroup(layout.createSequentialGroup()
						.addComponent(exitButton)
						.addComponent(nextButton))
					)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
	                     GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(salesListLabel)
					.addComponent(representativeList, GroupLayout.DEFAULT_SIZE,
							300, Short.MAX_VALUE))
		);
		
		// Set up vertical layout
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(chooseFilelabel)
					.addComponent(salesListLabel))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addComponent(buttonTXTInput)
						.addComponent(buttonXMLInput)
						.addComponent(buttonHTMLInput))
					.addComponent(representativeList, GroupLayout.DEFAULT_SIZE,
							300, Short.MAX_VALUE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(nextButton)
					.addComponent(exitButton))
		);
		
		inputWindowPanel.setLayout(layout);
	}

	private void insertFromTXT(ActionEvent evt) {
		FileChooser fileChooser = new FileChooser(FileExtension.TXT);
		File receiptFile = fileChooser.openReceiptsFile();
		
		// Invalid file choice
		if (receiptFile == null) {
			return;
		}
		
		boolean representativeDuplicate = false;

		TXTInput inputFileTXT = new TXTInput(receiptFile);	
		inputFileTXT.readFile();
		
		Representative representative = inputFileTXT.getAgent();
		representative.setupReceiptFileAppender(FileExtension.TXT, receiptFile);				
		allRepresentatives.add(representative);
		
		for(int i = 0; i< listModel.getSize(); i++){
			if(representative.getName().equals(listModel.getElementAt(i))){
				representativeDuplicate = true;
				break;
			}
		}
		
		if (representativeDuplicate == true) {
			JOptionPane.showMessageDialog(
					null, "Duplicate Sales Representative");

		} else {
			listModel.addElement(representative.getName());
			representativeList.setModel(listModel);
		}
	}
	
	private void insertFromXML(ActionEvent evt2) {
		FileChooser fileChooser = new FileChooser(FileExtension.XML);
		File receiptFile = fileChooser.openReceiptsFile();
		
		// Invalid file choice
		if (receiptFile == null) {
			return;
		}
		
		boolean representativeDuplicate = false;
		
		XMLInput inputFileXML = new XMLInput(receiptFile);	
		inputFileXML.readFile();
		
		Representative representative = inputFileXML.getAgent();
		representative.setupReceiptFileAppender(FileExtension.XML, receiptFile);				
		allRepresentatives.add(representative);
		
		for(int i = 0; i< listModel.getSize(); i++){
			if(representative.getName().equals(listModel.getElementAt(i))){
				representativeDuplicate = true;
				break;
			}
		}
		if (representativeDuplicate == true) {
			JOptionPane.showMessageDialog(
					null, "Duplicate Sales Representative");
		} else {
			listModel.addElement(representative.getName());
			representativeList.setModel(listModel);
		}

	}
	
	private void insertFromHTML(ActionEvent evt2) {
		FileChooser fileChooser = new FileChooser(FileExtension.HTML);
		File receiptFile = fileChooser.openReceiptsFile();
		
		// Invalid file choice
		if (receiptFile == null) {
			return;
		}
		
		boolean representativeDuplicate = false;
		
		HTMLInput inputFileXML = new HTMLInput(receiptFile);	
		inputFileXML.readFile();
		
		Representative representative = inputFileXML.getAgent();
		representative.setupReceiptFileAppender(FileExtension.HTML, receiptFile);
		allRepresentatives.add(representative);
		
		for(int i = 0; i < listModel.getSize(); i++){
			if(representative.getName().equals(listModel.getElementAt(i))){
				representativeDuplicate = true;
			}
		}
		if (representativeDuplicate == true) {
			JOptionPane.showMessageDialog(
					null, "Duplicate Sales Representative");
		} else {
			listModel.addElement(representative.getName());
			representativeList.setModel(listModel);
		}
	}
	
	private void selectAgent(MouseEvent e) {
		String representativeName;
        if(representativeList.getSelectedIndex() >= 0){
            representativeName = representativeList.getSelectedValue().toString();
            for(int i = 0; i < allRepresentatives.size(); i++){
                if(representativeName.equals(allRepresentatives.get(i).getName())){
            		selectedRepresentative = allRepresentatives.get(i);
            		nextButton.setVisible(true);
            		break;
                }
            }
        }
	}
	
	private void okButtonPressed(ActionEvent evt) {
		if(representativeList.isSelectionEmpty()){
			JOptionPane.showMessageDialog(
					null, "Please select a Sale's Representative");
			return;
		}

		ReceiptStatsSelectionWindow sw = new ReceiptStatsSelectionWindow(
				dialog, selectedRepresentative);
		this.setVisible(false);
		sw.setVisible(true);
	}
	
	
	private void exitButtonPressed(ActionEvent e) {
		System.exit(0);	
	}

}