package org.example;

public class CreditAccount {
    private double balance;
    private double interestRate;
    private double creditLimit;
    private int cardNumber;
    private int expirationMonth;
    private int expirationYear; // combine?

    public CreditAccount(double balance, double interestRate, double creditLimit, int cardNumber, int expirationMonth, int expirationYear) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }
}
