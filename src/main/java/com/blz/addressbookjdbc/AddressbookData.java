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

	public AddressbookData(int id, String firstName, String lastName, String address, String city, String state,
			String zip, String phone, String email, LocalDate dateAdded) {
		this(id, firstName, lastName, address, city, state, zip, phone, email);
		this.dateAdded = dateAdded;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AddressbookData that = (AddressbookData) o;
		return id == that.id && firstName.equals(that.firstName) && lastName.equals(that.lastName)
				&& address.equals(that.address) && city.equals(that.city) && state.equals(that.state)
				&& zip.equals(that.zip) && phone.equals(that.phone) && email.equals(that.email);
	}
}
