package de.aaa.bankaccountactivityvisualizer.domain;

import java.util.Set;
import java.util.TreeSet;

public class AccountActivity {

	final Set<AccountActivityItem> accountActivityItems = new TreeSet<>();

	public Set<AccountActivityItem> getAccountActivityItems() {
	  return accountActivityItems;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (accountActivityItems!=null){
			for(AccountActivityItem item: accountActivityItems){
				builder.append(item.toString()).append("\r\n");
			}
		}
		return builder.toString();
	}
}
