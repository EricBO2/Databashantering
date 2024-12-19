package se.eric.Main;

import se.eric.DatabassH.WorkRole.WorkRole;
import se.eric.DatabassH.WorkRole.WorkRoleDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class WorkRoleGUI {


    public static void Admin(){
        WorkRoleDAOImpl workRoleDAO = new WorkRoleDAOImpl();
        Boolean continu=true;
        if (login()) {                                          //logs in the user
            System.out.println("Access granted");
            while (continu) {                                   //options and implements admin options
                try {
                    OutputHandler.printRoleMenu();
                    String input = InputHandler.getString().toLowerCase();
                    switch (input) {
                        case "add work role", "1" -> {
                            WorkRole workRole = OutputHandler.creatWorkRole();
                            workRoleDAO.insertWorkRole(workRole);
                        }
                        case "get work roles", "2" -> {
                            List<WorkRole> workRoles = workRoleDAO.getWorkRols();
                            OutputHandler.printWorkRoles(workRoles);
                        }
                        case "get work role", "3" -> {
                            int id = OutputHandler.getID("find");
                            WorkRole workRole = workRoleDAO.getWorkRole(id);
                            System.out.println(workRole);
                        }
                        case "update work role", "4" -> {
                            int id = OutputHandler.getID("update");
                            WorkRole workRole = OutputHandler.creatWorkRole();
                            workRoleDAO.updateWorkRole(workRole, id);
                        }
                        case "delete work role", "5" -> {
                            int id = OutputHandler.getID("delete");
                            workRoleDAO.deleteWorkRole(id);
                        }
                        case "quit", "6"->{
                            continu=false;
                        }
                        default -> {
                            System.out.println("Invalid input");
                        }

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("Access denied");
        }
    }

    private static Boolean login(){                     //login for admin Access

        String password = "1234";                       //used a pre-set password for now
        System.out.println("Admin password");
        String input = InputHandler.getString();
        if (input.equals(password)) {
            return true;
        }
        else {
            return false;
        }

    }

}
