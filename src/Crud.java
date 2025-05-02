import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Crud {

    ConnectJDBC jdbc = new ConnectJDBC();
    Connection connection;

    {
        try {
            connection = jdbc.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Create
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (name, email) VALUES (?, ?)";
        try (Connection conn = jdbc.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getEmployeeName());
            stmt.setString(2, employee.getEmployeeEmail());
            stmt.executeUpdate();
        }
    }

    // Read
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sqlQuery = "SELECT * FROM employees";
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery)) {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEmployeeName(rs.getString("name"));
                employee.setEmployeeEmail(rs.getString("email"));
                employees.add(employee);
            }
        }
        return employees;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try(
            Connection conn = jdbc.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee user = new Employee();
                user.setId(rs.getInt("id"));
                user.setEmployeeName(rs.getString("name"));
                user.setEmployeeEmail(rs.getString("email"));
                return user;
            }
        }
        return null;
    }

    // Update
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = jdbc.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println(employee.toString());
            stmt.setString(1, employee.getEmployeeName());
            stmt.setString(2, employee.getEmployeeEmail());
            stmt.setInt(3, employee.getId());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = jdbc.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}