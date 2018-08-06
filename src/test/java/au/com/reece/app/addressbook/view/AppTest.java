package au.com.reece.app.addressbook.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import au.com.reece.app.addressbook.model.AddressBook;
import au.com.reece.app.addressbook.model.User;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest {

	@Before
	public void before() {
		User.getInstance().getAddressBookList().clear();
	}

	@Test
	public void createNewAddressBookTest() {

		final String input = "Sales";
		final InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		final Scanner scanner = new Scanner(System.in);
		final App app = new App(scanner);
		app.createNewAddressBook();
		Assert.assertTrue(User.getInstance().getAddressBookList().contains(new AddressBook("Sales")));
	}

	@Test
	public void createNewAddressBookTest_MultipleAddressBooks_Success() {

		final String input = "Sales\nIT";
		final InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		final Scanner scanner = new Scanner(System.in);
		final App app = new App(scanner);
		app.createNewAddressBook();
		app.createNewAddressBook();
		Assert.assertEquals(2, User.getInstance().getAddressBookList().size());
	}

	@Test
	public void listAllAddressBooksTest() {

		final String input = "Sales\nIT";
		final InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		final Scanner scanner = new Scanner(System.in);
		final App app = new App(scanner);
		app.createNewAddressBook();
		app.createNewAddressBook();

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		app.listAllAddressBooks();
		Assert.assertEquals("User address books:\nSales\nIT\n", out.toString());
	}

	@Test
	public void selectAddressBookTest() {

		final String input = "Sales\n0";
		final InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		final Scanner scanner = new Scanner(System.in);

		final App app = new App(scanner);
		app.createNewAddressBook();

		app.selectAddressBook();

		Assert.assertEquals("Sales", app.getCurrentlySelectedAddressBook().get().getAddressBookName());
	}

	@Test
	public void printAllContactsFromAnAddressBook() {

		final String input = "Sales\n0\nJyoti\nVerma\n0424144900\n0\nNo\n0";
		final InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		final Scanner scanner = new Scanner(System.in);
		final App app = new App(scanner);
		app.createNewAddressBook();
		app.selectAddressBook();
		app.createContact();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		app.printAllContactsFromAnAddressBook();
		Assert.assertEquals("Select address book to print : (Enter the number against the address book name)\n0 : "
				+ "Sales\nContact Name=Jyoti Verma\n\tPhoneNumbers:\tHOME:0424144900\n\n", out.toString());
	}

	@Test
	public void printUniqueContactsFromAllAddressBooks() throws IOException {

		final String input1 = "Sales\n0\nJyoti\nVerma\n0424144900\n0\nNo\n";
		final InputStream stream1 = new ByteArrayInputStream(input1.getBytes());
		System.setIn(stream1);

		Scanner scanner = new Scanner(System.in);
		final App app = new App(scanner);
		app.createNewAddressBook();
		app.selectAddressBook();
		app.createContact();

		final String input2 = "Home\n0\nJyoti\nVerma\n0424144900\n0\nNo\n";
		final InputStream stream2 = new ByteArrayInputStream(input2.getBytes());
		System.setIn(stream2);
		scanner = new Scanner(System.in);
		app.setIn(scanner);

		app.createNewAddressBook();
		app.selectAddressBook();
		app.createContact();

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		app.printAllContacts();
		Assert.assertEquals(
				"Printing all unique contacts:\nContact Name=Jyoti Verma\n\tPhoneNumbers:\tHOME:0424144900\n\n",
				out.toString());
	}
}
