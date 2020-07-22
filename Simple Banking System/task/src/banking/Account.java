package banking;

public class Account {
    private User user;
    private DataBaseHandler dataBaseHandler = new DataBaseHandler(Main.filename);
    private CardGenerator cardGenerator = new CardGenerator();

    public Account(User user) {
        this.user = user;
    }

    public Account() {
    }

    public boolean isExists(User user) {
        return dataBaseHandler.verification(user) != null;
    }

    private boolean isCorrectData(User user) {
        User ver;
        if (isExists(user)) {
            ver = dataBaseHandler.verification(user);
            return ver.getPIN().equals(user.getPIN());
        } else return false;
    }

    public boolean authorization(String cardNum, String pin) {
        User login = new User(cardNum, pin);
        if (isExists(login)) {
            if (isCorrectData(login)) {
                this.user = login;
                return true;
            }
        }
        return false;
    }

    public void createAccount() {
        user = new User(
                cardGenerator.generateCardNum(),
                cardGenerator.generatePIN()
        );

        dataBaseHandler.insert(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getBalance() {
        return user.getBalance();
    }

    public boolean addIncome(int income) {
        return dataBaseHandler.updateBalance(income);
    }

    public boolean accountDelete() {
        return dataBaseHandler.deleteAccount(this.user);
    }

    public boolean transferMoney(int money, String cardnum) {
        if (user.getBalance() < money || !isExists(new User(cardnum))) {
            return false;
        } else {
            return dataBaseHandler.transfer(user, cardnum, money);
        }
    }
}
