package au.com.reece.app.addressbook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressBook {

	private final String addressBookName;
	private List<Contact> contactList;

	public AddressBook(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	public String getAddressBookName() {
		return addressBookName;
	}

	public List<Contact> getContactList() {
		if (contactList != null) {
			return contactList;
		}
		contactList = new ArrayList<>();
		return contactList;
	}

	@Override
	public String toString() {
		return addressBookName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressBookName, contactList);
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (!(other instanceof AddressBook)) {
			return false;
		}
		final AddressBook o = (AddressBook) other;
		if (this.getAddressBookName().equals(o.getAddressBookName())) {
			if (this.getContactList().equals(o.getContactList())) {
				return true;
			}
		}
		return false;
	}

}
