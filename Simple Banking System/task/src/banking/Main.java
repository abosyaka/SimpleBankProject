package banking;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static public String filename;

    public static void main(String[] args) {
        Main.filename = args[1];
        Bank bank = new Bank();
        bank.start();
    }
}
