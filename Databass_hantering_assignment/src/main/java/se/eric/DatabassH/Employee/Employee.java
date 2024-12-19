package se.eric.DatabassH.Employee;

import se.eric.DatabassH.WorkRole.WorkRole;

public class Employee {
    int employeeID;
    String name;
    String email;
    String password;
    WorkRole workRole;

    public Employee(int employeeID, String name, String email, String password, WorkRole workRole) {
        this.employeeID = employeeID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.workRole = workRole;
    }                                                   //employee class to mach assignment specification

    public Employee(String name, String email, String password, WorkRole workRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.workRole = workRole;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WorkRole getWorkRole() {
        return workRole;
    }

    public void setWorkRole(WorkRole workRole) {
        this.workRole = workRole;
    }
}
