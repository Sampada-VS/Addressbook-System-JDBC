package com.blz.addressbookjdbc;

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

}
