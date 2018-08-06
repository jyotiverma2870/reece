package au.com.reece.app.addressbook.controller;

import java.util.List;

import au.com.reece.app.addressbook.dao.AddressBookDao;
import au.com.reece.app.addressbook.dao.AddressBookDaoImpl;
import au.com.reece.app.addressbook.model.AddressBook;
import au.com.reece.app.addressbook.model.User;

public class AddressBookController {

	private User user;
	AddressBookDao addressBookDao = new AddressBookDaoImpl();

	public User getUser() {
		return user;
	}

	public AddressBookController() {
	}

	public AddressBookController(User user) {
		this.user = user;
	}

	public void createNewAddressBook(AddressBook addressBook) {
		addressBookDao.create(addressBook);
	}

	public boolean isUniqueAddressBookName(String addressBookName) {
		return addressBookDao.isUniqueAddressBookName(addressBookName);
	}

	public List<AddressBook> getAllUserAddressBooks() {
		return addressBookDao.getAllUserAddressBooks();
	}

}
