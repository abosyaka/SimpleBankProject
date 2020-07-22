package banking;

public class LuhnAlgorithm {
    private int sumOfDigits = 0;
    private int checksum;
    private String cardNumber;

    public LuhnAlgorithm() {
    }

    public LuhnAlgorithm(String cardNumber) {
        this.cardNumber = moderatedCardNumber(cardNumber);
    }

    private String moderatedCardNumber(String cardNumber) {
        StringBuilder newCardNumber = new StringBuilder(cardNumber.length());
        int newNum;
        for (int i = 0; i < cardNumber.length(); i++) {
            if (i % 2 == 0) {
                newNum = (cardNumber.charAt(i) - '0') * 2;
            } else {
                newNum = cardNumber.charAt(i) - '0';
            }
            if (newNum > 9) newNum -= 9;
            newCardNumber.append(newNum);
        }
        return newCardNumber.toString();
    }

    private int getSum() {
        int sum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            sum += cardNumber.charAt(i) - '0';
        }
        return sum;
    }

    public int getChecksum() {
        sumOfDigits = getSum();
        if (sumOfDigits % 10 == 0) checksum = 0;
        else checksum = 10 - sumOfDigits % 10;
        return checksum;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
