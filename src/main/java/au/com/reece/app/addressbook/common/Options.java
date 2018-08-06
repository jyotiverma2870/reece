package au.com.reece.app.addressbook.common;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class Options {

	public static enum MainMenuOptions {
	// @formatter:off
		CREATE("0", "Create an address book"),
		LIST("1", "List all address books"),
		SELECT("2", "Select an address book to work with"),
		PRINT_ALL("3", "Print all contacts from an address book"),
		PRINT_ALL_UNIQUE("4", "Print all unique contacts (across all address books)"),
		EXIT("5", "Exit Application");
		// @formatter:on

		private final String id;
		private final String option;

		MainMenuOptions(String id, String option) {
			this.id = id;
			this.option = option;
		}

		public String getId() {
			return id;
		}

		public String getOption() {
			return option;
		}

		public static Optional<MainMenuOptions> getById(String id) {
			return Arrays.asList(Options.MainMenuOptions.values()).stream().filter(o -> o.getId().equals(id))
					.collect(Collectors.reducing((a, b) -> null));
		}
	}

	public static enum AddressBookOptions {

		CREATE("1", "Create a contact"), REMOVE("2", "Remove a contact"),
		PRINT_ALL("3", "Print all address book contacts"), MAIN_MENU("4", "Main Menu");

		private final String id;
		private final String option;

		AddressBookOptions(String id, String option) {
			this.id = id;
			this.option = option;
		}

		public String getId() {
			return id;
		}

		public String getOption() {
			return option;
		}

		public static Optional<AddressBookOptions> getById(String id) {
			return Arrays.asList(Options.AddressBookOptions.values()).stream().filter(o -> o.getId().equals(id))
					.collect(Collectors.reducing((a, b) -> null));
		}
	}
}
