package au.com.reece.app.addressbook.dao;

import java.util.List;

import au.com.reece.app.addressbook.model.AddressBook;
import au.com.reece.app.addressbook.model.User;

public class AddressBookDaoImpl implements AddressBookDao {

	@Override
	public void create(AddressBook addressBook) {
		User.getInstance().getAddressBookList().add(addressBook);

	}

	@Override
	public void delete(AddressBook addressBook) {

	}

	@Override
	public boolean isUniqueAddressBookName(String addressBookName) {
		return !User.getInstance().getAddressBookList().stream()
				.anyMatch(ab -> ab.getAddressBookName().equalsIgnoreCase(addressBookName));

	}

	@Override
	public List<AddressBook> getAllUserAddressBooks() {
		return User.getInstance().getAddressBookList();
	}

}
