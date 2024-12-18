package se.eric.Main;

import java.util.Scanner;

public class InputHander {

    static Scanner scan;
    static {
        scan  = new Scanner(System.in);
    }
    public static String getString(){
        return scan.nextLine();
    }
    public static double getDouble(){
        boolean aloud = true;
        while (aloud) {
            if (scan.hasNextDouble()) {
                return scan.nextDouble();
            }
            else{
                System.out.println("Please enter a double");
            }
            scan.nextLine();
        }
        return 0.0;
    }
    public static int getInt(){
        boolean aloud = true;
        while (aloud) {
            if (scan.hasNextInt()) {
                return scan.nextInt();
            }
            else{
                System.out.println("Please enter an int");
            }
            scan.nextLine();
        }
        return 0;

    }
    public static void close(){
        scan.close();
    }
}
