package banking;

import java.util.Random;

public class CardGenerator {
    private Random random = new Random();

    public String generateCardNum() {
        StringBuilder CardNum = new StringBuilder("400000");
        byte left = 48;
        byte right = 57;
        for (int i = 0; i < 9; i++) {
            CardNum.append((char) (random.nextInt(right - left + 1) + left));
        }

        LuhnAlgorithm algorithm = new LuhnAlgorithm(CardNum.toString());
        CardNum.append(algorithm.getChecksum());

        return CardNum.toString();
    }

    public String generatePIN() {
        byte left = 48;
        byte right = 57;

        StringBuilder PIN = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            PIN.append((char)(random.nextInt(right - left + 1) + left));
        }

        return PIN.toString();
    }
}
