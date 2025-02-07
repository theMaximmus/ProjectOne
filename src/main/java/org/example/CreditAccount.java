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

    private static final int FIRST_MONTH = 1;
    private static final int LAST_MONTH = 12;
    private static final int FIRST_YEAR = 0;

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
        if (expirationMonth >= FIRST_MONTH && expirationMonth <= LAST_MONTH) {
            this.expirationMonth = expirationMonth;
        } else {
            System.out.println("Entered invalid month of expiration");
        }
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        if (expirationYear >= FIRST_YEAR) {
            this.expirationYear = expirationYear;
        } else {
            System.out.println("Entered invalid year of expiration");
        }

    }

    public boolean isExpired() {
        LocalDate currentDate = LocalDate.now();
        return (currentDate.getYear() > this.expirationYear) ||
                (currentDate.getYear() == this.expirationYear && currentDate.getMonthValue() > this.expirationMonth);
    }

    public String creditRemaining() {
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();

        double result = this.creditLimit - this.balance;

        String formattedCredit = numberFormatter.format(result);

        return formattedCredit;
    }

    public boolean addCharge(double amount) {
        if (this.balance + amount > creditLimit) {
            return false;
        } else {
            this.balance += amount;
            return true;
        }
    }

    public void calculateMinimumPayment() {}

    public void makePayment() {}

    public void addInterest() {}

    public int howLongToPayOff() {
        return 0;
    }

    // Extra credit  TODO: third condition of transfer
    public boolean transferAccount(CreditAccount transferTarget) {
        if ( ((this.getBalance() + this.getBalance() * 0.01) < transferTarget.getCreditLimit()) && (this.creditLimit < transferTarget.getCreditLimit()) && (this.interestRate < transferTarget.getInterestRate()) ) {
            CreditAccount instance = new CreditAccount();
            return true;
        }

        return false;
    }

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