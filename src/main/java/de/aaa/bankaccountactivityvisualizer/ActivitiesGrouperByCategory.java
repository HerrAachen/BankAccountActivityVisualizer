package de.aaa.bankaccountactivityvisualizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.aaa.bankaccountactivityvisualizer.domain.AccountActivityItem;

public class ActivitiesGrouperByCategory implements ActivitiesGrouper {

	Map<String, List<String>> categoryNameToContainingStrings;
	private static final String CATEGORY_CONFIG_FILE = "categories.txt";
	
	public ActivitiesGrouperByCategory() {
		categoryNameToContainingStrings = readCategoriesFromFile(CATEGORY_CONFIG_FILE);
	}
	
	private Map<String, List<String>> readCategoriesFromFile(String categoryConfigFile) {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(categoryConfigFile));
		String line = "";
		while((line = bufferedReader.readLine())!=null){
			
		}
	}

	@Override
	public Collection<AccountActivityItem> transform(Set<AccountActivityItem> accountActivity) {
		// TODO Auto-generated method stub
		return null;
	}
}
