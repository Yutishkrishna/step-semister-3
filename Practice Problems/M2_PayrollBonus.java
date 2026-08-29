public class M2_PayrollBonus {

    static class Employee {
        String empId;
        double salary;

        Employee(String empId, double salary) {
            this.empId = empId;
            this.salary = salary;
        }

        void raiseSalary(double salary) {
            this.salary += salary; // "this.salary" is the field, "salary" is the parameter
        }

        void printSalary() {
            System.out.println(empId + " | Final Salary: Rs " + this.salary);
        }
    }

    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("E-101", 40000),
                new Employee("E-102", 55000),
                new Employee("E-103", 62000),
                new Employee("E-104", 48000)
        };

        for (Employee emp : employees) {
            emp.raiseSalary(5000);
            emp.printSalary();
        }
    }
}
