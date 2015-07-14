package de.aaa.bankaccountactivityvisualizer;

import java.util.Collection;
import java.util.Set;

import de.aaa.bankaccountactivityvisualizer.domain.AccountActivityItem;

public interface ActivitiesGrouper {

	Collection<AccountActivityItem> transform(Set<AccountActivityItem> accountActivity);
}
