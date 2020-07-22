package banking;

public class User {
    private int id;
    private String cardNum;
    private String PIN;
    private int balance = 0;

    public User() {
    }

    public User(int id, String cardNum, String PIN) {
        this.id = id;
        this.cardNum = cardNum;
        this.PIN = PIN;
    }

    public User(String cardNum, String PIN) {
        this.cardNum = cardNum;
        this.PIN = PIN;
    }

    public User(int id, String cardNum, String PIN, int balance) {
        this.id = id;
        this.cardNum = cardNum;
        this.PIN = PIN;
        this.balance = balance;
    }

    public User(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
