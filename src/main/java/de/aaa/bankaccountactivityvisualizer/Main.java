package de.aaa.bankaccountactivityvisualizer;

import java.io.File;
import java.io.IOException;

import de.aaa.bankaccountactivityvisualizer.domain.AccountActivity;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        TdAccountActivityCsvParser parser = new TdAccountActivityCsvParser();
        AccountActivity accountActivity = parser.parseAccountActivity(new File("C:\\Users\\malik.atalla\\Downloads\\accountactivity_2015-06.csv"));
        System.out.println(accountActivity);
        
    }
}
