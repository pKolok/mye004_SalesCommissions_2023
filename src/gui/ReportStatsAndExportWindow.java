package gui;

import data.Representative;
import data.ReportStatistics;
import enums.FileExtension;
import output.TXTReportWriter;
import output.XMLReportWriter;
import output.HTMLReportWriter;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;

public class ReportStatsAndExportWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField totalValueTextField;
	private JTextField totalItemsTextField;
	private JTextField trouserSalesTextField;
	private JTextField shirtSalesTextField;
	private JTextField coatSalesTextField;
	private JTextField skirtSalesTextField;
	private JTextField commissionTextField;
	private ReceiptStatsSelectionWindow selectionWindow;
	private Representative representative;
	private ReportStatistics reportStatistics;

	public ReportStatsAndExportWindow(final ReceiptStatsSelectionWindow sw, 
			Representative representative, ReportStatistics reportStatistics) {	
		this.selectionWindow = sw;
		this.representative = representative;
		this.reportStatistics = reportStatistics;
		
		initialiseUI();
		fillInSalesStats();
	}	
	
	private void initialiseUI() {
		JPanel resultWindowPanel = new JPanel();
		
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		resultWindowPanel.setBackground(SystemColor.controlHighlight);
		resultWindowPanel.setBorder(new EmptyBorder(10, 25, 10, 25));
		getContentPane().add(resultWindowPanel, BorderLayout.CENTER);

		JButton txtReportButton = new JButton("Export to TXT");
		txtReportButton.setBackground(UIManager.getColor("Button.light"));
		txtReportButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				outputTXTButtonPressed(evt);
			}
		});

		JButton xmlReportButton = new JButton("Export to XML");
		xmlReportButton.setBackground(UIManager.getColor("Button.light"));
		xmlReportButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		xmlReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				outputXMLButtonPressed(evt);
			}
		});
		
		JButton htmlReportButton = new JButton("Export to HTML");
		htmlReportButton.setBackground(UIManager.getColor("Button.light"));
		htmlReportButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		htmlReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				outputHTMLButtonPressed(evt);
			}
		});

		JLabel totalValueLabel = new JLabel("Total Sales Value");
		totalValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel totalItemsLabel = new JLabel("Total Items Sold");
		totalItemsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel trousersSalesLabel = new JLabel("Sales of Trousers");
		trousersSalesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel shirtSalesLabel = new JLabel("Sales of Shirts");
		shirtSalesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel coatSalesLabel = new JLabel("Sales of Coats");
		coatSalesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel skirtSalesLabel = new JLabel("Sales of Skirts");
		skirtSalesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel commissionLabel = new JLabel("Commission");
		commissionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		totalValueTextField = new JTextField();
		totalValueTextField.setEditable(false);
		totalValueTextField.setColumns(10);
		
		totalItemsTextField = new JTextField();
		totalItemsTextField.setEditable(false);
		totalItemsTextField.setColumns(10);
		
		trouserSalesTextField = new JTextField();
		trouserSalesTextField.setEditable(false);
		trouserSalesTextField.setColumns(10);
		
		shirtSalesTextField = new JTextField();
		shirtSalesTextField.setEditable(false);
		shirtSalesTextField.setColumns(10);
		
		coatSalesTextField = new JTextField();
		coatSalesTextField.setEditable(false);
		coatSalesTextField.setColumns(10);
		
		skirtSalesTextField = new JTextField();
		skirtSalesTextField.setEditable(false);
		skirtSalesTextField.setColumns(10);
		
		commissionTextField = new JTextField();
		commissionTextField.setEditable(false);
		commissionTextField.setColumns(10);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBackground(Color.LIGHT_GRAY);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onExitButtonPressed(evt);
			}
		});
		
		JButton backButton = new JButton("Back");
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onBackButtonPressed(evt);	
			}
		});
		
		
		GroupLayout layout = new GroupLayout(resultWindowPanel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// Set up horizontal layout
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				// 1st column
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(totalValueLabel)
					.addComponent(totalItemsLabel)
					.addComponent(trousersSalesLabel)
					.addComponent(shirtSalesLabel)
					.addComponent(coatSalesLabel)
					.addComponent(skirtSalesLabel)
					.addComponent(commissionLabel)
					.addGroup(layout.createSequentialGroup()
						.addComponent(backButton)
						.addComponent(exitButton))
				)
				// 2nd column
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(totalValueTextField)
					.addComponent(totalItemsTextField)
					.addComponent(trouserSalesTextField)
					.addComponent(shirtSalesTextField)
					.addComponent(coatSalesTextField)
					.addComponent(skirtSalesTextField)
					.addComponent(commissionTextField))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     15, GroupLayout.DEFAULT_SIZE)
				// 3rd column
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(txtReportButton, 0, GroupLayout.DEFAULT_SIZE,
							300)
					.addComponent(xmlReportButton, 0, GroupLayout.DEFAULT_SIZE,
							300)
					.addComponent(htmlReportButton, 0, GroupLayout.DEFAULT_SIZE,
							300))
		);
		
		// Set up vertical layout
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(totalValueLabel)
					.addComponent(totalValueTextField)
					.addComponent(txtReportButton, 0, GroupLayout.DEFAULT_SIZE,
							Short.MAX_VALUE))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(totalItemsLabel)
					.addComponent(totalItemsTextField)
					.addComponent(xmlReportButton, 0, GroupLayout.DEFAULT_SIZE,
							Short.MAX_VALUE))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(trousersSalesLabel)
					.addComponent(trouserSalesTextField)
					.addComponent(htmlReportButton, 0, GroupLayout.DEFAULT_SIZE,
							Short.MAX_VALUE))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(shirtSalesLabel)
					.addComponent(shirtSalesTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(coatSalesLabel)
					.addComponent(coatSalesTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(skirtSalesLabel)
					.addComponent(skirtSalesTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(commissionLabel)
					.addComponent(commissionTextField))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     20, GroupLayout.DEFAULT_SIZE)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(backButton)
					.addComponent(exitButton))
		);
		
		resultWindowPanel.setLayout(layout);
	}
	
	private void fillInSalesStats() {

		if(reportStatistics.getTotalSales() >= 0)
			totalValueTextField.setText(Double.toString(
					reportStatistics.getTotalSales()));
		else 
			totalValueTextField.setEnabled(false);
		
		if(reportStatistics.getTotalItems() >= 0)
			totalItemsTextField.setText(Integer.toString(
					reportStatistics.getTotalItems()));
		else 
			totalItemsTextField.setEnabled(false);
		
		if(reportStatistics.getShirtSales() >= 0)
			shirtSalesTextField.setText(Float.toString(
					reportStatistics.getShirtSales()));
		else 
			shirtSalesTextField.setEnabled(false);
		
		if(reportStatistics.getSkirtSales() >= 0)
			skirtSalesTextField.setText(Float.toString(
					reportStatistics.getSkirtSales()));
		else 
			skirtSalesTextField.setEnabled(false);
		
		if(reportStatistics.getCoatsSales() >= 0)
			coatSalesTextField.setText(Float.toString(
					reportStatistics.getCoatsSales()));
		else 
			coatSalesTextField.setEnabled(false);
		
		if(reportStatistics.getTrousersSales() >= 0)
			trouserSalesTextField.setText(Float.toString(
					reportStatistics.getTrousersSales()));
		else 
			shirtSalesTextField.setEnabled(false);
		
		if(reportStatistics.getCommission() >= 0)
			commissionTextField.setText(Double.toString(
					reportStatistics.getCommission()));
		else 
			commissionTextField.setEnabled(false);		
	}
	
	private void outputTXTButtonPressed(ActionEvent evt) {
		FileChooser fileChooser = new FileChooser(FileExtension.TXT);
		String filename = fileChooser.getSaveFileName();

	    TXTReportWriter reportWriter = new TXTReportWriter(
	    		representative, filename);
	    reportWriter.writeReport();
	    JOptionPane.showMessageDialog(null, "Report Saved Successfully");
	}
	
	private void outputXMLButtonPressed(ActionEvent evt) {
		FileChooser fileChooser = new FileChooser(FileExtension.XML);
		String filename = fileChooser.getSaveFileName();
		
		XMLReportWriter reportWriter = new XMLReportWriter(
				representative, filename);
		reportWriter.writeReport();
		JOptionPane.showMessageDialog(null, "Report Saved Successfully");		
	}
	
	private void outputHTMLButtonPressed(ActionEvent evt) {
		FileChooser fileChooser = new FileChooser(FileExtension.HTML);
		String filename = fileChooser.getSaveFileName();
		
		HTMLReportWriter reportWriter = new HTMLReportWriter(
				representative, filename);
		reportWriter.writeReport();
		JOptionPane.showMessageDialog(null, "Report Saved Successfully");		
	}
	
	private void onExitButtonPressed(ActionEvent evt) {
		System.exit(0);		
	}
	
	private void onBackButtonPressed(ActionEvent evt) {
		selectionWindow.setVisible(true);
		this.dispose();	
	}
	
}