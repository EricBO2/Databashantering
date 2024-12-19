package se.eric.Main;


public class Main {

    public static void main(String[] args) {
        Boolean continu = true;

        while (continu) {                                           //Main loop that gets you to the appropriate menu
            OutputHandler.printMainMenu();
            String input = InputHandler.getString().toLowerCase();
            switch (input) {
                case "login","1" ->{
                    EmployeeGUI.login();
                }
                case "sing up","2"->{
                    EmployeeGUI.singUp();
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
        InputHandler.close();
    }

}
