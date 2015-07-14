package de.aaa.bankaccountactivityvisualizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.aaa.bankaccountactivityvisualizer.domain.AccountActivityItem;
import de.aaa.bankaccountactivityvisualizer.domain.Grouping;

public class ActivitiesGrouperByCategory implements ActivitiesGrouper {

  Map<String, List<String>> categoryNameToContainingStrings;
  private static final String CATEGORY_CONFIG_FILE = "categories.txt";

  public ActivitiesGrouperByCategory() {
    categoryNameToContainingStrings = readCategoriesFromFile(CATEGORY_CONFIG_FILE);
  }

  private Map<String, List<String>> readCategoriesFromFile(String categoryConfigFile) throws IOException {
    Map<String, List<String>> categoryMap = new HashMap<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(categoryConfigFile))) {
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
    Map<String, AccountActivityItem> category2accountActivityItem = new HashMap<>();
    for (String category : categoryNameToContainingStrings.keySet()) {
      for (AccountActivityItem item : accountActivity) {
        if (matches(category, item)) {
          AccountActivityItem accountActivityItem = category2accountActivityItem.get(category);
          if (accountActivityItem == null) {
            accountActivityItem = new AccountActivityItem();
            accountActivityItem.setMessage(item.getMessage());
            category2accountActivityItem.put(item.getMessage(), accountActivityItem);
          }
          accountActivityItem.setBookedAmount(accountActivityItem.getBookedAmount() + item.getBookedAmount());
        }
      }
    }

    return category2accountActivityItem.values();
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
