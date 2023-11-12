package gui;

import data.Representative;
import data.Receipt;
import data.SaleItem;
import data.Address;
import data.Company;
import data.ReportStatistics;

import java.util.Arrays;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ReceiptStatsSelectionWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private ReceiptImportWindow inputDialog;
	private Representative selectedRepresentative;
	private JTextField representativeTextField;
	private JCheckBox totalSalesCheckBox;
	private JCheckBox totalItemsCheckBox;
	private JCheckBox categoryCheckBox;
	private JCheckBox skirtCheckBox;
	private JCheckBox shirtCheckBox;
	private JCheckBox trousersCheckBox;
	private JCheckBox coatCheckBox;
	private JCheckBox commissionCheckBox;
	private JTextField receiptIDTextField;
	private JTextField dateTextField;
	private JTextField kindTextField;
	private JTextField salesTextField;
	private JTextField itemsTextField;
	private JTextField companyTextField;
	private JTextField countryTextField;
	private JTextField cityTextField;
	private JTextField streetTextField;
	private JTextField numberTextField;
	private JTextField addedReceiptsTextField;
	private int numOfReceipts = 0;
	
	public ReceiptStatsSelectionWindow(ReceiptImportWindow dialog, 
			Representative representative) {
		this.inputDialog = dialog;
		this.selectedRepresentative = representative;
		
		initialiseUI();
		fillInSalesRepresentative();
	}
	
	public void initialiseUI() {
		JPanel selectionWindowPanel = new JPanel();
		
		setBackground(new Color(0, 0, 0));
		setBounds(100, 100, 800, 510);
		getContentPane().setLayout(new BorderLayout());
		selectionWindowPanel.setBackground(SystemColor.controlHighlight);
		selectionWindowPanel.setBorder(new EmptyBorder(10, 25, 10, 25));
		getContentPane().add(selectionWindowPanel, BorderLayout.CENTER);
		
		JLabel representativeLabel = new JLabel("Sales Representative:");
		representativeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		representativeTextField = new JTextField();
		representativeTextField.setBackground(SystemColor.controlHighlight);
		representativeTextField.setEditable(false);
		representativeTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		representativeTextField.setColumns(10);

		JLabel selectStatsLabel = new JLabel("Select Summary Statistics");
		selectStatsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		totalSalesCheckBox = new JCheckBox("Total Sales Value");
		totalSalesCheckBox.setBackground(SystemColor.controlHighlight);
		totalSalesCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		totalItemsCheckBox = new JCheckBox("Total Items Sold");
		totalItemsCheckBox.setBackground(SystemColor.controlHighlight);
		totalItemsCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		shirtCheckBox = new JCheckBox("Shirts");
		shirtCheckBox.setBackground(SystemColor.controlHighlight);
		shirtCheckBox.setEnabled(false);
		shirtCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		trousersCheckBox = new JCheckBox("Trousers");
		trousersCheckBox.setBackground(SystemColor.controlHighlight);
		trousersCheckBox.setEnabled(false);
		trousersCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		coatCheckBox = new JCheckBox("Coats");
		coatCheckBox.setBackground(SystemColor.controlHighlight);
		coatCheckBox.setEnabled(false);
		coatCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		skirtCheckBox = new JCheckBox("Skirts");
		skirtCheckBox.setBackground(SystemColor.controlHighlight);
		skirtCheckBox.setEnabled(false);
		skirtCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		categoryCheckBox = new JCheckBox("Sales of certain category");
		categoryCheckBox.setBackground(SystemColor.controlHighlight);
		categoryCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		categoryCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isEnabled = false;
				if (categoryCheckBox.isSelected() == true) {
					isEnabled = true;
				} else {
					isEnabled = false;
				}
				
				skirtCheckBox.setEnabled(isEnabled);
				shirtCheckBox.setEnabled(isEnabled);
				coatCheckBox.setEnabled(isEnabled);
				trousersCheckBox.setEnabled(isEnabled);
			}
		});
		
		commissionCheckBox = new JCheckBox("Sales Representative's Commission");
		commissionCheckBox.setBackground(SystemColor.controlHighlight);
		commissionCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		// -------------------------- Add new Receipt --------------------------
		final JLabel receiptIDLabel = new JLabel("Receipt ID:");
		receiptIDLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		receiptIDLabel.setVisible(false);

		receiptIDTextField = new JTextField();
		receiptIDTextField.setVisible(false);
		receiptIDTextField.setColumns(10);

		final JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		dateLabel.setVisible(false);

		dateTextField = new JTextField();
		dateTextField.setVisible(false);
		dateTextField.setColumns(10);

		final JLabel kindLabel = new JLabel("Kind:");
		kindLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		kindLabel.setVisible(false);

		kindTextField = new JTextField();
		kindTextField.setVisible(false);
		kindTextField.setColumns(10);

		final JLabel salesLabel = new JLabel("Sales:");
		salesLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		salesLabel.setVisible(false);

		salesTextField = new JTextField();
		salesTextField.setVisible(false);
		salesTextField.setColumns(10);

		final JLabel itemsLabel = new JLabel("Items:");
		itemsLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		itemsLabel.setVisible(false);

		itemsTextField = new JTextField();
		itemsTextField.setVisible(false);
		itemsTextField.setColumns(10);

		final JLabel companyLabel = new JLabel("Company:");
		companyLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		companyLabel.setVisible(false);

		companyTextField = new JTextField();
		companyTextField.setVisible(false);
		companyTextField.setColumns(10);

		final JLabel countryLabel = new JLabel("Country:");
		countryLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		countryLabel.setVisible(false);

		countryTextField = new JTextField();
		countryTextField.setVisible(false);
		countryTextField.setColumns(10);

		final JLabel cityLabel = new JLabel("City:");
		cityLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cityLabel.setVisible(false);

		cityTextField = new JTextField();
		cityTextField.setVisible(false);
		cityTextField.setColumns(10);

		final JLabel streetLabel = new JLabel("Street:");
		streetLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		streetLabel.setVisible(false);

		streetTextField = new JTextField();
		streetTextField.setVisible(false);
		streetTextField.setColumns(10);

		final JLabel numberLabel = new JLabel("Number:");
		numberLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		numberLabel.setVisible(false);

		numberTextField = new JTextField();
		numberTextField.setVisible(false);
		numberTextField.setColumns(10);

		final JButton addReceiptButton = new JButton("Add");
		addReceiptButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addReceiptButton.setVisible(false);
		addReceiptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onAddNewReceiptButtonPressed(evt);
			}
		});
		
		final List<JLabel> newReceiptLabels = Arrays.asList(receiptIDLabel, 
				dateLabel, kindLabel, salesLabel, itemsLabel, companyLabel, 
				countryLabel, cityLabel, streetLabel, numberLabel);
		final List<JTextField> newReceiptFields = Arrays.asList(
				receiptIDTextField, dateTextField, kindTextField, 
				salesTextField, itemsTextField, companyTextField, 
				countryTextField, cityTextField, streetTextField,
				numberTextField);
		
		final JToggleButton addReceiptToggleButton = new JToggleButton(
				"Add New Receipt");
		addReceiptToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addReceiptToggleButton.setBackground(new Color(135, 206, 235));
		addReceiptToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				boolean isSelected = false;
				if(addReceiptToggleButton.isSelected() == true) {
					isSelected = true;
					addReceiptToggleButton.setText("Cancel New Receipt");
				} else {
					isSelected = false;
					addReceiptToggleButton.setText("Add New Receipt");
				}
				
				for (int i = 0; i < newReceiptLabels.size(); ++i) {
					newReceiptLabels.get(i).setVisible(isSelected);
					newReceiptFields.get(i).setVisible(isSelected);
				}
				addReceiptButton.setVisible(isSelected);
			}
		});

		// ---------------------------------------------------------------------
		
		JLabel addedReceiptsLabel = new JLabel("Added Receipts:");

		addedReceiptsTextField = new JTextField();
		addedReceiptsTextField.setText("0");
		addedReceiptsTextField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		addedReceiptsTextField.setColumns(10);

		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onBackButtonPressed(evt);
			}
		});

		JButton nextButton = new JButton("Next");
		nextButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onNextButtonPressed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(selectionWindowPanel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// TODO: finalise design - remove dead code
		// Set up horizontal layout
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				// 1st column
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(representativeLabel)
					.addComponent(selectStatsLabel)
					.addComponent(totalSalesCheckBox)
					.addComponent(totalItemsCheckBox)
					.addComponent(categoryCheckBox)
					.addGroup(layout.createSequentialGroup()
						.addGap(25)
						.addComponent(shirtCheckBox))
					.addGroup(layout.createSequentialGroup()
						.addGap(25)
						.addComponent(trousersCheckBox))
					.addGroup(layout.createSequentialGroup()
						.addGap(25)
						.addComponent(coatCheckBox))
					.addGroup(layout.createSequentialGroup()
						.addGap(25)
						.addComponent(skirtCheckBox))
					.addComponent(commissionCheckBox)
					.addGroup(layout.createSequentialGroup()
						.addComponent(backButton)
						.addComponent(nextButton))
				)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
	                     30, 50)
				// 2nd column
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(representativeTextField, 
							GroupLayout.DEFAULT_SIZE, 200, 200)
					.addComponent(addReceiptToggleButton, 
							GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
							200)
