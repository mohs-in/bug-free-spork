import java.sql.*;

public class ConnectJDBC {

    public Connection getConnection() throws SQLException {

        String postgresUsername = "postgres";
        String postgresPassword = "ms8000";
        String url = "jdbc:postgresql://localhost:5432/crud";

        return DriverManager.getConnection(url, postgresUsername, postgresPassword);
    }
}
