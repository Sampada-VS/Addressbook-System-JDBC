package com.blz.addressbookjdbc;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressbookServiceTest {

	static AddressbookService addressbookService;
	List<AddressbookData> addressbookData;

	@BeforeClass
	public static void createObj() {
		addressbookService = new AddressbookService();
		addressbookService.readAddressbookDataFromDB(AddressbookService.IOService.DB_IO);
	}

	@AfterClass
	public static void nullObj() {
		addressbookService = null;
	}

	@Test
	public void givenAddressbookInDB_WhenRetrieved_ShouldMatchTotalPersonCount() {
		addressbookData = addressbookService.readAddressbookDataFromDB(AddressbookService.IOService.DB_IO);
		assertEquals(4, addressbookData.size());
		System.out.println("Total person in addressbook :" + addressbookData.size());
	}

	@Test
	public void givenPhoneNumber_WhenUpdated_ShouldSyncWithDB() {
		addressbookService.updateContactNumber("Terrisa", "9876543285");
		boolean result = addressbookService.checkAddressbookSyncWithDB("Terrisa");
		assertTrue(result);
		System.out.println("Contact number got updated for Terrisa.");
	}
	
	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchPersonCount() {
		LocalDate dateAdded = LocalDate.of(2019, 01, 01);
		LocalDate dateNow = LocalDate.now();
		addressbookData = addressbookService.readAddressbookForDateRange(AddressbookService.IOService.DB_IO,dateAdded, dateNow);
		assertEquals(3, addressbookData.size());
		System.out.println("Person count match for given date range.");
	}	
	
	@Test
	public void givenCity_WhenContactsRetrieved_ShouldMatchPersonCount() {
		String city="Mumbai";
		addressbookData = addressbookService.readAddressbookForCity(AddressbookService.IOService.DB_IO,city);
		assertEquals(2, addressbookData.size());
		System.out.println("Person count match for given city.");
	}	
	
	@Test
	public void givenState_WhenContactsRetrieved_ShouldMatchPersonCount() {
		String state="Maharashtra";
		addressbookData = addressbookService.readAddressbookForState(AddressbookService.IOService.DB_IO,state);
		assertEquals(3, addressbookData.size());
		System.out.println("Person count match for given state.");
	}	
}
