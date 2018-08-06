package au.com.reece.app.addressbook.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	private static User user = new User();

	private User() {

	}

	private List<AddressBook> addressBookList;

	public List<AddressBook> getAddressBookList() {
		if (addressBookList != null) {
			return addressBookList;
		}
		addressBookList = new ArrayList<>();
		return addressBookList;
	}

	public static User getInstance() {
		return user;
	}
}
