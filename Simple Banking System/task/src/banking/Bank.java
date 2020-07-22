package banking;

import java.util.Scanner;

public class Bank {
    private final boolean EXIT = false;
    private final boolean GO = true;

    private Scanner scanner = new Scanner(System.in);
    Account accountManager = new Account();

    public void start() {
        boolean state = GO;
        byte input;

        do {
            printBankOptions();
            input = scanner.nextByte();

            switch (input) {
                case 1:
                    accountManager.createAccount();

                    successReg();
                    break;
                case 2:
                    if (isLoggedIn()) {
                        System.out.println("You have successfully logged in!\n");

                        state = accountLoop();
                    } else {
                        System.out.println("Wrong card number or PIN!\n");
                    }
                    break;
                case 0:
                    printBye();
                    state = EXIT;
            }
        } while (state);
    }

    private void printBankOptions() {
        System.out.println("1. Create an account\n" +
                "2. Log into account\n" +
                "0. Exit");
        System.out.print(">");
    }

    private void printAccMenu() {
        System.out.print("1. Balance\n" +
                "2. Add income\n" +
                "3. Do transfer\n" +
                "4. Close account\n" +
                "5. Log out\n" +
                "0. Exit\n>");
    }

    private void printBye() {
        System.out.println("Bye!");
    }

    private void successReg() {
        System.out.println("Your card number has been created\n" +
                "Your card number:\n" +
                accountManager.getUser().getCardNum() +
                "\nYour card PIN:\n" +
                accountManager.getUser().getPIN() + "\n");
    }

    private boolean isLoggedIn() {
        System.out.print("Enter your card number:\n>");
        String cardVerification = scanner.next();
        System.out.print("Enter your PIN:\n>");
        String PINVerification = scanner.next();

        return accountManager.authorization(cardVerification, PINVerification);
    }

    private int addIncome() {
        System.out.println("Enter income:\n>");
        int income = scanner.nextInt();
        return income;
    }

    private boolean accountLoop() {
        boolean accState = GO;
        byte input;
        boolean running = GO;

        do {
            printAccMenu();

            input = scanner.nextByte();

            switch (input) {
                case 1:
                    System.out.println(accountManager.getBalance());
                    break;
                case 2:
                    if (accountManager.addIncome(addIncome())) {
                        System.out.println("Income was added!");
                    } else {
                        printError();
                    }
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    if (accountManager.accountDelete()) {
                        System.out.println("The account has been deleted!");
                        accState = EXIT;
                    } else {
                        printError();
                    }
                    break;
                case 5:
                    accState = EXIT;
                    break;
                case 0:
                    running = EXIT;
                    printBye();
                    break;
            }

        } while (accState && running);

        return running;
    }

    private void printError() {
        System.out.println("Something went wrong...");
    }

    private void transfer() {
        int money;
        System.out.println("Transfer\n" +
                "Enter card number:\n>");
        String cardNum = scanner.next();
        if (!accountManager.isExists(new User(cardNum))) {
            System.out.println("Such a card does not exist.\n");
        } else {
            money = scanner.nextInt();
            if (accountManager.transferMoney(money, cardNum)) {
                System.out.println("Success!\n");
            } else{
                System.out.println("Not enough money!\n");
            }
        }
    }
}
