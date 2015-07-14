package de.aaa.bankaccountactivityvisualizer;

import java.util.Set;

import de.aaa.bankaccountactivityvisualizer.domain.AccountActivityItem;
import de.aaa.bankaccountactivityvisualizer.domain.Grouping;

public interface ActivitiesGrouper {

	Grouping transform(Set<AccountActivityItem> accountActivity);
}
