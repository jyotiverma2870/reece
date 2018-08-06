package au.com.reece.app.addressbook.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import au.com.reece.app.addressbook.common.Options;
import au.com.reece.app.addressbook.common.Utils;
import au.com.reece.app.addressbook.controller.AddressBookController;
import au.com.reece.app.addressbook.controller.ContactsController;
import au.com.reece.app.addressbook.model.AddressBook;
import au.com.reece.app.addressbook.model.Contact;
import au.com.reece.app.addressbook.model.PhoneNumber;
import au.com.reece.app.addressbook.model.PhoneNumber.PhoneNumberType;

/**
 * Hello world!
 *
 */
public class App

{
	AddressBookController addressBookController = new AddressBookController();
	ContactsController contactsController = new ContactsController();

	private Optional<AddressBook> currentlySelectedAddressBook = Optional.empty();

	private Scanner in;

	public App(Scanner scanner) {
		this.in = scanner;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Reece Address Book App !");
		final App app = new App(new Scanner(System.in));
		app.showMainMenu();
	}

	public void showMainMenu() {

		do {
			System.out.println(" ======= Main Menu ======");
			Arrays.asList(Options.MainMenuOptions.values())
					.forEach(option -> System.out.println(option.getId() + " : " + option.getOption()));
			System.out.println(" ===== End Main Menu ====");
			System.out.println("Enter your choice : ");

			final String choice = in.nextLine();
			if (Options.MainMenuOptions.getById(choice).isPresent()) {
				final Options.MainMenuOptions selectedOption = Options.MainMenuOptions.getById(choice).get();
				switch (selectedOption) {
				case CREATE:
					createNewAddressBook();
					break;
				case LIST:
					listAllAddressBooks();
					break;
				case SELECT:
					if (selectAddressBook()) {
						showAddressBookMenu();
					}
					break;
				case PRINT_ALL:
					printAllContactsFromAnAddressBook();
					break;
				case PRINT_ALL_UNIQUE:
					printAllContacts();
					break;
				case EXIT:
					System.out.println("Thanks for using the Reece address book app. Exiting now !");
					System.exit(0);
					break;
				}
			} else {
				System.out.println("Enter number from " + Options.MainMenuOptions.CREATE.ordinal() + " to "
						+ Options.MainMenuOptions.EXIT.ordinal());
			}
		} while (true);
	}

	public void printAllContacts() {
		System.out.println("Printing all unique contacts:");
		final Set<Contact> contactsSet = new HashSet<Contact>();
		if (addressBookController.getAllUserAddressBooks().size() > 0) {
			/*
			 * Merge all contacts in a Set for uniqueness
			 */
			addressBookController.getAllUserAddressBooks().forEach(ab -> contactsSet.addAll(ab.getContactList()));
			contactsSet.forEach(c -> System.out.println(c));
		} else {
			System.out.println("No address books exists for the user. Please create an address book first");
		}

	}

	public boolean selectAddressBook() {
		if (addressBookController.getAllUserAddressBooks() == null
				|| addressBookController.getAllUserAddressBooks().size() == 0) {
			System.out.println("No address books exists for the user. Please create an address book first");
		} else {
			System.out.println("Select address book to work with: (Enter the number against the address book name)");
			final AtomicInteger index = new AtomicInteger(0);
			addressBookController.getAllUserAddressBooks()
					.forEach(ab -> System.out.println(index.getAndIncrement() + " : " + ab.getAddressBookName()));
			final String choice = in.nextLine();
			if (Utils.isNumeric(choice)) {
				if (Integer.valueOf(choice) < addressBookController.getAllUserAddressBooks().size()) {
					currentlySelectedAddressBook = Optional
							.ofNullable(addressBookController.getAllUserAddressBooks().get(Integer.valueOf(choice)));
					return true;
				} else {
					System.out.println("Invalid selection. Please enter a valid number in the range from 0 to "
							+ (addressBookController.getAllUserAddressBooks().size() - 1));

				}
			} else {
				System.out.println("Invalid selection. Please enter a valid number.");
			}
		}
		return false;
	}

	public void printAllContactsFromAnAddressBook() {
		if (addressBookController.getAllUserAddressBooks() == null
				|| addressBookController.getAllUserAddressBooks().size() == 0) {
			System.out.println("No address books exists for the user. Please create an address book first");

		} else {
			System.out.println("Select address book to print : (Enter the number against the address book name)");
			final AtomicInteger index = new AtomicInteger(0);
			addressBookController.getAllUserAddressBooks()
					.forEach(ab -> System.out.println(index.getAndIncrement() + " : " + ab.getAddressBookName()));
			final String choice = in.nextLine();
			if (Utils.isNumeric(choice)) {
				if (addressBookController.getAllUserAddressBooks().size() > Integer.valueOf(choice)) {
					final AddressBook addressBook = addressBookController.getAllUserAddressBooks()
							.get(Integer.valueOf(choice));
					if (addressBook.getContactList().size() > 0) {
						addressBook.getContactList().forEach(c -> System.out.println(c));
					} else {
						System.out.println("Address Book empty !");
					}
				} else {
					System.out.println("Invalid selection. Please enter a valid number in range from 0 to "
							+ (addressBookController.getAllUserAddressBooks().size() - 1));
				}
			} else {
				System.out.println("Invalid selection. Please enter a valid number.");
			}
		}

	}

