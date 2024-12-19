package se.eric.Main;

import se.eric.DatabassH.Employee.Employee;
import se.eric.DatabassH.WorkRole.WorkRole;
import se.eric.DatabassH.WorkRole.WorkRoleDAOImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class OutputHandler {

    public static int getID(String messes){                         //gets work role ID
        System.out.println("What is the role ID of the work role you what to "+messes+"?");
        int roleID = InputHandler.getInt();
        InputHandler.getString();
        return roleID;
    }
    public static void printWorkRoles(List<WorkRole> workRoles) throws SQLException {
        for (WorkRole workRole : workRoles) {                       //prints a list of work roles
            System.out.println(workRole);
        }
    }
    public static WorkRole creatWorkRole() throws SQLException {    //gets the user to creat a work role
        System.out.println("What is the work roles title?");
        String title = InputHandler.getString();
        System.out.println("Describe the work role?");
        String description = InputHandler.getString();
        System.out.println("What is the salary of the work role?");
        double salary = InputHandler.getDouble();
        Date currentDate = new Date(System.currentTimeMillis());

        InputHandler.getString();
        return new WorkRole(title,description,salary,currentDate);
    }
    public static Employee creatEmployee() throws SQLException {    //gets the user to creat a employee
        WorkRoleDAOImpl workRoleDAO = new WorkRoleDAOImpl();

        System.out.println("What is your name?");
        String name = InputHandler.getString();
        System.out.println("What is your email?");
        String email = InputHandler.getString();
        System.out.println("What is your password?");
        String password = InputHandler.getString();
        System.out.println("What is your work role id?");           //made it so its work role ID to make it easier on my self
        WorkRole workRole = workRoleDAO.getWorkRole(InputHandler.getInt());

        InputHandler.getString();                                   //to reset the scanner
        return new Employee(name,email,password,workRole);
    }


    public static void printRoleMenu(){                             //pre-prepared list off options for role menu
        List<String> option =List.of("Add work role","Get work roles","Get work role",
                "Update work role","Delete work role","Quit");
        printMenu(option);
    }
    public static void printMainMenu(){                             //pre-prepared list off options for main menu
        List<String> option =List.of("Login","Sing up","Admin access","Quit");
        printMenu(option);
    }

    private static void printMenu(List<String> options){            //prints a list of options
        System.out.println("_________________________");
        System.out.println("|what do you want to do?|");
        for (int i = 1; i < options.size()+1; i++) {
            System.out.print("|"+i+": "+options.get(i-1));
            if (options.get(i-1).length()>=16){
                System.out.println("\t|");
            }
            else if (options.get(i-1).length()>=12){
                System.out.println("\t\t|");
            }
            else {
                System.out.println("\t\t\t\t|");
            }
        }
        System.out.println("_________________________");
    }
}
