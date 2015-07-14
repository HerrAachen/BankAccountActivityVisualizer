package de.aaa.bankaccountactivityvisualizer.domain;

import java.util.Collection;
import java.util.HashSet;

public class Group {

  private Collection<AccountActivityItem> activityItems = new HashSet<>();
  private String name;
  private double totalBookedAmount;
  
  public Collection<AccountActivityItem> getActivityItems() {
    return activityItems;
  }
  public void setActivityItems(Collection<AccountActivityItem> activityItems) {
    this.activityItems = activityItems;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public double getTotalBookedAmount() {
    return totalBookedAmount;
  }
  public void setTotalBookedAmount(double totalBookedAmount) {
    this.totalBookedAmount = totalBookedAmount;
  }
  
  
}
