package au.com.reece.app.addressbook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {

	private String firstName;
	private String lastName;
	private List<PhoneNumber> phoneNumbers;

	public Contact(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Contact(String firstName, String lastName, List<PhoneNumber> phoneNumbers) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumbers = phoneNumbers;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		if (phoneNumbers != null) {
			return phoneNumbers;
		}
		phoneNumbers = new ArrayList<>();
		return phoneNumbers;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Contact Name=" + firstName + " " + lastName);
		sb.append("\n\tPhoneNumbers:");
		getPhoneNumbers().forEach(ph -> sb.append("\t" + ph.getType() + ":" + ph.getNumber()));
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, phoneNumbers);
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (!(other instanceof Contact)) {
			return false;
		}
		final Contact o = (Contact) other;
		if (this.getFirstName().equals(o.getFirstName()) && this.getLastName().equals(o.getLastName())) {
			if (this.getPhoneNumbers().equals(o.getPhoneNumbers())) {
				return true;
			}
		}
		return false;
	}

}
