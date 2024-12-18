package se.eric.Main;

import se.eric.DatabassH.Employee.Employee;
import se.eric.DatabassH.WorkRole.WorkRole;
import se.eric.DatabassH.WorkRole.WorkRoleDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {

    private static WorkRoleDAOImpl workRoleDAO;

    public static void main(String[] args) {
        workRoleDAO = new WorkRoleDAOImpl();
        Boolean continu = true;

        while (continu) {
            OutputHander.printMainMenu();
            String input = InputHander.getString().toLowerCase();
            switch (input) {
                case "login","1" ->{
                    EmployeeGUI.login();
                }
                case "sing up","2"->{
                    Employee employee = OutputHander.creatEmployee();

                }
                case "admin access","3"->{
                    WorkRoleGUI.Admin();
                }
                case "quit", "4" -> {
                    continu=false;
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }

}
