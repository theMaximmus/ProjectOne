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
    private static final double MIN_PAYMENT_THRESHOLD = 25.0;
    private static final double HIGH_BALANCE_THRESHOLD = 1000.0;
    private static final double HIGH_BALANCE_PERCENT = 0.02;

    private static final DecimalFormat df = new DecimalFormat("#.##");

    public CreditAccount(double balance, double interestRate, double creditLimit, String cardNumber,
            int expirationMonth, int expirationYear) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public double getBalance() {
        return Double.parseDouble(df.format(this.balance));
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

    public double calculateMinimumPayment() {
        if (this.balance <= MIN_PAYMENT_THRESHOLD) {
            return this.balance;
        } else if (this.balance < HIGH_BALANCE_THRESHOLD) {
            return MIN_PAYMENT_THRESHOLD;
        } else {
            return this.balance * HIGH_BALANCE_PERCENT;
        }
    }

    public void makePayment(double amount) {
        if (amount >= this.balance) {
            this.balance = 0;
        } else {
            this.balance -= amount;
        }
    }

    public void makePayment() {
        double minPayment = calculateMinimumPayment();
        makePayment(minPayment);
    }

    public void addInterest() {
        this.balance += this.balance * (this.interestRate / 12);
    }

    public int howLongToPayOff() {
        CreditAccount testCreditAccount = new CreditAccount(this.balance, this.interestRate, this.creditLimit, this.cardNumber, this.expirationMonth, this.expirationYear);
//        double tempBalance = this.balance;
        int months = 0;
        while (testCreditAccount.getBalance() > 0) {
            double minPayment = (testCreditAccount.getBalance() <= MIN_PAYMENT_THRESHOLD) ? testCreditAccount.getBalance()
                    : (testCreditAccount.getBalance() < HIGH_BALANCE_THRESHOLD) ? MIN_PAYMENT_THRESHOLD
                            : (testCreditAccount.getBalance() * HIGH_BALANCE_PERCENT);
            testCreditAccount.balance -= minPayment;
            testCreditAccount.balance += testCreditAccount.getBalance() * (testCreditAccount.interestRate / 12);
            months++;
            if (months > 1000) {
                return -1;
            }
        }
        return months;
    }

    // Extra credit
    public boolean transferAccount(CreditAccount transferTarget) {
        double transferBalance = this.getBalance() * 1.01;
        double remainingCredit = transferTarget.getCreditLimit() - transferBalance;
        
        if (remainingCredit > 0) {
            return false;
        }

        if (this.getCreditLimit() >= transferTarget.getCreditLimit()) {
            return false;
        }
        if (this.interestRate <= transferTarget.getInterestRate()) {
            return false;
        }
        if (transferTarget.howLongToPayOff() >= (this.howLongToPayOff() / 2)) {
            return false;
        }

        transferTarget.balance += transferBalance;
        this.balance = 0;
        return true;
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
                "\tCredit Limit: " + formattedCreditLimit + "\n" +
                "\tCard Number: " + cardNumber + "\n" +
                "\tExpiration Date: " + expirationMonth + "/" + expirationYear;
    }
}

