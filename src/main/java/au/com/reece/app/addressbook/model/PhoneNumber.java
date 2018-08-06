package au.com.reece.app.addressbook.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PhoneNumber {

	PhoneNumberType type;
	String number;

	public PhoneNumber(PhoneNumberType type, String number) {
		this.type = type;
		this.number = number;
	}

	public PhoneNumberType getType() {
		return type;
	}

	public void setType(PhoneNumberType type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public static enum PhoneNumberType {
		HOME, MOBILE, OFFICE, OTHER;

		public static Optional<PhoneNumberType> getById(String id) {
			return Arrays.asList(PhoneNumberType.values()).stream().filter(o -> String.valueOf(o.ordinal()).equals(id))
					.collect(Collectors.reducing((a, b) -> null));
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, type);
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (!(other instanceof PhoneNumber)) {
			return false;
		}
		final PhoneNumber o = (PhoneNumber) other;
		return this.getType() == o.getType() && number.equals(o.getNumber());
	}
}
