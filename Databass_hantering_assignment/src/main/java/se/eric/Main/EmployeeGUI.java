package se.eric.Main;

import se.eric.DatabassH.Employee.Employee;
import se.eric.DatabassH.Employee.EmployeeDOAImpl;

import java.sql.SQLException;
import java.util.List;

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
                String email = InputHandler.getString();
                System.out.println();
                System.out.print("Password: ");
                String password = InputHandler.getString();
                System.out.println();                           //gets user input

                List<Employee> employees = employeeDOA.getEmployees();
                for (Employee employee : employees) {
                    if (email.equals(employee.getEmail()) && password.equals(employee.getPassword())) {
                        logdin = true;                          //test if any employee matches the intput
                        logdinGUI(employee);                    //prints employee info
                    }
                }
                if (logdin) {
                    System.out.println("login whit another user (Y/N):");
                    logdin = false;
                } else {
                    System.out.println("login failed try again (Y/N):");

                }
                String input = InputHandler.getString().toLowerCase();
                switch (input) {
                    case "y" -> continu = true;
                    case "n" -> continu = false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void logdinGUI(Employee user){                   //prints employee info
        System.out.println("welcome "+user.getName());
        System.out.println("your jobb is "+ user.getWorkRole().getTitle() + " you mack "+user.getWorkRole().getSalary()+ "kr");
        System.out.println("this jobb is described as "+ user.getWorkRole().getDescription());
        System.out.println("this jobb was created "+user.getWorkRole().getCreationDate());
    }
    public static void singUp(){                                    //creates a employee
        try{
            Employee employee = OutputHandler.creatEmployee();
            employeeDOA.insertEmployee(employee);
            logdinGUI(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
