package se.eric.DatabassH.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDOA {
    public void insertEmployee(Employee employee) throws SQLException;
    public List<Employee> getEmployees() throws SQLException;
    public Employee getEmployee(Integer employeeID) throws SQLException;
    public void deleteEmployee(Integer employeeID) throws SQLException;
    public void updateEmployee(Employee employee, int employeeID) throws SQLException;
}
