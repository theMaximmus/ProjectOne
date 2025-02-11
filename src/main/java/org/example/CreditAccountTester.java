package org.example;

public class CreditAccountTester {

    // tester methods
    private static boolean allTests = true;

    public static void testAddCharge(CreditAccount account, double amount, boolean expected) {
        boolean result = account.addCharge(amount);
        System.out.println("Expected Charge Sucess: " + expected + "\nActual Charge Sucess: " + result + "\n");

        if (result != expected) {
            System.out
                    .println("**********ADD CHARGE TEST FAILED: actual: " + result + ", expected: " + expected + "\n");
            allTests = false;
        }
    }

    public static void testBalance(CreditAccount account, double expected) {
        double result = account.getBalance();
        System.out.println("Expected Balance: " + expected + "\nActual Balance: " + result + "\n");

        if (result != expected) {
            System.out.println("**********BALANCE TEST FAILED: actual: " + result + ", expected: " + expected + "\n");
            allTests = false;
        }
    }

    public static void testCredit(CreditAccount account, String expected) {
        String result = account.creditRemaining();
        System.out.println("Expected Charge Remaining: " + expected + "\nActual Charge Remaining: " + result + "\n");

        if (!result.equals(expected)) {
            System.out.println("**********CREDIT TEST FAILED: actual: " + result + ", expected: " + expected + "\n");
            allTests = false;
        }
    }

    public static void testPayOff(CreditAccount account, int expected){
        int result = account.howLongToPayOff();
        System.out.println("Expected How Long To Pay Off Remaining: " + expected + "\nActual Charge Remaining: " + result + "\n");

        if(result != expected) {
            System.out.println("**********HOW LONG TO PAY OFF TEST FAILED: actual: " + result + ", expected: " + expected + "\n");
            allTests = false;
        }
    }

    public static void testExtra(CreditAccount account, CreditAccount newAccount, boolean expected){
        boolean result = account.transferAccount(newAccount);
        System.out.println("Expected Transfer: " + expected + "\nActual Transfer: " + result + "\n");

        if(result != expected) {
            System.out.println("**********TRANSFER FAILED: actual: " + result + ", expected: " + expected + "\n");
            allTests = false;
        }
    }

