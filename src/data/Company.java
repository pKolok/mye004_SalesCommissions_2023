package data;

public class Company {

		private String companyName;
		private Address companyAddress;
		
		public Company(String name, Address address)  {
			this.companyName = name;
			this.companyAddress = address;
		}
		
		public String getName() {
			return companyName;
		}
		
		public Address getCompanyAddress(){
			return companyAddress;
		}
		
}