	public void listAllAddressBooks() {
		if (addressBookController.getAllUserAddressBooks().size() == 0) {
			System.out.println("No address books created for user. Please create an address book first.");
		}
		final List<AddressBook> addressBookList = addressBookController.getAllUserAddressBooks();
		System.out.println("User address books:");
		addressBookList.forEach(ab -> System.out.println(ab.getAddressBookName()));

	}

	public void createNewAddressBook() {
		System.out.println("Enter address book name: (e.g. sales dept, human resources dept, customers etc)");
		final String addressBookName = in.nextLine();

		if (addressBookController.isUniqueAddressBookName(addressBookName)) {
			addressBookController.createNewAddressBook(new AddressBook(addressBookName));
			System.out.println("Address book created : " + addressBookName);
		} else {
			System.out.println(
					"Address book name [" + addressBookName + "] already in use. Please choose a different name.");
		}

	}

	public void showAddressBookMenu() {

		do {
			System.out.println(" ======= Address Book Menu ======");
			System.out.println(" ======= Currently Selected Address Book : "
					+ currentlySelectedAddressBook.orElse(new AddressBook("None")));
			Arrays.asList(Options.AddressBookOptions.values())
					.forEach(option -> System.out.println(option.getId() + " : " + option.getOption()));

			System.out.println(" ===== End Address Book Menu ====");
			System.out.println("Enter your choice : ");

			final String choice = in.nextLine();
			if (Options.AddressBookOptions.getById(choice).isPresent()) {
				final Options.AddressBookOptions selectedOption = Options.AddressBookOptions.getById(choice).get();
				switch (selectedOption) {
				case CREATE:
					createContact();
					break;
				case REMOVE:
					deleteContact();
					break;
				case PRINT_ALL:
					printCurrentlySelectedAdddressBook();
					break;
				case MAIN_MENU:
					return;
				}
			}
		} while (true);
	}

	private void deleteContact() {
		if (currentlySelectedAddressBook.get().getContactList().size() > 0) {
			System.out.println("Enter the contact number to be removed :");
			final AtomicInteger index = new AtomicInteger(0);
			currentlySelectedAddressBook.get().getContactList().forEach(c -> System.out
					.println(index.getAndIncrement() + " : " + c.getFirstName() + " " + c.getLastName()));
			final String choice = in.nextLine();
			if (Utils.isNumeric(choice)) {
				System.out.println("Removing contact "
						+ currentlySelectedAddressBook.get().getContactList().get(Integer.valueOf(choice)));
				contactsController
						.delete(currentlySelectedAddressBook.get().getContactList().get(Integer.valueOf(choice)));

			}
		} else {
			System.out.println("Address book is empty");
		}
	}

	public void createContact() {
		System.out.println("Enter Contact's first Name:");
		final String firstName = in.nextLine();
		System.out.println("Enter Contact's last Name:");
		final String lastName = in.nextLine();
		final Contact contact = new Contact(firstName, lastName);

		do {
			inputPhoneNumberForContact(contact);
			System.out.println("Add another Phone Number for this contact? : Yes/No");
		} while (in.nextLine().equalsIgnoreCase("yes"));
		contactsController.createContact(currentlySelectedAddressBook.get(), contact);
	}

	private void inputPhoneNumberForContact(final Contact contact) {
		System.out.println("Enter Phone number:");
		final String phoneNumber = in.nextLine();
		inputPhoneNumberType(contact, phoneNumber);
	}

	private void inputPhoneNumberType(final Contact contact, final String phoneNumber) {
		System.out.println("Select phone number type:");
		Arrays.asList(PhoneNumberType.values())
				.forEach(option -> System.out.println(option.ordinal() + " : " + option.name()));
		final String phoneNumberTypeStr = in.nextLine();
		if (PhoneNumberType.getById(phoneNumberTypeStr).isPresent()) {
			final PhoneNumberType phoneNumberType = PhoneNumberType.getById(phoneNumberTypeStr).get();
			contact.getPhoneNumbers().add(new PhoneNumber(phoneNumberType, phoneNumber));
		} else {
			System.out.println("Invalid Phone number type. Please select a valid phone number type:");
			inputPhoneNumberType(contact, phoneNumber);
		}
	}

	private void printCurrentlySelectedAdddressBook() {
		if (currentlySelectedAddressBook.get().getContactList().size() > 0) {
			currentlySelectedAddressBook.get().getContactList().forEach(c -> System.out.println(c));
		} else {
			System.out.println("Address book is empty");
		}
	}

	public Optional<AddressBook> getCurrentlySelectedAddressBook() {
		return currentlySelectedAddressBook;
	}

	public void setIn(Scanner in) {
		this.in = in;
	}

}
