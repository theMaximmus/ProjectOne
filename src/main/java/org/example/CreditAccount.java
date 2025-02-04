package org.example;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

public class CreditAccount {
    private double balance; // amount owed
    private double interestRate;
    private double creditLimit; // the maximum amount that can be charged
    private String cardNumber; // can be up to 16 digits long
    private int expirationMonth;
    private int expirationYear;

    public CreditAccount(double balance, double interestRate, double creditLimit, String cardNumber, int expirationMonth, int expirationYear) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
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

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public boolean isExpired() {
        LocalDate currentDate = LocalDate.now();
        return (currentDate.getYear() > this.expirationYear) ||
                (currentDate.getYear() == this.expirationYear && currentDate.getMonthValue() > this.expirationMonth);
    }

    public String creditRemaining() {
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();

        double result = this.creditLimit - this.balance;

        String formattedBalance = numberFormatter.format(result);

        return formattedBalance;
    }

    public void addCharge() {}

    public void calculateMinimumPayment() {}

    public void makePayment() {}

    public void addInterest() {}

    public int howLongToPayOff() {
        return 0;
    }

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
                "\tExpiration Date: " + expirationMonth + "/" + expirationYear;
    }
}
 /*
 Questions: Best format for cardNumber (BigInteger/String)? Validity Checkers?
  */