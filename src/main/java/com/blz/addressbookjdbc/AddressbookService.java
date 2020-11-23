package com.blz.addressbookjdbc;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AddressbookService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	private List<AddressbookData> addressbookList;
	private AddressbookDBService addressbookDBService;

	public AddressbookService() {
		addressbookDBService = AddressbookDBService.getInstance();
	}

	public AddressbookService(List<AddressbookData> addressbookList) {
		this();
		this.addressbookList = addressbookList;
	}

	public List<AddressbookData> readAddressbookDataFromDB(IOService ioService) {
		if (ioService == IOService.DB_IO)
			this.addressbookList = addressbookDBService.readData();
		return this.addressbookList;
	}

	public void updateContactNumber(String firstName, String phone) {
		int result;
		try {
			result = addressbookDBService.updateAddressbookData(firstName, phone);
			if (result == 0)
				return;
			AddressbookData addressbookData = this.getAddressbookData(firstName);
			if (addressbookData != null)
				addressbookData.phone = phone;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private AddressbookData getAddressbookData(String firstName) {
		AddressbookData addressbookData;
		addressbookData = this.addressbookList.stream().filter(dataItem -> dataItem.firstName.equals(firstName))
				.findFirst().orElse(null);
		return addressbookData;
	}

	public boolean checkAddressbookSyncWithDB(String firstName) {
		List<AddressbookData> addressbookDataList = addressbookDBService.getAddressbookDetails(firstName);
		return addressbookDataList.get(0).equals(getAddressbookData(firstName));
	}

	public List<AddressbookData> readAddressbookForDateRange(IOService ioService, LocalDate dateAdded,
			LocalDate dateNow) {
		if (ioService.equals(IOService.DB_IO))
			return addressbookDBService.getAddressbookForDateRange(dateAdded, dateNow);
		return null;
	}
}
