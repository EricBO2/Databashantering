package se.eric.Main;

import se.eric.DatabassH.Employee.Employee;
import se.eric.DatabassH.Employee.EmployeeDOA;
import se.eric.DatabassH.Employee.EmployeeDOAImpl;

import java.sql.SQLException;
import java.util.List;

import static se.eric.Main.InputHander.getString;

public class EmployeeGUI {

    private static EmployeeDOAImpl employeeDOA;
    static {
        employeeDOA = new EmployeeDOAImpl();
    }

    public static void login(){
        boolean logdin = false;
        boolean continu = true;
        try {
            while (continu) {
                System.out.print("User email: ");
                String email = InputHander.getString();
                System.out.println();
                System.out.print("Password: ");
                String password = InputHander.getString();
                System.out.println();

                List<Employee> employees = employeeDOA.getEmployees();
                for (Employee employee : employees) {
                    if (email.equals(employee.getEmail()) && password.equals(employee.getPassword())) {
                        logdin = true;
                        logdinGUI(employee);
                    }
                }
                if (logdin) {
                    System.out.println("login whit another user (Y/N):");
                    logdin = false;
                } else {
                    System.out.println("login failed try again (Y/N):");

                }
                String input = InputHander.getString().toLowerCase();
                switch (input) {
                    case "y" -> continu = true;
                    case "n" -> continu = false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void logdinGUI(Employee user){
        System.out.println("welcome "+user.getName());
        System.out.println("your jobb is "+ user.getWorkRole().getTitle() + " you mack "+user.getWorkRole().getSalary()+ "kr");
        System.out.println("this jobb is described as "+ user.getWorkRole().getDescription());
        System.out.println("this jobb was created "+user.getWorkRole().getCreationDate());
    }
    public static void singUp(){
        try{
            Employee employee = OutputHander.creatEmployee();
            employeeDOA.insertEmployee(employee);
            logdinGUI(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
