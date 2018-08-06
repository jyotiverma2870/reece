package au.com.reece.app.addressbook.controller;

import au.com.reece.app.addressbook.dao.ContactsDao;
import au.com.reece.app.addressbook.dao.ContactsDaoImpl;
import au.com.reece.app.addressbook.model.AddressBook;
import au.com.reece.app.addressbook.model.Contact;

public class ContactsController {

	ContactsDao contactsDao = new ContactsDaoImpl();

	public void createContact(AddressBook addressBook, Contact contact) {
		contactsDao.createContact(addressBook, contact);
	}

	public void delete(Contact contact) {
		contactsDao.delete(contact);

	}

}
