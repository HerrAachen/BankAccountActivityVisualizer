package de.aaa.bankaccountactivityvisualizer.domain;

import java.util.ArrayList;
import java.util.List;

public class Grouping {

  List<Group> activityGroups = new ArrayList<>();

  public List<Group> getActivityGroups() {
    return activityGroups;
  }

  public void setActivityGroups(List<Group> activityGroups) {
    this.activityGroups = activityGroups;
  }
}
