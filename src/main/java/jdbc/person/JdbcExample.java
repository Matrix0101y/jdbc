package jdbc.person;

import com.mysql.cj.jdbc.Driver;
import jdbc.person.dao.EmployeeDao;
import jdbc.person.model.Employee;

import java.util.List;

public class JdbcExample {
    public static void main(String[] args) {

        EmployeeDao employeeDao = new EmployeeDao();

        Employee employee = new Employee();

        employee.setId(6);
        employee.setName("Yasin");
        employee.setSurname("Sadigzade");
        employee.setAge(20);
        employee.setSalary(6300);

        employeeDao.update(employee);
        employeeDao.delete(7);


        List<Employee> employeeList = employeeDao.getAllEmployees();
        printEmployees(employeeList);

    }

    private static void printEmployees(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