    public static void main(String[] args) {

        System.out.println("\n------------------------------------Test Case A------------------------------------");
        CreditAccount A = new CreditAccount(199, 0.055, 500, "5555_5555_5555_5555", 2, 2025);
        System.out.println(A);
        allTests = true;

        // add charge 200
        testAddCharge(A, 200, true);
        testBalance(A, 399);
        testCredit(A, "$101.00");

        // make minimum payment
        A.calculateMinimumPayment();
        A.makePayment();
        testBalance(A, 374);
        testCredit(A, "$126.00");

        // add interest
        A.addInterest();
        testBalance(A, 375.71);
        testCredit(A, "$124.29");

        // add charge 300
        testAddCharge(A, 200, false);

        // add charge 52.24
        testAddCharge(A, 52.24, true);
        testBalance(A, 427.95);
        testCredit(A, "$72.05");

        // make payment 450
        A.makePayment(450);
        testBalance(A, 0);
        testCredit(A, "$500.00");

        // add interest
        A.addInterest();
        testBalance(A, 0);
        testCredit(A, "$500.00");

        if (allTests) {
            System.out.println("\n**********TEST CASE A PASSED**********");
        }

        System.out.println("\n------------------------------------Test Case B------------------------------------");
        CreditAccount B = new CreditAccount(0, 0.069, 5000, "9999_9999_9999_9999", 2, 2025);
        System.out.println(B);
        allTests = true;
        
        // add charge of 1500
        testAddCharge(B, 1500, true);
        testBalance(B, 1500);
        testCredit(B, "$3,500.00");

        // make minimum payment
        B.calculateMinimumPayment();
        B.makePayment();
        testBalance(B, 1470);
        testCredit(B, "$3,530.00");

        // add interest
        B.addInterest();
        testBalance(B, 1478.45);
        testCredit(B, "$3,521.55");

        // add charge 2200.39
        testAddCharge(B, 2200.39, true);
        testBalance(B, 3678.84);
        testCredit(B, "$1,321.16");

        // add charge 3000
        testAddCharge(B, 3000, false);

        // make minimum payment
        B.calculateMinimumPayment();
        B.makePayment();
        testBalance(B, 3605.27);
        testCredit(B, "$1,394.73");

        //add interest
        B.addInterest();
        testBalance(B, 3626);
        testCredit(B, "$1,374.00");

        //make payment of 3500
        B.makePayment(3500);
        testBalance(B, 126);
        testCredit(B, "$4,874.00");

        //make minimum payment
        B.makePayment(B.calculateMinimumPayment());
        testBalance(B, 101);
        testCredit(B, "$4,899.00");

        //make minimum payment
        B.makePayment(B.calculateMinimumPayment());
        testBalance(B, 76);
        testCredit(B, "$4,924.00");

        //add interest
        B.addInterest();
        testBalance(B, 76.43);
        testCredit(B, "$4,923.57");

        //make payment of 60
        B.makePayment(60);
        testBalance(B, 16.43);
        testCredit(B, "$4,983.57");

        //make minimum payment
        B.makePayment(B.calculateMinimumPayment());
        testBalance(B, 0);
        testCredit(B, "$5,000.00");

        if (allTests) {
            System.out.println("\n**********TEST CASE B PASSED**********");
        }

        System.out.println("\n------------------------------------Test Case C------------------------------------");
        allTests = true;
        CreditAccount C1 = new CreditAccount(5985, 0.015 , 111, "1111_1111_1111_1111", 2, 2025);
        testPayOff(C1, 136);

        CreditAccount C2 = new CreditAccount(3000, 0.085 , 111, "1111_1111_1111_1111", 2, 2025);
        testPayOff(C2, 131);

        CreditAccount C3= new CreditAccount(3000, 0.15 , 111, "1111_1111_1111_1111", 2, 2025);
        testPayOff(C3, 197);

        CreditAccount C4= new CreditAccount(5000, 0.19 , 111, "1111_1111_1111_1111", 2, 2025);
        testPayOff(C4, 421);
        
        if (allTests) {
            System.out.println("\n**********TEST CASE C PASSED**********");
        }

        System.out.println("\n------------------------------------Test Case D------------------------------------");
        CreditAccount one = new CreditAccount(100, 0.02, 333, "1234_1234_1234_1234", 2, 2025);
        // Print the instance to the console
        System.out.println(one);
        // Determine whether the card is expired
        System.out.println(one.isExpired());
        // Print the remaining credit
        System.out.println(one.creditRemaining());
        // Add a charge that overflows the limit
        System.out.println(one.addCharge(300));
        // Add a charge that does not overflow the limit
        System.out.println(one.addCharge(223));

        System.out.println("\n------------------------------------Extra Test Cases------------------------------------");
        allTests = true;

        // Test Case must succeed because there is enough credit remaining for the transfer balance.
        CreditAccount extra1 = new CreditAccount(100, 0.07, 100, "1234_1234_1234_1234", 2, 2025);
        CreditAccount new1 = new CreditAccount(0, 0.05, 101, "1234_1234_1234_1234", 2, 2025);
        testExtra(extra1, new1, true);
        System.out.println("Extra1" + "\n");

        // Test Case must fail because there is not enough credit remaining for the transfer balance.
        CreditAccount extra2 = new CreditAccount(100, 0.07, 100, "1234_1234_1234_1234", 2, 2025);
        CreditAccount new2 = new CreditAccount(20, 0.05, 101, "1234_1234_1234_1234", 2, 2025);
        testExtra(extra2, new2, false);
        System.out.println("Extra2" + "\n");

        // Test Case must succeed because it does have a higher credit limit.
        CreditAccount extra3 = new CreditAccount(100, 0.15, 10000, "1234_1234_1234_1234", 2, 2025);
        CreditAccount new3 = new CreditAccount(100, 0.05, 11000, "1234_1234_1234_1234", 2, 2025);
        testExtra(extra3, new3, true);
        System.out.println("Extra3" + "\n");

        // Test Case must fail because it does not have a lower credit limit.
        CreditAccount extra4 = new CreditAccount(100, 0.15, 10000, "1234_1234_1234_1234", 2, 2025);
        CreditAccount new4 = new CreditAccount(100, 0.05, 9000, "1234_1234_1234_1234", 2, 2025);
        testExtra(extra4, new4, false);
        System.out.println("Extra4" + "\n");

        // Test Case must succeed because it does have a lower interest rate.
        CreditAccount extra5 = new CreditAccount(100, 0.19, 10000, "1234_1234_1234_1234", 2, 2025);
        CreditAccount new5 = new CreditAccount(100, 0.05, 10000, "1234_1234_1234_1234", 2, 2025);
        testExtra(extra5, new5, true);
        System.out.println("Extra5" + "\n");

        // Test Case must fail because it does not have a lower interest rate.
        CreditAccount extra6 = new CreditAccount(100, 0.19, 10000, "1234_1234_1234_1234", 2, 2025);
        CreditAccount new6 = new CreditAccount(100, 0.20, 10000, "1234_1234_1234_1234", 2, 2025);
        testExtra(extra6, new6, false);
        System.out.println("Extra6" + "\n");

        // Test Case must succeed because it takes less time to pay-off using a minimum payment
        CreditAccount extra7 = new CreditAccount(100, 0.19, 10000, "1234_1234_1234_1234", 2, 2025);
        CreditAccount new7 = new CreditAccount(100, 0.02, 10000, "1234_1234_1234_1234", 2, 2025);
        testExtra(extra7, new7, true);
        System.out.println("Extra7" + "\n");

        // Test Case must fail because it takes more time to pay-off using a minimum payment
        CreditAccount extra8 = new CreditAccount(100, 0.20, 15000, "1234_1234_1234_1234", 2, 2025);
        CreditAccount new8 = new CreditAccount(100, 0.15, 15000, "1234_1234_1234_1234", 2, 2025);
        testExtra(extra8, new8, false);
        System.out.println("Extra8" + "\n");

        if (allTests) {
            System.out.println("\n**********TEST CASE EXTRA PASSED**********");
        }

    }   
}