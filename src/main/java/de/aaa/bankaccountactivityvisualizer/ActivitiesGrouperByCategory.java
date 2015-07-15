package de.aaa.bankaccountactivityvisualizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.aaa.bankaccountactivityvisualizer.domain.AccountActivityItem;
import de.aaa.bankaccountactivityvisualizer.domain.Group;
import de.aaa.bankaccountactivityvisualizer.domain.Grouping;
import de.aaa.bankaccountactivityvisualizer.exceptions.ConfigurationException;

public class ActivitiesGrouperByCategory implements ActivitiesGrouper {

	Map<String, List<String>> categoryNameToContainingStrings;
	private static final String CATEGORY_CONFIG_FILE = "categories.txt";

	public ActivitiesGrouperByCategory() {
		try {
			categoryNameToContainingStrings = readCategoriesFromFile(CATEGORY_CONFIG_FILE);
		} catch (IOException e) {
			throw new ConfigurationException(
					"Could not find file '" + CATEGORY_CONFIG_FILE + "' in " + System.getProperty("user.dir"), e);
		}
	}

	private Map<String, List<String>> readCategoriesFromFile(String categoryConfigFile) throws IOException {
		Map<String, List<String>> categoryMap = new HashMap<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(categoryConfigFile)))) {
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(";");
				String category = parts[0];
				List<String> containedTerms = new ArrayList<>();
				for (int i = 1; i < parts.length; i++) {
					containedTerms.add(parts[i]);
				}
				categoryMap.put(category, containedTerms);
			}
		}
		return categoryMap;
	}

	@Override
	public Grouping transform(Set<AccountActivityItem> accountActivity) {
		Set<AccountActivityItem> uncategorizedItems = new HashSet<>();
		uncategorizedItems.addAll(accountActivity);
		
		Map<String, Group> category2group = new HashMap<>();
		for (String category : categoryNameToContainingStrings.keySet()) {
			for (AccountActivityItem item : accountActivity) {
				if (matches(category, item)) {
					uncategorizedItems.remove(item);
					Group group = category2group.get(category);
					if (group == null) {
						group = new Group();
						category2group.put(category, group);
					}
					group.setTotalBookedAmount(group.getTotalBookedAmount() + item.getBookedAmount());
					group.setName(category + " " + group.getTotalBookedAmount() + "$");
				}
			}
		}
		Grouping groupingByCategory = new Grouping();
		groupingByCategory.getActivityGroups().addAll(category2group.values());
		
		Grouping uncategorizedItemsGroupedByMessage = new ActivitiesGrouperByMessage().transform(uncategorizedItems);
		groupingByCategory.getActivityGroups().addAll(uncategorizedItemsGroupedByMessage.getActivityGroups());
		
		return groupingByCategory;
	}

	private boolean matches(String category, AccountActivityItem item) {
		List<String> containedTerms = categoryNameToContainingStrings.get(category);
		for (String containedTerm : containedTerms) {
			if (item.getMessage().contains(containedTerm)) {
				return true;
			}
		}
		return false;
	}
}
