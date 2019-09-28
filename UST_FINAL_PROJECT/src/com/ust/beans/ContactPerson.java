package com.ust.beans;

public class ContactPerson {
	private int contactId;
	private String contactName;
	private int vendorId;
	private String department;
	private String email;
	private long phone;
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
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
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
	@Override
	public String toString() {
		return "ContactPerson [contactId=" + contactId + ", contactName="
				+ contactName + ", vendorId=" + vendorId + ", department="
				+ department + ", email=" + email + ", phone=" + phone + "]";
	}
	public ContactPerson() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContactPerson(int contactId, String contactName, int vendorId,
			String department, String email, long phone) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.vendorId = vendorId;
		this.department = department;
		this.email = email;
		this.phone = phone;
	}
	
}