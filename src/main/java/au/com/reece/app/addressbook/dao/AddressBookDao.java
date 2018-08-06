package au.com.reece.app.addressbook.dao;

import java.util.List;

import au.com.reece.app.addressbook.model.AddressBook;

public interface AddressBookDao {

	public void create(AddressBook addressBook);

	public void delete(AddressBook addressBook);

	public boolean isUniqueAddressBookName(String addressBookName);

	public List<AddressBook> getAllUserAddressBooks();
}
