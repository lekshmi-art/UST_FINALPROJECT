package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ust.beans.ContactPerson;
import com.ust.beans.Login;
import com.ust.beans.Vendor;
import com.ust.beans.VendorContact;


public class AdminDao {
	JdbcTemplate jdbcTemplate;

	public void setTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	// verify login
	public Login login(String username,String password){
		String sql="select userId,username,password from login where username=? and password=?";
		return (Login) jdbcTemplate.queryForObject(sql, new Object[]{username,password},new BeanPropertyRowMapper<Login>(Login.class));
	}
	//view vendor list
	public List<Vendor> getVendors(){
		return jdbcTemplate.query("select vendorId,vendorName,address,location,service,pincode from vendor_details where isActive='1'",new RowMapper<Vendor>() {
			public Vendor mapRow(ResultSet rs, int row) throws SQLException{
				Vendor v = new Vendor();
				v.setVendorId(rs.getInt(1));
				v.setVendorName(rs.getString(2));
				v.setAddress(rs.getString(3));
				v.setLocation(rs.getString(4));
				v.setService(rs.getString(5));
				v.setPincode(rs.getInt(6));
				return v;
			}
		});
	}
	//view contact list

	public List<VendorContact> getContactDetailsByVId() {
	return jdbcTemplate.query("select contactId,contactName,department,vendorId,email,phone from contact_details", new RowMapper<VendorContact>() {
		public VendorContact mapRow(ResultSet rs, int row)
				throws SQLException {
			VendorContact vc = new VendorContact();
			vc.setContactId(rs.getInt(1));
			vc.setContactName(rs.getString(2));
			vc.setDepartment(rs.getString(3));
			vc.setVendorId(rs.getInt(4));
			vc.setEmail(rs.getString(5));
			vc.setPhone(rs.getLong(6));
			return vc;
		}
	});
	}
	//view vendor by name
	public Vendor getVendorByName(String vendorName) {
		
		String sql="select vendorId,vendorName,address,location,service,pincode from vendor_details where vendorName='"+vendorName+"'";
		return jdbcTemplate.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<Vendor>(Vendor.class));
	}
	//view vendor by location
	public List<Vendor> getVendorByLocation(String location) {
		return jdbcTemplate
				.query("select vendorId,vendorName,address,location,service,pincode from vendor_details where location='"+location+"'",
						
						new RowMapper<Vendor>() {
							public Vendor mapRow(ResultSet rs, int row)
									throws SQLException {
								Vendor v = new Vendor();
								v.setVendorId(rs.getInt(1));
								v.setVendorName(rs.getString(2));
								v.setAddress(rs.getString(3));
								v.setLocation(rs.getString(4));
								v.setService(rs.getString(5));
								v.setPincode(rs.getInt(6));
								return v;
							}
						});
					}
	//to add new vendor
//	public int saveVendor(Vendor vc) {
//
//		String sql1 = "insert into vendor_details(vendorName,address,location,service,pincode,isActive) values "
//				+ "('"+ vc.getVendorName()+ "','"+ vc.getAddress()+ "','"+ vc.getLocation()+ "','"+ vc.getService()+ "',"+ vc.getPincode()+",'1')";		
//		return jdbcTemplate.update(sql1);
//	}
	//to update vendor
//	public int updateVendor(Vendor vc){
//		String sql="update vendor_details set vendorName='"+vc.getVendorName()+"',address='"+vc.getAddress()+"',location='"+vc.getLocation()+"',service='"+vc.getService()+"',pincode="+vc.getPincode()+"where vendorId="+vc.getVendorId();
//		return jdbcTemplate.update(sql);
//	}
	//to disable a vendor
	public int disableVendor(int vendorId ){
		String sql="update vendor_details set isActive=0 where vendorId="+vendorId+"";
		return jdbcTemplate.update(sql);
	}
	//to add new vendor and contact person
	public int saveVendor(VendorContact vc) {

		String sql1 = "insert into vendor_details(vendorName,address,location,service,pincode,isActive) values "
				+ "('"
				+ vc.getVendorName()
				+ "','"
				+ vc.getAddress()
				+ "','"
				+ vc.getLocation()
				+ "','"
				+ vc.getService()
				+ "','"
				+ vc.getPincode()
				+ "',"
				+ '1'
				+ ")";

		 jdbcTemplate.update(sql1);
		 
		 Integer maxId = getSequence();
		 String sql2="insert into contact_details(vendorId,contactName,department,email,phone) values ("
				 + maxId
					+ ",'"
					+ vc.getContactName()
					+ "','"
					+ vc.getDepartment()
					+ "','"
					+ vc.getEmail()
					+ "'," + vc.getPhone() + ")";
		 return jdbcTemplate.update(sql2);

				 
		 
	}
	//---------- to get vendor id
		private Integer getSequence() {
			Integer seq;
			String sql = "select MAX(vendorId)from vendor_details";
			seq = jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
			return seq;
		}
		//to update
		public int updateVendor(int vendorId, VendorContact vc) {

			String sql = "update vendor_details set vendorName='" + vc.getVendorName()
					+ "' ,address='" + vc.getAddress() + "' ,location='"
					+ vc.getLocation() + "',service='" + vc.getService() + "',pincode=" + vc.getPincode() + " "
					+ "where vendorId =" + vendorId;
			jdbcTemplate.update(sql);

			// Integer maxId = getSequence();

			String sql1 = "update contact_details set vendorId=" + vendorId + ",contactName='"
					+ vc.getContactName() + "',department='"
					+ vc.getDepartment() + "',email='"
					+ vc.getEmail() + "',phone='" + vc.getPhone() + "'where contactId = " + vc.getContactId();

			return jdbcTemplate.update(sql1);
		}
		//------------get vendor details by id
				public List<VendorContact> getVendorById(int vendorId) {
					return jdbcTemplate
							.query("select vc.vendorId,vc.vendorName,vc.address,vc.location,vc.service,vc.pincode,cd.contactName,cd.department,cd.email,cd.phone,cd.contactId from vendor_details vc join contact_details cd on vc.vendorId=cd.vendorId where vc.isActive=1 and vc.vendorId='"+ vendorId + "'",
									new RowMapper<VendorContact>() {
										public VendorContact mapRow(ResultSet rs, int row)
												throws SQLException {
											VendorContact vc = new VendorContact();
											vc.setVendorId(rs.getInt(1));
											vc.setVendorName(rs.getString(2));
											vc.setAddress(rs.getString(3));
											vc.setLocation(rs.getString(4));
											vc.setService(rs.getString(5));
											vc.setPincode(rs.getInt(6));
											vc.setContactName(rs.getString(7));
											vc.setDepartment(rs.getString(8));
											vc.setEmail(rs.getString(9));
											vc.setPhone(rs.getLong(10));
											vc.setContactId(rs.getInt(11));
											return vc;
										}
									});
				}
				
		//vendor details
		public VendorContact getVendorById1(int vendorId){
			String sql ="select vc.vendorId,vc.vendorName,vc.address,vc.location,vc.service,vc.pincode,cd.contactName,cd.department,cd.email,cd.phone,cd.contactId from vendor_details vc join contact_details cd on vc.vendorId=cd.vendorId where vc.isActive=1 and vc.vendorId='"+ vendorId + "'";
			return jdbcTemplate.queryForObject(sql, new Object[] {},
					new BeanPropertyRowMapper<VendorContact>(VendorContact.class));
		}
				
			
		
		 


}
