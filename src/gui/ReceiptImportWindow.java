package gui;

import data.Representative;
import input.TXTInput;
import input.XMLInput;

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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ReceiptImportWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private static ReceiptImportWindow dialog = new ReceiptImportWindow();
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> representativeList = new JList<String>();
	private ArrayList<Representative> allRepresentatives;
	private Representative selectedRepresentative = null;
	
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
		inputWindowPanel.setBorder(null);
		getContentPane().add(inputWindowPanel, BorderLayout.CENTER);
			
		JButton buttonTXTInput = new JButton("\u0395\u03B9\u03C3\u03B1\u03B3\u03C9\u03B3\u03AE \u03B1\u03C0\u03CC TXT");
		buttonTXTInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonTXTInput.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		buttonTXTInput.setFocusPainted(false);
		
		JButton buttonXMLInput = new JButton("\u0395\u03B9\u03C3\u03B1\u03B3\u03C9\u03B3\u03AE \u03B1\u03C0\u03CC XML");
		buttonXMLInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonXMLInput.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		buttonXMLInput.setFocusPainted(false);
		
		
		JLabel label = new JLabel("\u0395\u03C0\u03B9\u03BB\u03AD\u03BE\u03C4\u03B5 \u03B5\u03AF\u03B4\u03BF\u03C2 \u03B1\u03C1\u03C7\u03B5\u03AF\u03BF\u03C5 \u03B3\u03B9\u03B1 \u03C6\u03CC\u03C1\u03C4\u03C9\u03C3\u03B7 \u03B1\u03C0\u03BF\u03B4\u03B5\u03AF\u03BE\u03B5\u03C9\u03BD:");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		representativeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectAgent(e);
			}
		});
		
		representativeList.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		representativeList.setBackground(UIManager.getColor("Button.light"));
		representativeList.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		JLabel label_1 = new JLabel("\u039B\u03B9\u03C3\u03C4\u03B1 \u0391\u03BD\u03C4\u03B9\u03C0\u03C1\u03BF\u03C3\u03CE\u03C0\u03C9\u03BD");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				okButtonPressed(evt);						
			}
		});
		button.setToolTipText("");
		button.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		button.setBackground(UIManager.getColor("Button.shadow"));
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonPressed(e);
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		button_1.setBackground(UIManager.getColor("Button.shadow"));
		
		GroupLayout gl_inputWindowPanel = new GroupLayout(inputWindowPanel);
		gl_inputWindowPanel.setHorizontalGroup(
			gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputWindowPanel.createSequentialGroup()
					.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputWindowPanel.createSequentialGroup()
							.addGap(258)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(label)
							.addComponent(buttonTXTInput, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
							.addComponent(buttonXMLInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(representativeList, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(57, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_inputWindowPanel.createSequentialGroup()
					.addContainerGap(453, Short.MAX_VALUE)
					.addComponent(label_1)
					.addGap(143))
		);
		gl_inputWindowPanel.setVerticalGroup(
			gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputWindowPanel.createSequentialGroup()
					.addGap(23)
					.addComponent(label)
					.addGap(11)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputWindowPanel.createSequentialGroup()
							.addComponent(buttonTXTInput, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(buttonXMLInput, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addComponent(representativeList, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
					.addGap(139)
					.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		inputWindowPanel.setLayout(gl_inputWindowPanel);
		buttonTXTInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				insertFromTXT(evt);
			}
		});
		
		buttonXMLInput.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						insertFromXML(e);
					}
				});
	}
	
	private void cancelButtonPressed(ActionEvent e) {
		System.exit(0);	
	}

	private void insertFromTXT(ActionEvent evt) {
		
		JFileChooser TXTFileChooser;
		TXTFileChooser = new JFileChooser();     
		TXTFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);		       
		TXTFileChooser.showOpenDialog(null);
		boolean representativeDuplicate = false;
		try {
			File recieptFileTXT = TXTFileChooser.getSelectedFile();
			TXTInput inputFileTXT = new TXTInput(recieptFileTXT);	
			inputFileTXT.readFile();
			Representative representative = inputFileTXT.getAgent();
			representative.setupReceiptFileAppender("TXT", recieptFileTXT);				
			allRepresentatives.add(representative);
			for(int i = 0; i< listModel.getSize(); i++){
				if(representative.getName().equals(listModel.getElementAt(i))){
					representativeDuplicate = true;
				}
			}
			
			if (representativeDuplicate == true) {
				JOptionPane.showMessageDialog(null,"� ������������ ������� ��� ��� �����");

			} else {
				listModel.addElement(representative.getName());
				representativeList.setModel(listModel);
			}
			
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(
					null, "Duplicate Sales Representative");

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error Reading TXT File");
		}
	}
	
	private void insertFromXML(ActionEvent evt2) {
		JFileChooser XMLFileChooser;
		XMLFileChooser = new JFileChooser();     
		XMLFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);		       
		XMLFileChooser.showOpenDialog(null);
		boolean representativeDuplicate = false;
		try{
			File receiptFileXML = XMLFileChooser.getSelectedFile();
			XMLInput inputFileXML = new XMLInput(receiptFileXML);	
			inputFileXML.readFile();
			Representative representative = inputFileXML.getAgent();
			representative.setupReceiptFileAppender("XML", receiptFileXML);				
			allRepresentatives.add(representative);
			for(int i = 0; i< listModel.getSize(); i++){
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
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null,"Error Reading XML File");
		}
	}
	
	private void selectAgent(MouseEvent e) {
		String representativeName;
        if(representativeList.getSelectedIndex() >= 0){
            representativeName = representativeList.getSelectedValue().toString();
            for(int i = 0; i < allRepresentatives.size(); i++){
                if(representativeName.equals(allRepresentatives.get(i).getName())){
            		selectedRepresentative = allRepresentatives.get(i);
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

}