package se.eric.Main;

import se.eric.DatabassH.Employee.Employee;
import se.eric.DatabassH.WorkRole.WorkRole;
import se.eric.DatabassH.WorkRole.WorkRoleDAOImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class OutputHander {

    public static int getID(String messes){
        System.out.println("What is the role ID of the work role you what to "+messes+"?");
        int roleID = InputHander.getInt();
        InputHander.getString();
        return roleID;
    }
    public static void printWorkRoles(List<WorkRole> workRoles) throws SQLException {
        for (WorkRole workRole : workRoles) {
            System.out.println(workRole);
        }
    }
    public static WorkRole creatWorkRole() throws SQLException {
        System.out.println("What is the work roles title?");
        String title = InputHander.getString();
        System.out.println("Describe the work role?");
        String description = InputHander.getString();
        System.out.println("What is the salary of the work role?");
        double salary = InputHander.getDouble();
        Date currentDate = new Date(System.currentTimeMillis());

        InputHander.getString();
        return new WorkRole(title,description,salary,currentDate);
    }
    public static Employee creatEmployee() throws SQLException {
        WorkRoleDAOImpl workRoleDAO = new WorkRoleDAOImpl();

        System.out.println("What is your name?");
        String name = InputHander.getString();
        System.out.println("What is your email?");
        String email = InputHander.getString();
        System.out.println("What is your password?");
        String password = InputHander.getString();
        System.out.println("What is your work role id?");
        WorkRole workRole = workRoleDAO.getWorkRole(InputHander.getInt());

        InputHander.getString();
        return new Employee(name,email,password,workRole);
    }


    public static void printRoleMenu(){
        List<String> option =List.of("Add work role","Get work roles","Get work role",
                "Update work role","Delete work role","Quit");
        printMenu(option);
    }
    public static void printMainMenu(){
        List<String> option =List.of("Login","Sing up","Admin access","Quit");
        printMenu(option);
    }

    private static void printMenu(List<String> options){
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
