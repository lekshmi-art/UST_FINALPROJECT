package com.ust.beans;

public class VendorContact {
	private int vendorId;
	private String vendorName;
	private String address;
	private String location;
	private String service;
	private int pincode;
	private String isActive;
	private int contactId;
	private String contactName;
	private String department;
	private String email;
	private long phone;
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public VendorContact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VendorContact(int vendorId, String vendorName, String address,
			String location, String service, int pincode, String isActive,
			int contactId, String contactName, String department, String email,
			long phone) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.address = address;
		this.location = location;
		this.service = service;
		this.pincode = pincode;
		this.isActive = isActive;
		this.contactId = contactId;
		this.contactName = contactName;
		this.department = department;
		this.email = email;
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "VendorContact [vendorId=" + vendorId + ", vendorName="
				+ vendorName + ", address=" + address + ", location="
				+ location + ", service=" + service + ", pincode=" + pincode
				+ ", isActive=" + isActive + ", contactId=" + contactId
				+ ", contactName=" + contactName + ", department=" + department
				+ ", email=" + email + ", phone=" + phone + "]";
	}
	
	
}