package com.blz.addressbookjdbc;

import java.time.LocalDate;

public class AddressbookData {
	public int id;
	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String state;
	public String zip;
	public String phone;
	public String email;
	public LocalDate dateAdded;

	public AddressbookData(int id, String firstName, String lastName, String address, String city, String state,
			String zip, String phone, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
}
