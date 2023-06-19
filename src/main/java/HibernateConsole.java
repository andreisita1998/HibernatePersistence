

import java.util.Scanner;

public class HibernateConsole {

    private static Scanner scanner = new Scanner(System.in);
    private static EmployeeDAO dao = new EmployeeDAO();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add employee");
            System.out.println("2. Update employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Get all employees");
            System.out.println("5. Get employees by criteria");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    getAllEmployees();
                    break;
                case 5:
                    getEmployeesByCriteria();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter employee first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter employee age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter employee address: ");
        String address = scanner.nextLine();

        System.out.print("Enter employee salary: ");
        double salary = Double.parseDouble(scanner.next());
        scanner.nextLine();

        Employees employee = new Employees(firstName, age, address, salary);
        dao.addEmployee(employee);
        System.out.println("Employee added!");
    }
    private static void updateEmployee() {
        System.out.print("Enter employee ID: ");
        Long employeeId = scanner.nextLong();
        scanner.nextLine();

        Employees employee = dao.getEmployeeById(employeeId);

        if (employee == null) {
            System.out.println("Employee with ID " + employeeId + " not found!");
            return;
        }

        System.out.print("Enter new first name (leave blank to keep current value: " + employee.getFirstName() + "): ");
        String newFirstName = scanner.nextLine();

        if (!newFirstName.isEmpty()) {
            employee.setFirstName(newFirstName);
        }

        System.out.print("Enter new age (leave blank to keep current value: " + employee.getAge() + "): ");
        String newAgeStr = scanner.nextLine();

        if (!newAgeStr.isEmpty()) {
            int newAge = Integer.parseInt(newAgeStr);
            employee.setAge(newAge);
        }

        System.out.print("Enter new address (leave blank to keep current value: " + employee.getAddress() + "): ");
        String newAddress = scanner.nextLine();

        if (!newAddress.isEmpty()) {
            employee.setAddress(newAddress);
        }

        System.out.print("Enter new salary (leave blank to keep current value: " + employee.getSalary() + "): ");
        String newSalaryStr = scanner.nextLine();

        if (!newSalaryStr.isEmpty()) {
            double newSalary = Double.parseDouble(newSalaryStr);
            employee.setSalary(newSalary);
        }

        dao.updateEmployee(employee);
        System.out.println("Employee updated!");
    }

    private static void deleteEmployee() {
        System.out.print("Enter employee ID: ");
        Long employeeId = scanner.nextLong();
        scanner.nextLine();

        Employees employee = dao.getEmployeeById(employeeId);

        if (employee == null) {
            System.out.println("Employee with ID " + employeeId + " not found!");
            return;
        }

        dao.deleteEmployee(employeeId);
        System.out.println("Employee deleted!");
    }

    private static void getAllEmployees() {
        System.out.println("All employees:");
        dao.getAllEmployees().forEach(System.out::println);
    }

    private static void getEmployeesByCriteria() {
        System.out.println("Enter criteria (id, firstName, age, address, salary):");
        String criteria = scanner.nextLine();

        System.out.println("Enter value:");
        String value = scanner.nextLine();

        dao.getEmployeesByCriteria(criteria, value).forEach(System.out::println);
    }

}