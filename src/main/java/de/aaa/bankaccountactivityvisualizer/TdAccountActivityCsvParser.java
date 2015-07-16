package de.aaa.bankaccountactivityvisualizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import de.aaa.bankaccountactivityvisualizer.domain.AccountActivity;
import de.aaa.bankaccountactivityvisualizer.domain.AccountActivityItem;

public class TdAccountActivityCsvParser {

  private static final int BALANCE_COLUMN = 4;
  private static final int POSITIVE_BOOKING_AMOUNT_COLUMN = 3;
  private static final int NEGATIVE_BOOKING_AMOUNT_COLUMN = 2;
  private static final int MESSAGE_COLUMN = 1;
  private static final int DATE_COLUMN = 0;
  private static final String DELIMITER = ",";
  DateTimeFormatter dateFormat = DateTimeFormat.forPattern("MM/dd/yyyy");
  
  public AccountActivity parseAccountActivity(String directory) throws IOException{
    Stream<Path> filesInDirectory = Files.list(new File(directory).toPath());
    Stream<Path> csvFiles = filesInDirectory.filter(p -> p.toString().endsWith(".csv"));
  }

  public AccountActivity parseAccountActivity(File file) throws IOException {
    if (file == null) {
      return null;
    }
    AccountActivity accountActivity = new AccountActivity();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

      String line = "";
      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split(DELIMITER);
        AccountActivityItem item = new AccountActivityItem();
        item.setTime(dateFormat.parseDateTime(parts[DATE_COLUMN]));
        item.setMessage(parts[MESSAGE_COLUMN]);
        item.setBookedAmount(parseBookedAmount(parts));
        item.setBalance(Double.parseDouble(parts[BALANCE_COLUMN]));

        accountActivity.getAccountActivityItems().add(item);
      }
    }

    return accountActivity;
  }

  private double parseBookedAmount(String[] parts) {
    String negativeAmountField = parts[NEGATIVE_BOOKING_AMOUNT_COLUMN];
    String positiveAmountField = parts[POSITIVE_BOOKING_AMOUNT_COLUMN];
    if (isEmptyString(negativeAmountField)){
      return Double.parseDouble(positiveAmountField);
    } else {
      return -1 * Double.parseDouble(negativeAmountField);
    }
  }

  private boolean isEmptyString(String string) {
    return string==null || string.length()==0;
  }
}
