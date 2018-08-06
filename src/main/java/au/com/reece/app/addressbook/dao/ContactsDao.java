package au.com.reece.app.addressbook.dao;

import au.com.reece.app.addressbook.model.AddressBook;
import au.com.reece.app.addressbook.model.Contact;

public interface ContactsDao {

	public boolean delete(Contact contact);

	public void createContact(AddressBook addressBook, Contact contact);
}
