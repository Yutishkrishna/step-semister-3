public class M5_EmployeeStatic {

    static class Employee {
        String empName;
        double salary;

        static String companyName = "Bright Horizon Technologies";
        static int employeeCount = 0;

        Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("A", 10000);
        Employee e2 = new Employee("B", 20000);
        Employee e3 = new Employee("C", 30000);

        Employee.printCompanyInfo();
    }
}
