package output;

import java.io.FileWriter;
import java.io.IOException;

public class ReceiptTxtFileAppender extends ReceiptFileAppender{

	public void appendFile(){
		System.out.println("Mpike sto TXT");
		System.out.println(fileToAppend.getAbsolutePath());

		try {
			
			FileWriter fileWriter = new FileWriter(fileToAppend, true);		
			fileWriter.write("\n");
			fileWriter.write("Receipt ID: ");
			fileWriter.write(Integer.toString(receipt.getReceiptID()));
			fileWriter.write("\n");

			fileWriter.write("Date: ");
			fileWriter.write(receipt.getDate());
			fileWriter.write("\n");

			fileWriter.write("Kind: ");
			fileWriter.write(receipt.getKind().name());
			fileWriter.write("\n");

			fileWriter.write("Sales: ");
			fileWriter.write(receipt.getSales() + "");
			fileWriter.write("\n");

			fileWriter.write("Items: ");
			fileWriter.write(receipt.getItems() + "");
			fileWriter.write("\n");

			fileWriter.write("Company: ");
			fileWriter.write(receipt.getCompany().getName());
			fileWriter.write("\n");

			fileWriter.write("Country: ");
			fileWriter.write(receipt.getCompany().getCompanyAddress()
					.getCountry());
			fileWriter.write("\n");
			
			fileWriter.write("City: ");
			fileWriter.write(receipt.getCompany().getCompanyAddress()
					.getCity());
			fileWriter.write("\n");

			fileWriter.write("Street: ");
			fileWriter.write(receipt.getCompany().getCompanyAddress()
					.getStreet());
			fileWriter.write("\n");

			fileWriter.write("Number: ");
			fileWriter.write(Integer.toString(receipt.getCompany()
					.getCompanyAddress().getStreetNumber()));
			fileWriter.write("\n");

			fileWriter.close();
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}