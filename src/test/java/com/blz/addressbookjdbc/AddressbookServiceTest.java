package com.blz.addressbookjdbc;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
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
		LocalDate dateAdded = LocalDate.of(2018, 01, 01);
		LocalDate dateNow = LocalDate.now();
		addressbookData = addressbookService.readAddressbookForDateRange(AddressbookService.IOService.DB_IO, dateAdded,
				dateNow);
		assertEquals(3, addressbookData.size());
		System.out.println("Person count match for given date range.");
	}

	@Test
	public void givenCity_WhenContactsRetrieved_ShouldMatchPersonCount() {
		String city = "Mumbai";
		addressbookData = addressbookService.readAddressbookForCity(AddressbookService.IOService.DB_IO, city);
		assertEquals(2, addressbookData.size());
		System.out.println("Person count match for given city.");
	}

	@Test
	public void givenState_WhenContactsRetrieved_ShouldMatchPersonCount() {
		String state = "Maharashtra";
		addressbookData = addressbookService.readAddressbookForState(AddressbookService.IOService.DB_IO, state);
		assertEquals(3, addressbookData.size());
		System.out.println("Person count match for given state.");
	}

	@Test
	public void givenNewPerson_WhenAdded_ShouldSyncWithDB() {
		addressbookService.addPersonToAddressbook("Gunjan", "T", "K", "Kerala", "Kerala", "498792", "9876543213",
				"gt@gm.com", LocalDate.now());
		boolean result = addressbookService.checkAddressbookSyncWithDB("Gunjan");
		assertTrue(result);
		System.out.println("Person added in addressbook .");
	}

	@Test
	public void givenFourContacts_WhenAdded_ShouldMatchContactEntries() {
		AddressbookData[] arrayOfPersons = {
				new AddressbookData(0, "Bill", "T", "CST", "Mumbai", "Maharashtra", "428792", "9876543213", "bt@gm.com",LocalDate.now()),
				new AddressbookData(0, "Mark", "K", "Dadar", "Mumbai", "Maharashtra", "498892", "9876544213","mt@gm.com", LocalDate.now()),
				new AddressbookData(0, "Terrisa", "T", "Karve", "Pune", "Maharashtra", "491792", "9877543213","tt@gm.com", LocalDate.now()),
				new AddressbookData(0, "Charlie", "K", "S", "New Delhi", "Delhi", "493792", "9879543213", "ck@gm.com",LocalDate.now()) };
		AddressbookService addressbookService = new AddressbookService();
		addressbookService.readAddressbookDataFromDB(AddressbookService.IOService.DB_IO);
		Instant threadStart = Instant.now();
		addressbookService.addContactToAddressbookUsingThreads(Arrays.asList(arrayOfPersons));
		Instant threadEnd = Instant.now();
		System.out.println("Duration with thread :" + Duration.between(threadStart, threadEnd));
		assertEquals(5, addressbookService.countEntry(AddressbookService.IOService.DB_IO));
	}
}