//					.addGroup(layout.createSequentialGroup()
						.addComponent(receiptIDLabel)
//						.addComponent(receiptIDTextField))
//					.addGroup(layout.createSequentialGroup()
						.addComponent(dateLabel)
//						.addComponent(dateTextField))
//					.addGroup(layout.createSequentialGroup()
						.addComponent(kindLabel)
//						.addComponent(kindTextField))
//					.addGroup(layout.createSequentialGroup()
						.addComponent(salesLabel)
//						.addComponent(salesTextField))
//					.addGroup(layout.createSequentialGroup()
						.addComponent(itemsLabel)
//						.addComponent(itemsTextField))
//					.addGroup(layout.createSequentialGroup()
						.addComponent(companyLabel)
//						.addComponent(companyTextField))
//					.addGroup(layout.createSequentialGroup()
						.addComponent(countryLabel)
//						.addComponent(countryTextField))
//					.addGroup(layout.createSequentialGroup()
						.addComponent(cityLabel)
//						.addComponent(cityTextField))
//					.addGroup(layout.createSequentialGroup()
						.addComponent(streetLabel)
//						.addComponent(streetTextField))
//					.addGroup(layout.createSequentialGroup()
						.addComponent(numberLabel)
//						.addComponent(numberTextField))
					.addComponent(addReceiptButton)
