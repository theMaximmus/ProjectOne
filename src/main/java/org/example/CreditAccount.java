package org.example;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CreditAccount {
    private double balance; // amount owed
    private double interestRate;
    private double creditLimit; // the maximum amount that can be charged
    private String cardNumber; // can be up to 16 digits long
    private String expirationMonthAndYear;

    public CreditAccount(double balance, double interestRate, double creditLimit, String cardNumber, String expirationMonthAndYear) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        this.cardNumber = cardNumber;
        this.expirationMonthAndYear = expirationMonthAndYear;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    // no need to do any validity checking on the credit card number
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationMonthAndYear() {
        return expirationMonthAndYear;
    }

    public void setExpirationMonthAndYear(String expirationMonthAndYear) {
        this.expirationMonthAndYear = expirationMonthAndYear;
    }

    public boolean isExpired() {}

    public double creditRemaining() {}

    public void addCharge() {}

    public void calculateMinimumPayment() {}

    public void makePayment() {}

    public void addInterest() {}

    public int howLongToPayOff() {}

    // Extra credit
    public void transferAccount() {}

    @Override
    public String toString() {
        // Instantiate formatters for a better form of printed information
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();

        // Format the numbers
        String formattedBalance = numberFormatter.format(balance);
        String formattedCreditLimit = numberFormatter.format(creditLimit);
        String formattedInterestRate = percentFormatter.format(interestRate);

        return "Your CreditAccount: \n" +
                "\tBalance: " + formattedBalance + "\n" +
                "\tInterest Rate: " + formattedInterestRate + "\n" +
                "\tCredit Limit: " + formattedCreditLimit +"\n" +
                "\tCard Number: " + cardNumber +"\n" +
                "\tExpiration Date: " + expirationMonthAndYear;
    }
}
 /*
 Questions: Best format for cardNumber? Best format for Expiration Date? Validity Checkers?
  */