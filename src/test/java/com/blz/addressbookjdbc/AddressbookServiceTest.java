package com.blz.addressbookjdbc;

import static org.junit.Assert.*;

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
		addressbookService.updateContactNumber("Terrisa", "9876543284");
		boolean result = addressbookService.checkAddressbookSyncWithDB("Terrisa");
		assertTrue(result);
		System.out.println("Contact number got updated for Terrisa.");
	}
}
