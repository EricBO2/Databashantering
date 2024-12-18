package se.eric.Main;

import se.eric.DatabassH.WorkRole.WorkRole;
import se.eric.DatabassH.WorkRole.WorkRoleDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class WorkRoleGUI {


    public static void Admin(){
        WorkRoleDAOImpl workRoleDAO = new WorkRoleDAOImpl();
        Boolean continu=true;
        if (login()) {
            System.out.println("Access granted");
            while (continu) {
                try {
                    OutputHander.printRoleMenu();
                    String input = InputHander.getString().toLowerCase();
                    switch (input) {
                        case "add work role", "1" -> {
                            WorkRole workRole = OutputHander.creatWorkRole();
                            workRoleDAO.insertWorkRole(workRole);
                        }
                        case "get work roles", "2" -> {
                            List<WorkRole> workRoles = workRoleDAO.getWorkRols();
                            OutputHander.printWorkRoles(workRoles);
                        }
                        case "get work role", "3" -> {
                            int id = OutputHander.getID("find");
                            WorkRole workRole = workRoleDAO.getWorkRole(id);
                            System.out.println(workRole);
                        }
                        case "update work role", "4" -> {
                            int id = OutputHander.getID("update");
                            WorkRole workRole = OutputHander.creatWorkRole();
                            workRoleDAO.updateWorkRole(workRole, id);
                        }
                        case "delete work role", "5" -> {
                            int id = OutputHander.getID("delete");
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

    private static Boolean login(){

        System.out.println("Admin password");
        String input = InputHander.getString();
        if (input.equals("1234")){
            return true;
        }
        else {
            return false;
        }

    }

}
