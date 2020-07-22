package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class DataBaseHandler {
    private Connection connection;
    private String filename;

    public DataBaseHandler(String filename) {
        this.filename = filename;
        connection = getDbConnection();
        createTable();
    }

    private Connection getDbConnection() {
        String url = "jdbc:sqlite:" + filename;
        try {
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl(url);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS card (" +
                "   id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   number TEXT," +
                "   pin TEXT," +
                "   balance INTEGER DEFAULT 0" +
                ");";

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert(User user) {
        String sql = "INSERT INTO card(number, pin) VALUES(?,?)";

        try (PreparedStatement prst = connection.prepareStatement(sql)) {
            prst.setString(1, user.getCardNum());
            prst.setString(2, user.getPIN());
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User verification(User user) {
        String sql = "SELECT * FROM card WHERE number=" + user.getCardNum();
        User returnedUser = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                returnedUser = new User(rs.getInt("id"),
                        rs.getString("number"),
                        rs.getString("pin"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnedUser;
    }

    public boolean updateBalance(int value) {
        String sql = "UPDATE card SET balance = balance + ?";

        try {
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, value);
            prst.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public boolean transfer(User sender, String receiver, int value) {
        String sql = "UPDATE card SET balance = CASE number" +
                "WHEN '" + sender.getCardNum() + "' THEN 'balance - ?'" +
                "WHEN '" + receiver + "' THEN 'balance + ?'" +
                "ELSE balance END" +
                "WHERE number IN('" + sender.getCardNum() + "','" +
                receiver + "');";

        try {
            PreparedStatement prst = connection.prepareStatement(sql);

            prst.setInt(1,value);
            prst.setInt(2,value);
            prst.executeUpdate();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean deleteAccount(User user) {
        String sql = "DELETE FROM card WHERE number=? AND pin=?";
        try {
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1, user.getCardNum());
            prst.setString(2, user.getPIN());

            prst.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
