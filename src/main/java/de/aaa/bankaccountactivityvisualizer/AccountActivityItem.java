package de.aaa.bankaccountactivityvisualizer;

import org.joda.time.DateTime;

public class AccountActivityItem implements Comparable<AccountActivityItem> {

  private DateTime time;
  private String message;
  private double bookedAmount;
  private double balance;

  public DateTime getTime() {
    return time;
  }

  public void setTime(DateTime time) {
    this.time = time;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public double getBookedAmount() {
    return bookedAmount;
  }

  public void setBookedAmount(double bookedAmount) {
    this.bookedAmount = bookedAmount;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(balance);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(bookedAmount);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((message == null) ? 0 : message.hashCode());
    result = prime * result + ((time == null) ? 0 : time.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AccountActivityItem other = (AccountActivityItem) obj;
    if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
      return false;
    if (Double.doubleToLongBits(bookedAmount) != Double.doubleToLongBits(other.bookedAmount))
      return false;
    if (message == null) {
      if (other.message != null)
        return false;
    } else if (!message.equals(other.message))
      return false;
    if (time == null) {
      if (other.time != null)
        return false;
    } else if (!time.equals(other.time))
      return false;
    return true;
  }

  @Override
  public int compareTo(AccountActivityItem o) {
    int smaller = -1;
    int bigger = 1;
    int equal = 0;
    if (this.time.isBefore(o.time)) {
      return smaller;
    } else if (this.time.isAfter(o.time)) {
      return bigger;
    } else {
      if (this.message.compareTo(o.message) < 0) {
        return smaller;
      } else if (this.message.compareTo(o.message) > 0) {
        return bigger;
      } else {
        if (this.bookedAmount < o.bookedAmount) {
          return smaller;
        } else if (this.bookedAmount > o.bookedAmount) {
          return bigger;
        } else {
          if (this.balance < o.balance){
            return smaller;
          } else if (this.balance > o.balance){
            return bigger;
          }
        }
      }
    }

    return equal;
  }
}
