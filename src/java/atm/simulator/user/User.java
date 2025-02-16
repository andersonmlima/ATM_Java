package java.atm.simulator.user;
import java.atm.simulator.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String accountNumber;
    private String password;
    private double balance;

    public User(String accountNumber, String password, double balance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    // Metodo para buscar um usuário pelo número da conta
    public static User getUser(String accountNumber){
        String sql = "select * from atm_simulator.user where account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("account_number"),
                        rs.getString("password"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
