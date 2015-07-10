package de.aaa.bankaccountactivityvisualizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TdAccountActivityCsvParser {
	
	public AccountActivity parseAccountActivity(File file) throws FileNotFoundException{
		if (file==null){
			return null;
		}
		AccountActivity accountActivity = new AccountActivity();
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		
		
		return accountActivity;
	}

}
