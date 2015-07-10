package de.aaa.bankaccountactivityvisualizer;

import org.joda.time.DateTime;

public class AccountActivityItem {

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
	
}
