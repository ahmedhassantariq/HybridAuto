package Entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer{

	private SimpleStringProperty customerID;
	private SimpleStringProperty firstName;
	private SimpleStringProperty middleName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty phone;
	private SimpleStringProperty areaCode;
	private SimpleStringProperty address;

	public Customer(String customerID, String firstName, String middleName, String lastName, String phone, String areaCode, String address) {
		this.customerID = new SimpleStringProperty (customerID);
		this.firstName = new SimpleStringProperty(firstName);
		this.middleName = new SimpleStringProperty(middleName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone = new SimpleStringProperty(phone);
		this.areaCode = new SimpleStringProperty(areaCode);
		this.address = new SimpleStringProperty(address);
	}

	public String getCustomerID() {
		return customerID.get();
	}

	public void setCustomerID(String customerID) {
		this.customerID.set(customerID);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getMiddleName() {
		return middleName.get();
	}

	public void setMiddleName(String middleName) {
		this.middleName.set(middleName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone.set(phone);
	}

	public String getAreaCode() {
		return areaCode.get();
	}

	public void setAreaCode(String areaCode) {
		this.areaCode.set(areaCode);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public SimpleStringProperty getAddressProperty() {
		return address;
	}

	public SimpleStringProperty getPhoneProp(){
		return phone;
	}


}