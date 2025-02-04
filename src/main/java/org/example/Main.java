package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("----- Test Case D -----");
        CreditAccount one = new CreditAccount(100, 0.02, 333, "1234_1234_1234_1234", 2, 2025);
        System.out.println(one);
        System.out.println(one.isExpired());
        System.out.println(one.creditRemaining());


        System.out.println("----- Test Case A -----");
        CreditAccount A = new CreditAccount(199, 0.055, 500, "5555_5555_5555_5555", 2, 2025);

        System.out.println("----- Test Case B -----");
        CreditAccount B = new CreditAccount(0, 0.069, 5000, "9999_9999_9999_9999", 2, 2025);

        System.out.println("----- Test Case C -----");
//        CreditAccount A = new CreditAccount(199, 0.055, 500, "5555_5555_5555_5555", 2, 2025);
    }
}