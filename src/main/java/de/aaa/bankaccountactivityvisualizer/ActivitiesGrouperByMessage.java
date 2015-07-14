package de.aaa.bankaccountactivityvisualizer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.aaa.bankaccountactivityvisualizer.domain.AccountActivityItem;

public class ActivitiesGrouperByMessage implements ActivitiesGrouper {

	public Collection<AccountActivityItem> transform(Set<AccountActivityItem> accountActivity) {
		Map<String, AccountActivityItem> message2accountActivityItem = new HashMap<>();
		for (AccountActivityItem item : accountActivity) {
			AccountActivityItem accountActivityItem = message2accountActivityItem.get(item.getMessage());
			if (accountActivityItem == null) {
				accountActivityItem = new AccountActivityItem();
				accountActivityItem.setMessage(item.getMessage());
				message2accountActivityItem.put(item.getMessage(), accountActivityItem);
			}
			accountActivityItem.setBookedAmount(accountActivityItem.getBookedAmount() + item.getBookedAmount());
		}
		
		return message2accountActivityItem.values();
	}
}
