package jdbc.person.dao;

import com.mysql.cj.jdbc.Driver;
import jdbc.person.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList();

        //close() methodunu artiq bele etmekle try connection-in close() methodunu generate edib yaradir.
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from employee");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setAge(resultSet.getInt("age"));
                employee.setSalary(resultSet.getDouble("salary"));

                employeeList.add(employee);
            }

        } catch (SQLException sql) {
            System.out.println("GetAll employee error: " + sql.getMessage());
        }

        return employeeList;
    }

    public void insert(Employee employee) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into employee(name,surname,age,salary) values(?,?,?,?)");

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setInt(3, employee.getAge());
            statement.setDouble(4, employee.getSalary());

            statement.executeUpdate();
        } catch (SQLException sql) {
            System.out.println("insert employee error: " + sql.getMessage());
        }

    }

    public void update(Employee employee) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement("update employee set name=?, surname=?, age=?, salary=?");

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setInt(3, employee.getAge());
            statement.setDouble(4, employee.getSalary());

            statement.executeUpdate();
        } catch (SQLException sql) {
            System.out.println("Update employee error: " + sql.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement("delete from employee where id=?");

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException sql) {
            System.out.println("delete employee error: " + sql.getMessage());
        }
    }

}
