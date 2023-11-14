package gui;

import data.Representative;
import data.enums.SaleItem;
import data.Receipt;
import data.Address;
import data.Company;
import data.ReportStatistics;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.text.DateFormatter;

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
	private JSpinner receiptIDField;
	private JFormattedTextField dateField;
	private JComboBox<String> kindField;
	private JSpinner salesField;
	private JSpinner itemsField;
	private JTextField companyTextField;
	private JTextField countryTextField;
	private JTextField cityTextField;
	private JTextField streetTextField;
	private JSpinner numberField;
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
		representativeLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		representativeTextField = new JTextField();
		representativeTextField.setBackground(SystemColor.controlHighlight);
		representativeTextField.setEditable(false);
		representativeTextField.setFont(new Font("Times New Roman", Font.BOLD, 16));
		representativeTextField.setColumns(10);

		JLabel selectStatsLabel = new JLabel("Select Summary Statistics");
		selectStatsLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));

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
		receiptIDLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		receiptIDLabel.setVisible(false);

		SpinnerNumberModel idModel = new SpinnerNumberModel(0, -1,
				Short.MAX_VALUE, 1);
		receiptIDField = new JSpinner(idModel);
		receiptIDField.setVisible(false);
		receiptIDField.setValue(-1);

		final JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		dateLabel.setVisible(false);

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter df = new DateFormatter(format);
		dateField = new JFormattedTextField(df);
		dateField.setVisible(false);
		dateField.setValue(new Date());

		final JLabel kindLabel = new JLabel("Kind:");
		kindLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		kindLabel.setVisible(false);

		String[] kinds = { SaleItem.SHIRT.toString(), SaleItem.SKIRT.toString(),
				SaleItem.COAT.toString(), SaleItem.TROUSERS.toString() };
		kindField = new JComboBox<String>(kinds);
		kindField.setVisible(false);

		final JLabel salesLabel = new JLabel("Sales Value:");
		salesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		salesLabel.setVisible(false);

		SpinnerNumberModel salesModel = new SpinnerNumberModel(0.0, -1.0,
				Short.MAX_VALUE, 0.1);
		salesField = new JSpinner(salesModel);
		salesField.setVisible(false);
		salesField.setValue(-1);

		final JLabel itemsLabel = new JLabel("Items Sold:");
		itemsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		itemsLabel.setVisible(false);

		SpinnerNumberModel itemsModel = new SpinnerNumberModel(0, -1,
				Short.MAX_VALUE, 1);
		itemsField = new JSpinner(itemsModel);
		itemsField.setVisible(false);
		itemsField.setValue(-1);

		final JLabel companyLabel = new JLabel("Company Name:");
		companyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		companyLabel.setVisible(false);

		companyTextField = new JTextField();
		companyTextField.setVisible(false);
		companyTextField.setColumns(10);

		final JLabel countryLabel = new JLabel("Country:");
		countryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		countryLabel.setVisible(false);

		countryTextField = new JTextField();
		countryTextField.setVisible(false);
		countryTextField.setColumns(10);

		final JLabel cityLabel = new JLabel("City:");
		cityLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		cityLabel.setVisible(false);

		cityTextField = new JTextField();
		cityTextField.setVisible(false);
		cityTextField.setColumns(10);

		final JLabel streetLabel = new JLabel("Street Name:");
		streetLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		streetLabel.setVisible(false);

		streetTextField = new JTextField();
		streetTextField.setVisible(false);
		streetTextField.setColumns(10);

		final JLabel numberLabel = new JLabel("Street Number:");
		numberLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		numberLabel.setVisible(false);

		SpinnerNumberModel numberModel = new SpinnerNumberModel(0, -1,
				Short.MAX_VALUE, 1);
		numberField = new JSpinner(numberModel);
		numberField.setVisible(false);
		numberField.setValue(-1);

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
		final List<? extends JComponent> newReceiptFields = Arrays.asList(
				receiptIDField, dateField, kindField, 
				salesField, itemsField, companyTextField, 
				countryTextField, cityTextField, streetTextField,
				numberField);
		
		final JToggleButton addReceiptToggleButton = new JToggleButton(
				"Add New Receipt");
		addReceiptToggleButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
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
					.addComponent(receiptIDField)
					.addComponent(dateField)
					.addComponent(kindField)
					.addComponent(salesField)
					.addComponent(itemsField)
					.addComponent(companyTextField)
					.addComponent(countryTextField)
					.addComponent(cityTextField)
					.addComponent(streetTextField)
					.addComponent(numberField)
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
					.addComponent(receiptIDField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(totalItemsCheckBox)
					.addComponent(dateLabel)
					.addComponent(dateField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(categoryCheckBox)
					.addComponent(kindLabel)
					.addComponent(kindField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(shirtCheckBox)
					.addComponent(salesLabel)
					.addComponent(salesField))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
					.addComponent(trousersCheckBox)
					.addComponent(itemsLabel)
					.addComponent(itemsField))
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
					.addComponent(numberField))
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
		if (areAllReceiptFieldsFilledIn()) {
			addReceipt();
			resetReceiptFields();
		} else {
			JOptionPane.showMessageDialog(
					null, "Please fill in all receipt fields");			
		}
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
	
	private boolean areAllReceiptFieldsFilledIn() {
		return (int) receiptIDField.getValue() >= 0
				&& !dateField.getText().isEmpty()
				&& (double) salesField.getValue() > 0.0
				&& (int) itemsField.getValue() > 0
				&& !companyTextField.getText().isEmpty()
				&& !countryTextField.getText().isEmpty()
				&& !cityTextField.getText().isEmpty()
				&& !streetTextField.getText().isEmpty()
				&& (int) numberField.getValue() >= 0;
	}
	
	private void addReceipt() {
		Receipt receipt = getReceiptFromUserInput();
		
		if (receipt == null) 
			return;
		
		selectedRepresentative.addRepresentativeReceipt(receipt);
		selectedRepresentative.appendReceiptToReceiptsFile(receipt);
		
		numOfReceipts++;
		addedReceiptsTextField.setText(Integer.toString(numOfReceipts));
		
		JOptionPane.showMessageDialog(null,"Receipt added to the list of Sale's"
				+ " Representative " + selectedRepresentative.getName());
	}

	private void resetReceiptFields() {
		receiptIDField.setValue(-1);
		dateField.setValue(new Date());
		kindField.setSelectedIndex(0);
		salesField.setValue(-1);
		itemsField.setValue(-1);
		companyTextField.setText("");
		countryTextField.setText("");
		cityTextField.setText("");
		streetTextField.setText("");
		numberField.setValue(-1);
	}
	
	private Receipt getReceiptFromUserInput() {
		try {
			int id = (int) receiptIDField.getValue();
			String date = dateField.getText();
			Double sales = (double) salesField.getValue();
			int items = (int) itemsField.getValue();
			String kind = (String) kindField.getSelectedItem();
			String companyName = companyTextField.getText();
			String country = countryTextField.getText();
			String city = cityTextField.getText();
			String street = streetTextField.getText();
			int streetNumber = (int) numberField.getValue();
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