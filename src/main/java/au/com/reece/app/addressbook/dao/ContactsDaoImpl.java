package au.com.reece.app.addressbook.dao;

import au.com.reece.app.addressbook.model.AddressBook;
import au.com.reece.app.addressbook.model.Contact;
import au.com.reece.app.addressbook.model.User;

public class ContactsDaoImpl implements ContactsDao {

	@Override
	public boolean delete(Contact contact) {
		for (final AddressBook addressBook : User.getInstance().getAddressBookList()) {
			if (addressBook.getContactList().remove(contact)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void createContact(AddressBook addressBook, Contact contact) {
		addressBook.getContactList().add(contact);
	}

}