//					.addGroup(layout.createSequentialGroup()
//						.addComponent(addedReceiptsLabel)
//						.addComponent(addedReceiptsTextField, 
//								GroupLayout.DEFAULT_SIZE, 10, 25))
				)
				// 3rd column
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(receiptIDTextField)
					.addComponent(dateTextField)
					.addComponent(kindTextField)
					.addComponent(salesTextField)
					.addComponent(itemsTextField)
					.addComponent(companyTextField)
					.addComponent(countryTextField)
					.addComponent(cityTextField)
					.addComponent(streetTextField)
					.addComponent(numberTextField)
					.addGroup(layout.createSequentialGroup()
						.addComponent(addedReceiptsLabel)
						.addComponent(addedReceiptsTextField, 
								GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
				)
		);
		
		// Set up vertical layout
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(representativeLabel)
					.addComponent(representativeTextField, 
							GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
							50))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
	                     50, 100)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(selectStatsLabel)
					.addComponent(addReceiptToggleButton))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(totalSalesCheckBox)
					.addComponent(receiptIDLabel)
					.addComponent(receiptIDTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(totalItemsCheckBox)
					.addComponent(dateLabel)
					.addComponent(dateTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(categoryCheckBox)
					.addComponent(kindLabel)
					.addComponent(kindTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(shirtCheckBox)
					.addComponent(salesLabel)
					.addComponent(salesTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(trousersCheckBox)
					.addComponent(itemsLabel)
					.addComponent(itemsTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(coatCheckBox)
					.addComponent(companyLabel)
					.addComponent(companyTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(skirtCheckBox)
					.addComponent(countryLabel)
					.addComponent(countryTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(commissionCheckBox)
					.addComponent(cityLabel)
					.addComponent(cityTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(streetLabel)
					.addComponent(streetTextField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(numberLabel)
					.addComponent(numberTextField))
				.addComponent(addReceiptButton)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     100, 100)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(backButton)
					.addComponent(nextButton)
					.addComponent(addedReceiptsLabel)
					.addComponent(addedReceiptsTextField, 
							GroupLayout.DEFAULT_SIZE, 25, 25))
		);
		
		selectionWindowPanel.setLayout(layout);
	}
	
	private void fillInSalesRepresentative() {
		try {
			representativeTextField.setText(selectedRepresentative.getName());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"Invalid Sales Representative");
		}
	}
	
	private void onAddNewReceiptButtonPressed(ActionEvent evt) {
		if (areAllReceiptFieldsEmpty()) {
			JOptionPane.showMessageDialog(null,"Please fill in receipt fields");
			return;
		}
			
		addReceipt();
		appendFile();
		resetReceiptFields();
	}
	
	private void onBackButtonPressed(ActionEvent evt) {
		dispose();
		inputDialog.setVisible(true);		
	}
	
	private void onNextButtonPressed(ActionEvent evt) {
		double totalSales = -1;
		int totalItems = -1;
		float shirtSales = -1;
		float skirtSales = -1;
		float coatsSales = -1;
		float trousersSales = -1;
		double commission = -1;
		
		if(totalSalesCheckBox.isSelected())
			 totalSales = selectedRepresentative.calculateTotalSales();
		
		if(totalItemsCheckBox.isSelected())
			totalItems = selectedRepresentative.calculateTotalItems();
		
		if(shirtCheckBox.isSelected())
			shirtSales = selectedRepresentative.calculateItemSales(
					SaleItem.SHIRT);

		if(skirtCheckBox.isSelected()  )
			skirtSales = selectedRepresentative.calculateItemSales(
					SaleItem.SKIRT);

		if(coatCheckBox.isSelected())
			coatsSales = selectedRepresentative.calculateItemSales(
					SaleItem.COAT);
		
		if(trousersCheckBox.isSelected())
			trousersSales = selectedRepresentative.calculateItemSales(
					SaleItem.TROUSERS);
		
		if(commissionCheckBox.isSelected())
			commission = selectedRepresentative.calculateCommission();
		
		ReportStatistics reportStatistics = new ReportStatistics(totalSales, 
				totalItems, shirtSales, skirtSales, trousersSales, coatsSales, 
				commission);

		ReportStatsAndExportWindow rs = new ReportStatsAndExportWindow(
				this,selectedRepresentative, reportStatistics);
		rs.setVisible(true);
		this.setVisible(false);		
	}
	
	private boolean areAllReceiptFieldsEmpty() {
		return receiptIDTextField.getText().isEmpty() 
				&& dateTextField.getText().isEmpty() 
				&& kindTextField.getText().isEmpty() 
				&& salesTextField.getText().isEmpty()
				&& itemsTextField.getText().isEmpty()
				&& companyTextField.getText().isEmpty()
				&& countryTextField.getText().isEmpty()
				&& cityTextField.getText().isEmpty()
				&& streetTextField.getText().isEmpty()
				&& numberTextField.getText().isEmpty();
	}
	
	private void addReceipt() {
		Receipt receipt = getReceiptFromUserInput();
		
		if (receipt == null) 
			return;
		
		selectedRepresentative.addRepresentativeReceipt(receipt);
		
		numOfReceipts++;
		addedReceiptsTextField.setText(Integer.toString(numOfReceipts));
		
		JOptionPane.showMessageDialog(null,"Receipt added to the list of Sale's"
				+ " Representative " + selectedRepresentative.getName());
	}
	
	private void appendFile() {
		Receipt receipt = getReceiptFromUserInput();
		
		if (receipt == null) 
			return;
		
		selectedRepresentative.getFileAppender().appendFile(receipt);
	}

	private void resetReceiptFields() {
		receiptIDTextField.setText("");	
		dateTextField.setText("");			
		kindTextField.setText("");	
		salesTextField.setText("");
		itemsTextField.setText("");	
		companyTextField.setText("");	
		countryTextField.setText("");	
		cityTextField.setText("");	
		streetTextField.setText("");	
		numberTextField.setText("");	
	}
	
	private Receipt getReceiptFromUserInput() {
		try {
			int id = Integer.parseInt(receiptIDTextField.getText());
			String date = dateTextField.getText();
			Double sales = Double.parseDouble(salesTextField.getText());
			int items = Integer.parseInt(itemsTextField.getText());
			String kind = kindTextField.getText();
			String companyName = companyTextField.getText();
			String country = countryTextField.getText();
			String city = cityTextField.getText();
			String street = streetTextField.getText();
			int streetNumber = Integer.parseInt(numberTextField.getText());
			Address address = new Address(country, city, street, streetNumber);
			Company company = new Company(companyName, address);
			
			return new Receipt(id, date, sales, kind, items, company);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(
					null,"Can not successfully parse user input");
			return null;
		}
	}
	

		
}