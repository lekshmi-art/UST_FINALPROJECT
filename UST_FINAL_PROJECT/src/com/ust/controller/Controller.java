package com.ust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.beans.Login;
import com.ust.beans.Vendor;
import com.ust.beans.VendorContact;
import com.ust.dao.AdminDao;





@RestController
public class Controller {
	@Autowired
	AdminDao dao;
	
	//verify login
	@RequestMapping(value = "/api/login/{username}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public Login selectRole(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		return dao.login(username, password);
	}
	//view vendor list
	@ResponseBody
	@RequestMapping(value = "api/vendor", method = RequestMethod.GET)
	public List getAllVendors() {
		List vendorList=dao.getVendors();
		return vendorList;
	}
	//view contact list
	@ResponseBody
	@RequestMapping(value = "api/contact", method = RequestMethod.GET)
	public List getAllContacts() {
		List contactList=dao.getContactDetailsByVId();
		return contactList;
	}
	// view vendor list by name
		@RequestMapping(value = "/api/vendorname/{vendorName}", method = RequestMethod.GET)
		@ResponseBody
		public Vendor getVendorByName(Model m,@PathVariable("vendorName") String vendorName){
			return dao.getVendorByName(vendorName);
			
	}
	// view vendor list by location
		@RequestMapping(value = "/api/vendorlocation/{location}", method = RequestMethod.GET)
		@ResponseBody
	public List getVendorByLocation(Model m, @PathVariable("location") String location) {
		List list;
	
			list =dao.getVendorByLocation(location);
		return list;
	}
	// to add new vendor
//		@ResponseBody
//		@RequestMapping(value = "/api/savevendor", method =RequestMethod.POST)
//		public void saveVendor(@RequestBody Vendor v1){
//				dao.saveVendor(v1);
//		}
	//to update existing vendor
//		@RequestMapping(value = "/api/updatevendor", method = RequestMethod.PUT)
//		public void updateVendor(@RequestBody Vendor vc)  {
//			
//				dao.updateVendor(vc);
//			
//		}
	//to disable a vendor
		
		@RequestMapping(value = "/api/disablevendor/{vendorId}", method = RequestMethod.PUT)
		@ResponseBody
		public void disableVendor(@PathVariable("vendorId") int vendorId) {
			dao.disableVendor(vendorId);
		}
	// Add Vendor
		@ResponseBody
		@RequestMapping(value = "/api/insertvendor", method = RequestMethod.POST)
		public void insertVendor(@RequestBody VendorContact vc){
			dao.saveVendor(vc);
		}
	// update Vendor
		@ResponseBody
		@RequestMapping(value = "/api/updatevendor", method = RequestMethod.PUT)
		public void updateDoctor(@RequestBody VendorContact vc){
			int vendorId = vc.getVendorId();
			int contactId = vc.getContactId();
			dao.updateVendor(vendorId, vc);
		}
	// view contactdetails by vendor id

		@RequestMapping(value = "/api/contactDetails/{vendorId}", method = RequestMethod.GET)
		@ResponseBody
		public List getContactDetails(Model m,@PathVariable("vendorId") int vendorId) {
			List list;
		list=dao.getVendorById(vendorId);
		return list;

		}
		//view vendor details by id
		@RequestMapping(value = "/api/vendordetails/{vendorId}", method = RequestMethod.GET)
		@ResponseBody
		public VendorContact getVendorById1(Model m,@PathVariable("vendorId") int vendorId){
			return dao.getVendorById1(vendorId);
		}
					
		


			

		

	

}
