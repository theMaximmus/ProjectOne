package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CreditAccount one = new CreditAccount(100, 0.02, 333, "1234_1234_1234_1234", 2, 2025);
        System.out.println(one);
        System.out.println(one.isExpired());
    }
}