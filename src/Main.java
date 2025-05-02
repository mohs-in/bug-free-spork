import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        boolean state = true;

        while(state) {
            System.out.println("Hi, Welcome... Enter which crud operation you want to perform: ");
            System.out.println("1. Create(Add new Employee) 2. Read(Retrieve all Employees) 3. Read(Retrieve a specific Employee)");
            System.out.println("4. Update(Update Employee data) 5. Delete(Delete Employee Record) 6. Exit");

            Scanner scanner = new Scanner(System.in);
            int crudOperation = scanner.nextInt();

            switch (crudOperation) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    getAllEmployees();
                    break;
                case 3:
                    getSpecificEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    state = false;
                    break;
                default:
                    System.out.println("Enter a valid number from the given options");
            }
        }
    }

    private static void deleteEmployee() throws SQLException {
        try {
            Scanner scanner = new Scanner(System.in);
            Employee employee = new Employee();
            Crud obj = new Crud();
            System.out.println("Enter the ID of the Employee to delete: ");
            employee.setId(scanner.nextInt());
            obj.deleteUser(employee.getId());
            System.out.println("Successfully deleted the Employee data with ID: " + employee.getId());
            System.out.println();
        } catch(SQLException e) {
            System.out.println("Error deleting the Employee Data, Enter valid id." + e);
        }
    }

    private static void updateEmployee() throws SQLException {
        try {
            Scanner scanner = new Scanner(System.in);
            Employee employee = new Employee();
            Crud obj = new Crud();

            boolean state = true;
            System.out.println("Enter the ID of the Employee: ");
            int id = scanner.nextInt();
            employee = obj.getEmployeeById(id);
            scanner.nextLine();
            while(state) {
                System.out.println("Select what you want to update: 1. Name 2. Email 3. Exit");
                int option = scanner.nextInt();
                scanner.nextLine();
                if(option == 1) {
                    System.out.println("Enter the updated name of the Employee: ");
                    employee.setEmployeeName(scanner.nextLine());
                } else if(option == 2) {
                    System.out.println("Enter the updated email of the Employee: ");
                    employee.setEmployeeEmail(scanner.nextLine());
                } else if(option == 3) {
                    state = false;
                } else {
                    System.out.println("Invalid option");
                }
            }
            obj.updateEmployee(employee);
            System.out.println("Employee " + employee.getEmployeeName() + " updated successfully.");
            System.out.println();
        } catch(SQLException e) {
            System.out.println("Error updating the Employee Data." + e);
        } catch(InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
        }
    }

    private static void getSpecificEmployee() throws SQLException {
        try {
            Crud obj = new Crud();
            Scanner scanner = new Scanner(System.in);
            int id;
            Employee employee = new Employee();

            System.out.println("Enter the ID of the Employee: ");
            id = scanner.nextInt();
            employee = obj.getEmployeeById(id);
            if(employee == null) System.out.println("No Employee found with id: " + id);
            else System.out.println(employee.toString());

            System.out.println("Successfully Retrieved Employee Data with id: " + id);
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error Retrieving Employee Data.");
        }
    }

    private static void getAllEmployees() throws SQLException {
        try {
            Crud obj = new Crud();
            List<Employee> employees = obj.getAllEmployees();
            for (Employee employee: employees) {
                System.out.println(employee.toString());
            }
            System.out.println("Successfully Retrieved all the Employee Data");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error Retrieving Employee Data.");
        }
    }


    private static void addEmployee() throws SQLException {
        try {
            Employee employee = new Employee();
            Crud obj = new Crud();

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the name of the Employee: ");
            employee.setEmployeeName(scanner.nextLine());
            System.out.println("Enter the email of the Employee: ");
            employee.setEmployeeEmail(scanner.nextLine());

            obj.addEmployee(employee);
            System.out.println(employee.getEmployeeName() + " Added Successfully.");
            System.out.println();
        } catch(SQLException e) {
            System.out.println("Error Adding an employee." + e);
        }
    }
}