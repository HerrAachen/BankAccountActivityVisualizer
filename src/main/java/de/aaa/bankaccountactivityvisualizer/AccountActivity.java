package de.aaa.bankaccountactivityvisualizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AccountActivity {

	final Set<AccountActivityItem> accountActivityItems = new TreeSet<>();

	public Set<AccountActivityItem> getAccountActivityItems() {
		return accountActivityItems;
	}
}
