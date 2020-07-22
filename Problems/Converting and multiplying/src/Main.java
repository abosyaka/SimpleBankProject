import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] seq = new String[100];
        int i = 0;
        String input = "";

        while (true) {
            input = scanner.next();
            if (input.equals("0")) break;
            seq[i++] = input;
        }

        for (int j = 0; j < i; j++) {
            try {
                System.out.println(Integer.parseInt(seq[j]) * 10);
            } catch (RuntimeException e) {
                System.out.println("Invalid user input: " + seq[j]);
            }
        }
    }
}