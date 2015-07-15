package de.aaa.bankaccountactivityvisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.aaa.bankaccountactivityvisualizer.domain.AccountActivityItem;
import de.aaa.bankaccountactivityvisualizer.domain.Group;
import de.aaa.bankaccountactivityvisualizer.domain.Grouping;

public class ActivitiesGrouperByMessage implements ActivitiesGrouper {

	public Grouping transform(Set<AccountActivityItem> accountActivity) {
		Map<String, Group> message2accountActivityItem = new HashMap<>();
		for (AccountActivityItem item : accountActivity) {
		  Group group = message2accountActivityItem.get(item.getMessage());
			if (group == null) {
				group = new Group();
				message2accountActivityItem.put(item.getMessage(), group);
			}
			group.setTotalBookedAmount(group.getTotalBookedAmount() + item.getBookedAmount());
			group.setName(item.getMessage() + ":" + group.getTotalBookedAmount() + "$");
		}
		
		Grouping grouping = new Grouping();
		grouping.getActivityGroups().addAll(message2accountActivityItem.values());
		return grouping;
	}
}
